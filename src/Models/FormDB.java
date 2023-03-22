package Models;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormDB implements IText {
    @Override
    public void printTxt(String title) {
        // Create a StringBuilder to store the text.
        StringBuilder text = new StringBuilder();

        // Create a Pattern to search for the title in the text file.
        Pattern pattern = Pattern.compile("Title : " + title);
        Matcher matcher;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(instructFile))) {
            // Read the first line of the file.
            String line = bufferedReader.readLine();

            // Loop through each line of the file until the end of text is found.
            while (line != null && !(line.toLowerCase().contains("end of text"))) {
                // Check if the current line matches the title pattern.
                matcher = pattern.matcher(line);
                if (matcher.find()) {
                    // If the title is found, start appending lines to the text StringBuilder until the end of text is found.
                    line = bufferedReader.readLine();
                    while (line != null && !(line.toLowerCase().contains("end of text"))) {
                        text.append(line + "\n");
                        line = bufferedReader.readLine();
                    }
                } else {
                    // If the current line doesn't match the title pattern, move to the next line.
                    line = bufferedReader.readLine();
                }
            }
            // Print the text to the console.
            System.out.println(text);
        } catch (IOException e) {
            // If an error occurs while reading the file, print an error message and the stack trace.
            System.out.println("Error occurred while reading from the file.");
            e.printStackTrace();
        }
    }

    @Override
    public void addText(String title, String text) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(instructFile, true))) {
            // Open the file for writing and append the title and text to the end of the file.
            bufferedWriter.write("Title : " + title + "\n");
            bufferedWriter.write(text + "\n");
        } catch (IOException e) {
            // If an error occurs while writing to the file, print the stack trace.
            e.printStackTrace();
        }
    }
}