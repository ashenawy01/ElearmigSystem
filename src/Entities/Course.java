package Entities;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    String name; // Course name
    String courseID; // Course ID
    int pID; // professor ID
    String imgSrc;  // The image src of the course

    // Listing the weeks of each course in a tree map for higher performance in sorting weeks according to their values
    TreeMap<Integer, Week> weeks;

    // constructor that takes and initialize the whole inputs
    public Course(String name, String courseID, int pID, String imgSrc) {
        this.name = name;
        this.pID = pID;
        this.imgSrc = imgSrc;
        this.courseID = courseID;
        this.weeks = new TreeMap<>();
    }

    // setters & getters
    public String getCourseID() {
        return courseID;
    }
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    public int getPID() {
        return pID;
    }
    public void setPID(int pID) {
        this.pID = pID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        String cleanedName = name.replaceAll("[^a-zA-Z]", "");
        this.name = cleanedName;
    }
    public String getImgSrc() {
        return imgSrc;
    }
    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public TreeMap<Integer, Week> getWeeks() {
        return weeks;
    }

    public void setWeeks(TreeMap<Integer, Week> weeks) {
        this.weeks = weeks;
    }

    // Add week to the week treemap which takes the week number as a key and the week as a value
    public void addWeek (Week week) {
        weeks.put(week.getWeekNum(), week);
        System.out.println("Entities.Week : " + week.getWeekNum() + " is added successfully");
    }

    // Remove a week from the weeks treemap by its number
    public void removeWeek(int num) {
        if(weeks.remove(num) != null) { // return null if there is no week with the given number
            System.out.println("The week No: " + num + " was deleted successfully");
        } else {
            System.out.printf("Error 404 - week No : %d is not found\n", num);
        }
    }



    // hashcode the courseID coz it is supposed to be unique it can be used in compression with equals()
    @Override
    public int hashCode() {
        return Objects.hash(this.courseID);
    }

    // compare any two Courses according to their IDs
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj.getClass() != this.getClass() || obj == null) return false;
        return this.courseID == ((Course) obj).courseID;
    }

    @Override
    public String toString() { // print the object with (name, courseID, imgSrc)
        return
            "Course Name : " + this.getName() + "\n" +
            "Course ID : " + this.courseID + "\n" +
            "Course Image course : " + this.imgSrc + "\n";
    }
}
