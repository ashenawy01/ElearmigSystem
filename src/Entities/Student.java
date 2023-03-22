package Entities;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student user that extends from the User class.
 */
public class Student extends User {

    private Department dept; // Department of the student
    private int year; // year of the student
    private int semester; // semester of the student
    private List<Course> courses; // courses that the student has taken

    /**
     * Creates a new Student object.
     *
     * @param name     the name of the student
     * @param userID   the ID of the student
     * @param password the password of the student
     * @param dept     the department of the student
     * @param year1    the year of the student
     * @param semester1 the semester of the student
     */
    public Student(String name, int userID, String password, Department dept, int year1, int semester1) {
        super(name, userID, password);
        setYear(year1); // set the year of the student
        setSemester(semester1); // set the semester of the student
        this.dept = dept; // set the department of the student
        courses = new ArrayList<>(); // initialize the courses list
    }

    /**
     * Prints out the courses that the student has taken.
     */
    private void printCourses() {
        System.out.print("{");
        for (Course c: courses) {
            System.out.print(" " + c.getName() + " ");
        }
        System.out.print("}");
    }

    /**
     * Gets the department of the student.
     *
     * @return the department of the student
     */
    public Department getDept() {
        return dept;
    }

    /**
     * Sets the department of the student.
     *
     * @param dept the department of the student
     */
    public void setDept(Department dept) {
        this.dept = dept;
    }

    /**
     * Gets the year of the student.
     *
     * @return the year of the student
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year of the student.
     *
     * @param year the year of the student
     */
    public void setYear(int year) {
        if (year > 0 && year < 6)
            this.year = year;
        else
            System.out.println("Invalid input for year [1 : 5]");
    }

    /**
     * Gets the semester of the student.
     *
     * @return the semester of the student
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Sets the semester of the student.
     *
     * @param semester the semester of the student
     */
    public void setSemester(int semester) {
        if (semester > 0 && semester < 3)
            this.semester = semester;
        else
            System.out.println("Invalid input for semester [1 : 2]");
    }
    // courses setter and getter
    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    /**
     * Returns the Student object as a string.
     *
     * @return a string representation of the student object
     */
    @Override
    public String toString() {
        return "Student {" +
                "name='" + this.getName() + '\'' +
                ", userID='" + this.getUserID() + '\'' +
                ", year='" + this.getYear() + '\'' +
                ", Semester='" + this.getSemester() + '\'' +
                '}';
    }
}

