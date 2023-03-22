package Entities;

import java.io.Serializable;
import java.util.Objects;

/**
 * The abstract class representing a user in the system.
 */
public abstract class User implements Serializable {

    // to ensure that the same class is used for serialization and deserialization
    private static final long serialVersionUID = 1L;

    private String name; // The name of the user
    private int userID; // The unique ID of the user
    private String password; // The password of the user
    private boolean hasToken; // A boolean value indicating whether the user has a token or not

    /**
     * Creates a new User object.
     * @param name The name of the user.
     * @param userID The unique ID of the user.
     * @param password The password of the user.
     */
    public User(String name, int userID, String password) {
        this.name = name;
        this.userID = userID;
        this.password = password;
    }

    /**
     * Sets the user ID to the given value.
     * @param userID The new user ID.
     */
    public void setUserID(int userID) {
        if (userID < 1) {
            System.out.println("Error! Please, Enter a valid userID");
        }
        else
            this.userID = userID;
    }

    /**
     * Gets the user ID of the user.
     * @return The user ID of the user.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * Gets the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user to the given value after cleaning any non-alphabetical characters.
     * @param name The new name of the user.
     */
    public void setName(String name) {
        String cleanedName = name.replaceAll("[^a-zA-Z]", "");
        this.name = cleanedName;
    }

    /**
     * Gets the password of the user.
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user to the given value.
     * @param password The new password of the user.
     */
    public void setPassword(String password) {
        if (password.isEmpty() || password.length() < 3 || !((password.matches(".*[a-zA-Z].*") && password.matches(".*\\d.*")))) {
            System.out.println("Error! Please, Enter a valid pass (more than 6 char, includes chars and numbers)");
        } else
            this.password = password;
    }

    /**
     * Returns a hash code value for the object based on the user ID.
     * @return The hash code value for the object.
     */
    public int hashCode() {
        return Objects.hash(userID);
    }

    /**
     * Gets a boolean value indicating whether the user has a token.
     * @return True if the user has a token, false otherwise.
     */
    public boolean hasToken() {
        return hasToken;
    }

    /**
     * Sets the value of the boolean indicating whether the user has a token.
     * @param hasToken The new value of the boolean.
     */
    public void setHasToken(boolean hasToken) {
        this.hasToken = hasToken;
    }

    /**
     * Compares the user to the given object for equality.
     * @param obj The object to compare the user to.
     * @return True if the user is equal to the object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return this.userID == user.userID;
    }

    /**
     * Returns the Student object as a string.
     * @return a string representation of the student object
     */
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
