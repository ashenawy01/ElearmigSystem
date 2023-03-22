package Controller;

import Entities.*;
import Models.StudentDB;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class ValidStudent implements Valid{
    private static StudentDB studentdb = new StudentDB();

    @Override
    public Object signIn(int userID, String pass) {
        formTxt.printTxt("Login");
        ArrayList<Course> courses;

        Student student = (Student) studentdb.find(userID, pass);
        if (student != null) {
            System.out.println("Welcome " + student.getName());
            System.out.println("\nYour courses");
            for (Course c: courseDB.getCoursesOf(student.getDept().name())) {
                System.out.println(c);
            }
            return student;
        }
        else {
            System.out.println("Incorrect username or password");
            return null;
        }
    }

    public Object signUp(String name, int userID, String pass, int acYear, int sem, Department department) {
        if (name.isEmpty() || name.length() < 2) {
            System.out.println("Error! Please, Enter a valid name");
            return null;
        } else if (studentdb.find(userID) != null) {
            System.out.println("User is already existed");
            return null;
        } else if (userID < 1) {
            System.out.println("Error! Please, Enter a valid userID");
            return null;
        } else if (pass.isEmpty() || pass.length() < 3 || !((pass.matches(".*[a-zA-Z].*") && pass.matches(".*\\d.*")))) {
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        } else if (acYear < 0 || acYear > 6) {
            System.out.println("Error! Please, Enter a valid academic year");
            return null;
        } else if (sem < 0 || sem > 2) {
            System.out.println("Error! Please, Enter a valid semester");
            return null;
        } else {
            Student student = new Student(name, userID, pass, department, acYear, sem);
            ArrayList<Course> courses = courseDB.getCoursesOf(department.name());
            student.setCourses(courses);
            if (studentdb.appendObject(student)) {
                // Print a success message to the console
                System.out.println("Entities.Student : " + student.getName() + " is added successfully!");
                return student;
            }
            else {
                System.out.println("Error with database connection, please try again");
                return null;
            }

        }
    }

    @Override
    public Object resetPassword(String newPass, int userID) {
        Student student = (Student) studentdb.find(userID);
        if (student != null && studentdb.removeObj(student)) {
            student.setPassword(newPass);
            studentdb.appendObject(student);
            System.out.println("New password is add successfully!");
            return student;
        } else {
            System.out.println("Wrong userID - User not found");
            return null;
        }
    }



}
