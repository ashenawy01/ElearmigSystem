//package Controller;
//
//import Entities.Course;
//import Model.Database.StudentDB;
//import Entities.Student;
//
//import java.io.EOFException;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class validStudent implements Valid, IClassStoring {
//
//    private StudentDB studentDB = new StudentDB();
//    private static ArrayList<String> availableDept = new ArrayList<>(List.of("CS", "ENG", "BS"));
//    Scanner scanner = new Scanner(System.in);
//
//    @Override
//    public void showCourses(String userDept, boolean hasToken) {
//        if (hasToken) {
//            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(IClassStoring.CourseFileName))) {
//                while (true) {
//                    Course course = (Course) ois.readObject();
//                    if (course.getCourseID().contains(userDept)) {
//                        System.out.println("course : " + course.getName());
//                    }
//                }
//            } catch (EOFException e) {
//                System.out.println("End of list");
//            } catch (ClassNotFoundException | IOException e) {
//                e.printStackTrace();
//            }
//
//        } else {
//            System.out.println("Sorry! Access denied, please login");
//        }
//    }
//
//    @Override
//    public boolean login(int userID, String pass) {
//        Student s = (Student) studentDB.find(userID, pass);
//        if (s != null) {
//            s.setHasToken(true);
//            System.out.println(s);
//            showCourses(s.getDept(), s.hasToken());
//            return true;
//        } else {
//            System.out.println("Incorrect userID or password");
//            return false;
//        }
//    }
//
//    public boolean validInput(String name, int userID, String pass, String dept, int acYear, int sem) {
//        if (name.isEmpty() || name.length() < 2) {
//            System.out.println("Error! Please, Enter a valid name");
//            return false;
//        } else if (userID < 1) {
//            System.out.println("Error! Please, Enter a valid userID");
//            return false;
//        } else if (pass.isEmpty() || pass.length() < 3 || !((pass.matches(".*[a-zA-Z].*") && pass.matches(".*\\d.*")))) {
//            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
//            return false;
//        } else if (!availableDept.contains(dept)) {
//            System.out.println("Invalid Department, please try again");
//            return false;
//        } else if (acYear < 0 || acYear > 6) {
//            System.out.println("Error! Please, Enter a valid academic year");
//            return false;
//        } else if (sem < 0 || sem > 2) {
//            System.out.println("Error! Please, Enter a valid semester");
//            return false;
//        } else {
//            System.out.println("All input valid");
//            return true;
//        }
//    }
//
//    @Override
//    public void signUp() {
//        String name;
//        int userID;
//        String pass;
//        int acYear;
//        int sem;
//        String dept;
//        boolean validInput = false;
//        int iterator = 0;
//        do {
//            System.out.println("_".repeat(25));
//            if (iterator > 0) {
//                System.out.println("Please, try again");
//            }
//            System.out.print("Entities.Student name : ");
//            name = scanner.next();
//            System.out.print("Entities.Student userID : ");
//            userID = scanner.nextInt();
//            System.out.print("Entities.Student password : ");
//            pass = scanner.next();
//            System.out.print("Entities.Student department : ");
//            dept = scanner.next();
//            System.out.print("Academic year : ");
//            acYear = scanner.nextInt();
//            System.out.print("Semester : ");
//            sem = scanner.nextInt();
//            System.out.println("_".repeat(25));
//            validInput = validInput(name, userID, pass, dept, acYear, sem);
//            iterator++;
//        } while (!validInput);
//
//        Student student = null;
//
//        if (validInput)
//            student = new Student(name, pass, dept, acYear, sem);
//
//        if (studentDB.find(userID, pass) != null) {
//            studentDB.appendObject(student);
//        }
//    }
//
//    @Override
//    public void resetPassword(String newPass1, String newPass2, int userID) {
//        if (newPass1 == newPass2) {
//            Student s = (Student) studentDB.find(userID);
//            if (s == null) {
//                System.out.println("Error 404 - Entities.User not found");
//            } else {
//                s.setPassword(newPass1);
//                studentDB.removeObj(s);
//                studentDB.appendObject(s);
//                System.out.println("Password changed successfully");
//            }
//        } else {
//            System.out.println("Error - the 2 passwords are not the same");
//        }
//    }
//}
