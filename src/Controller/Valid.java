package Controller;

import Entities.Course;
import Entities.Department;
import Entities.Professor;
import Models.CourseDB;
import Models.FormDB;
import Models.ProfessorDB;
import Models.StudentDB;

import java.util.List;

public interface Valid {
    CourseDB courseDB = new CourseDB();
    FormDB formTxt = new FormDB();

    Object signIn (int userID, String pass);
    Object resetPassword(String newPass, int userID);
}
