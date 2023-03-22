package Models;

import Entities.Professor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfessorDB implements IDatabase{
    private static final String professorFile = "professor.bin";

    /**
     * Creates a new database file containing the given objects.
     * @param obj The objects to store in the database.
     */
    @Override
    public void createDB (Object... obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(professorFile))) ) {
            // Convert the array of objects to an array of Professors
            Professor[] professors = Arrays.copyOf(obj, obj.length, Professor[].class);
            // Write each professor object to the output stream
            for (Professor o : professors) {
                oos.writeObject(o);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends a new object to the database file.
     * @param obj The object to append to the database.
     * @return true if the object was successfully appended, false otherwise.
     */
    @Override
    public boolean appendObject(Object obj) {
        Professor professor = (Professor) obj;
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(professorFile, true))) {
            // Override the writeStreamHeader method to reset the stream header
            protected void writeStreamHeader() throws IOException {
                reset();
            }}) {
            // Write the professor object to the output stream
            oos.writeObject(professor);
            // Print a success message to the console
            System.out.println("Entities.Professor : " + professor.getName() + " is added successfully!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves all objects from the database file and prints them to the console.
     */
    @Override
    public void retrieveAll() {
        StringBuilder stringBuilder = new StringBuilder();
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(professorFile)))) {
            Professor professor;
            // Read each professor object from the input stream and append it to the string builder
            while (true) {
                professor = (Professor) ois.readObject();
                stringBuilder.append(professor + "\n");
            }
        } catch (EOFException e) {
            // End of file reached, print the string builder contents to the console
            System.out.println(stringBuilder);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes an object from the database file.
     * @param obj The object to remove from the database.
     * @return true if the object was successfully removed, false otherwise.
     */
    @Override
    public boolean removeObj(Object obj) {
        List<Professor> professors = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(professorFile)))) {
            // Read each professor object from the input stream and add it to the list
            while (true) {
                Professor s = (Professor) ois.readObject();
                professors.add(s);
            }
        } catch (EOFException e) {
            // End of file reached, do nothing
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(professorFile)))){

            // Write each professor object from the list to the output stream, except for the one to be removed
            for (Professor s : professors) {
                if (!s.equals(obj)) {
                    oos.writeObject(s);
                }
            }
            // Print a success message to the console
            System.out.println("The professor : " + obj + " is deleted successfully from the database");
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Finds a professor in the file with the given ID and password.
     * @param id the ID of the professor to find
     * @param pass the password of the professor to find
     * @return the professor object if found, or null if not found
     */
    public Object find(int id, String pass) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(professorFile)))) {
            while (true) {
                Professor professor = (Professor) ois.readObject();
                if (professor.getUserID() == id && professor.getPassword().equalsIgnoreCase(pass)) {
                    return professor;
                }
            }
        } catch (EOFException e) {
            System.out.println("Error 404 - User is not existed");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Finds a professor in the file with the given ID.
     * @param id the ID of the professor to find
     * @return the professor object if found, or null if not found
     */
//    public Object find(int id) {
//        try (ObjectInputStream ois = new ObjectInputStream(
//                new BufferedInputStream(new FileInputStream(professorFile)))) {
//            while (true) {
//                Professor professor = (Professor) ois.readObject();
//                if (professor.getUserID() == id) {
//                    return professor;
//                }
//            }
//        } catch (EOFException e) {
//            System.out.println("Error 404 - User is not existed");
//            return null;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }


    @Override
    public <T> Object find(T id) {
        try (ObjectInputStream ois = new ObjectInputStream(
                             new BufferedInputStream(new FileInputStream(professorFile)))) {

            while (true) {
                Professor professor = (Professor) ois.readObject();
                if (professor.getUserID() == (int) id)
                    return professor;
            }
        } catch (EOFException e) {
            return null;
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
