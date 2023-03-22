package Controller;

import Entities.Course;
import Entities.Department;
import Entities.Professor;
import Entities.Student;
import Models.ProfessorDB;

import java.util.ArrayList;
import java.util.List;

public class ValidProfessor implements Valid{
    private static ProfessorDB professordb = new ProfessorDB();

    @Override
    public Object signIn(int userID, String pass) {
        Professor professor = (Professor) professordb.find(userID, pass);
        if (professor != null) {
            System.out.println("Welcome Dr. "+ professor.getName());
            System.out.println("\nYour courses");
            for (Course c: courseDB.getProfCourses(professor.getUserID())) {
                System.out.println(c);
            }
            return true;
        }
        else {
            System.out.println("Incorrect username or password");
            return false;
        }
    }

    public Object signUp(String name, int userID, String pass, Department... departments) {
        if (name.isEmpty() || name.length() < 2) {
            System.out.println("Error! Please, Enter a valid name");
            return null;
        } else if (professordb.find(userID) != null) {
            System.out.println("User is already existed");
            return null;
        } else if (userID < 1) {
            System.out.println("Error! Please, Enter a valid userID");
            return null;
        } else if (pass.isEmpty() || pass.length() < 3 || !((pass.matches(".*[a-zA-Z].*") && pass.matches(".*\\d.*")))) {
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
            return null;
        } else {
            Professor professor = new Professor(name, userID, pass);
            for (Department d: departments) {
                professor.addDept(d);
            }
            ArrayList<Course> courses = courseDB.getProfCourses(userID);
            professor.setCourses(courses);
            if (professordb.appendObject(professor)) {
                // Print a success message to the console
                System.out.println("Professor : " + professor.getName() + " is added successfully!");
                return professor;
            }
            else {
                System.out.println("Error with database connection, please try again");
                return null;
            }

        }
    }

    @Override
    public Object resetPassword(String newPass, int userID) {
        Professor professor = (Professor) professordb.find(userID);
        if (professor != null && professordb.removeObj(professor)) {
            professor.setPassword(newPass);
            professordb.appendObject(professor);
            System.out.println("New password is add successfully!");
            return professor;
        } else {
            System.out.println("Wrong userID - User not found");
            return null;
        }

    }
}
