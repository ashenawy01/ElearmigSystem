package Models;

import Entities.Course;
import Entities.Professor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;


public class CourseDB implements IDatabase, Serializable{ //
    private static final String courseFile = "course.bin";

    /**
     * the first call function to create the db with a list of objects
     * param: list of objects with type Course
     * @return store the list in the db file and return nothing
     */
    @Override
    public void createDB (Object... obj) {
        // buffering the ObjectOutputStream by BufferedOutputStream and with size of 8192 bytes (or 8 kilobytes)
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new  BufferedOutputStream(new FileOutputStream(courseFile), 8192)) ) {
            // store the objects in an array of courses
            Course[] courses = Arrays.copyOf(obj, obj.length, Course[].class);
            // writing the file
            for (Course c: courses) {
                oos.writeObject(c);
            }
        }
        catch (IOException e) { // catching any io exception thrown by the file stream
            e.printStackTrace();
        }

    }

        /**
         * Appends a new course to the course data file
         *
         * @param obj - The course object to add
         * @return boolean - true if the course was added successfully, false otherwise
         */
        @Override
        public boolean appendObject(Object obj) {
            Course course = (Course) obj;

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(courseFile, true))) {
                // Override ObjectOutputStream's writeStreamHeader method to reset the stream header
                // This is necessary to append new objects to an existing file
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            }) {
                // Write the course object to the file
                oos.writeObject(course);
                System.out.println("Course : " + course.getName() + " is added successfully!");
                return true;
            } catch (IOException e) {
                // Print stack trace for any IO exceptions
                e.printStackTrace();
            }

            return false;
        }

        /**
         * (For test ONLY)
         * Retrieves all courses from the course data file and prints them to the console
         */
        @Override
        public void retrieveAll() {
            ArrayList<Course> courses = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(courseFile)))) {
                // Read all course objects from the file and add them to the courses list
                while (true) {
                    Course course = (Course) ois.readObject();
                    courses.add(course);
                }

            } catch (EOFException e) {
                // Print all courses to the console
                for (Course course : courses) {
                    System.out.println(course);
                }
            } catch (Exception e) {
                // Print stack trace for any exceptions
                e.printStackTrace();
            }
        }

        /**
         * Removes a course from the course data file
         *
         * @param obj - The course object to remove
         * @return boolean - true if the course was removed successfully, false otherwise
         */
        @Override
        public boolean removeObj(Object obj) {
            List<Course> courses = new ArrayList<>();
            try (ObjectInputStream ois = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream(courseFile)))) {
                // Read all course objects from the file and add them to the courses list
                while (true) {
                    Course course = (Course) ois.readObject();
                    if (!course.getCourseID().equalsIgnoreCase(((Course) obj).getCourseID())) {
                        courses.add(course);
                    }
                }
            } catch (EOFException e) {
                // Do nothing - end of file has been reached
            } catch (ClassNotFoundException | IOException e) {
                // Print stack trace for any exceptions and return false
                e.printStackTrace();
                return false;
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(courseFile)))) {
                // Write all remaining courses to the file, excluding the course to be removed
                for (Course course : courses) {
                    oos.writeObject(course);
                }
                System.out.println("The Course : " + ((Course) obj).getName() + " is deleted successfully from the database");

                return true;
            } catch (IOException e) {
                // Print stack trace for any IO exceptions and return false
                e.printStackTrace();
                return false;
            }
        }


    /**
     * Finds and returns the Course object with the specified ID.
     * @param id The ID of the course to find.
     * @return The Course object with the specified ID, or null if not found.
     */


    @Override
    public <T> Object find(T id) {
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(courseFile)))) {

            while (true) {
                Course course = (Course) ois.readObject();
                if (course.getCourseID().equalsIgnoreCase(id.toString()))
                    return course;
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
     * Retrieves all Course objects from the courseFile and returns them as an ArrayList.
     * @return An ArrayList of all Course objects in the courseFile.
     */
    public ArrayList<Course> getAll() {
        ArrayList<Course> courses = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(courseFile)))) {
            while (true) {
                Course course = (Course) ois.readObject();
                courses.add(course);
            }
        } catch (EOFException e) {
            return courses;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all Course objects in the courseFile that match the specified department ID.
     * @param dept The department ID to match.
     * @return An ArrayList of all Course objects in the courseFile that match the specified department ID.
     */
    public ArrayList<Course> getCoursesOf(String dept) {
        ArrayList<Course> courses = new ArrayList<>();
        for (Course c : getAll()) {
            if (c.getCourseID().contains(dept.toUpperCase())) {
                courses.add(c);
            }
        }
        return courses;
    }

    public ArrayList<Course> getProfCourses (int profId) {
        ArrayList<Course> courses = new ArrayList<>();
        for (Course c : getAll()) {
            if (c.getPID() == profId) {
                courses.add(c);
            }
        }
        return courses;
    }


}
