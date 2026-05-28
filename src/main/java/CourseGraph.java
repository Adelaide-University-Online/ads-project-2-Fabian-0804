/**
 * File: CourseGraph.java
 * Description: Stores the graph structure and prerequisite connections between courses.
 * Author: Fabian DiGrazia
 * Student ID: 2911898
 * Email ID: diyfy006
 * AI Tool Used:
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class CourseGraph {

    // Stores the adjacency list for the graph
    private HashMap<Course, ArrayList<Course>> adjacencyList;

    // Stores courses by their code for quick access
    private HashMap<String, Course> courses;

    public CourseGraph() {

        adjacencyList = new HashMap<>();
        courses = new HashMap<>();
    }

    // Adds a course vertex to the graph
    public void addCourse(Course course) {

        if (!adjacencyList.containsKey(course)) {

            adjacencyList.put(course, new ArrayList<>());
            courses.put(course.getCode(), course);
        }
    }

    // Adds a directed edge between two courses
    public void addEdge(Course prerequisite, Course course) {

        adjacencyList.get(prerequisite).add(course);
    }

    public Course getCourse(String code) {

        return courses.get(code);
    }

    public HashMap<Course, ArrayList<Course>> getAdjacencyList() {

        return adjacencyList;
    }

    public HashMap<String, Course> getCourses() {

        return courses;
    }

    // Displays the graph as an adjacency list
    public void displayGraph() {

        ArrayList<Course> sortedCourses =
                new ArrayList<>(adjacencyList.keySet());

        // Sorts courses alphabetically
        Collections.sort(sortedCourses,
                (course1, course2) ->
                        course1.getCode().compareTo(course2.getCode()));

        for (Course course : sortedCourses) {

            ArrayList<Course> connectedCourses =
                    new ArrayList<>(adjacencyList.get(course));

            // Sorts connected courses alphabetically
            Collections.sort(connectedCourses,
                    (course1, course2) ->
                            course1.getCode().compareTo(course2.getCode()));

            System.out.println(course + " -> "
                    + connectedCourses);
        }
    }
}
