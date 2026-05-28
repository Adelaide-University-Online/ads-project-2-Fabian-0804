/**
 * File: DegreePlanner.java
 * Description: Reads degree files and builds the course graph.
 * Author: Fabian DiGrazia
 * Student ID: 2911898
 * Email ID: diyfy006
 * AI Tool Used:
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DegreePlanner {

    private CourseGraph graph;

    public DegreePlanner() {

        graph = new CourseGraph();
    }

    // Reads the degree file and builds the graph
    public void loadFile(String filename) {

        try {

            Scanner scanner = new Scanner(new File(filename));

            // Reads the first line containing all courses
            if (scanner.hasNextLine()) {

                String firstLine = scanner.nextLine();

                String[] allCourses = firstLine.split(",");

                // Creates Course objects and adds them to the graph
                for (String code : allCourses) {

                    Course course = new Course(code.trim());

                    graph.addCourse(course);
                }
            }

            // Reads prerequisite relationships
            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();

                String[] parts = line.split(",");

                Course course = graph.getCourse(parts[0].trim());

                // Remaining values are prerequisites
                for (int i = 1; i < parts.length; i++) {

                    Course prerequisite = graph.getCourse(parts[i].trim());

                    graph.addEdge(prerequisite, course);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found.");
        }
    }

    public CourseGraph getGraph() {

        return graph;
    }
}
