package Entities;

import java.util.ArrayList;
import java.util.List;

public class Professor extends User {
    private List<Course> courses; // list of courses that each prof teach
    private List<Department> departments; // list of departments each prof teach in

    // single constructor
    public Professor(String name, int userID, String password) {
        super(name, userID, password);
        courses = new ArrayList<>();
        departments = new ArrayList<>();
    }

    // setter and getters
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    // add department to list
    public void addDept (Department d) {
        this.departments.add(d);
    }

    // remove a single department form the list
    // it returns true if it is removed or false if it doesn't exist
    public boolean removeDept(Department d)
    {
        return departments.remove(d);
    }

    // add a course to the courses list
    public void addCourse (Course c) {
        this.courses.add(c);
    }

    // remove a single course form the list
    // it returns true if it is removed or false if it doesn't exist
    public boolean removeCourse(Course c) {
        return courses.remove(c);
    }

}
