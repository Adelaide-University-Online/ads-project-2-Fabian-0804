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

        for (Course course : adjacencyList.keySet()) {

            System.out.println(course + " -> " + adjacencyList.get(course));
        }
    }
}
