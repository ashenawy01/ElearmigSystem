/*
This is a class for a database of students, implementing the IDatabase interface.
It has functions for creating, appending, retrieving, and removing objects from the database,
as well as finding a student by their ID or their ID and password, and getting the last ID in the database.
It uses a binary file to store and read the students.
*/
package Models;

import Entities.Professor;
import Entities.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentDB implements IDatabase {
    private static final String studentFile = "students.bin";

    /**
     * Creates a new database file containing the given objects.
     * @param obj The objects to store in the database.
     */
    @Override
    public void createDB (Object... obj) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new  BufferedOutputStream(new FileOutputStream(studentFile))) ) {
            // Convert the array of objects to an array of Students
            Student[] students = Arrays.copyOf(obj, obj.length, Student[].class);
            // Write each student object to the output stream
            for (Student o : students) {
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
        Student student = (Student) obj;
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(studentFile, true))) {
            // Override the writeStreamHeader method to reset the stream header
            protected void writeStreamHeader() throws IOException {
                reset();
            }}) {
            // Write the student object to the output stream
            oos.writeObject(student);
            // Print a success message to the console
            System.out.println("Entities.Student : " + student.getName() + " is added successfully!");
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
                new BufferedInputStream(new FileInputStream(studentFile)))) {
            Student student;
            // Read each student object from the input stream and append it to the string builder
            while (true) {
                student = (Student) ois.readObject();
                stringBuilder.append(student + "\n");
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
        List<Student> students = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(studentFile)))) {
            // Read each student object from the input stream and add it to the list
            while (true) {
                Student s = (Student) ois.readObject();
                students.add(s);
            }
        } catch (EOFException e) {
            // End of file reached, do nothing
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return false;
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(studentFile)))){

            // Write each student object from the list to the output stream, except for the one to be removed
            for (Student s : students) {
                if (!s.equals(obj)) {
                    oos.writeObject(s);
                }
            }
            // Print a success message to the console
            System.out.println("The student : " + obj + " is deleted successfully from the database");
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Finds a student in the file with the given ID and password.
     * @param id the ID of the student to find
     * @param pass the password of the student to find
     * @return the student object if found, or null if not found
     */
    public Object find(int id, String pass) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(studentFile)))) {
            while (true) {
                Student student = (Student) ois.readObject();
                if (student.getUserID() == id && student.getPassword().equalsIgnoreCase(pass)) {
                    return student;
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
     * Finds a student in the file with the given ID.
     * @param id the ID of the student to find
     * @return the student object if found, or null if not found
     */
    @Override
    public <T> Object find(T id) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(studentFile)))) {

            while (true) {
                Student student = (Student) ois.readObject();
                if (student.getUserID() == (int) id)
                    return student;
            }
        } catch (EOFException e) {
            return null;
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the last ID in the file, or -1 if the file is empty.
     * @return the last ID in the file, or -1 if the file is empty
     */
    public int lastID () {
        int lID = 1;
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(studentFile)))) {
            Student lastStudent;
            while (true) {
                lastStudent = (Student) ois.readObject();
                lID = lastStudent.getUserID();
            }
        } catch (EOFException e) {
            return lID;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
}


