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
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;

public class DegreePlanner {

    private CourseGraph graph;
    private HashMap<Course, Integer> prerequisiteCounts;

    public DegreePlanner() {

        graph = new CourseGraph();
        prerequisiteCounts = new HashMap<>();
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
                    prerequisiteCounts.put(course, 0);
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

                    int currentCount = prerequisiteCounts.get(course);
                    prerequisiteCounts.put(course, currentCount + 1);
                }
            }

            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found.");
        }
    }

    // Generates the study plan using prerequisite counts
    public ArrayList<ArrayList<Course>> generateStudyPlan(int maxCoursesPerStudyPeriod) {

        ArrayList<ArrayList<Course>> studyPlan = new ArrayList<>();

        // Stores completed courses
        HashSet<Course> completedCourses = new HashSet<>();

        // Continue until all courses are completed
        while (completedCourses.size() < graph.getCourses().size()) {

            ArrayList<Course> availableCourses = new ArrayList<>();

            // Finds courses with no remaining prerequisites
            for (Course course : prerequisiteCounts.keySet()) {

                if (prerequisiteCounts.get(course) == 0
                        && !completedCourses.contains(course)) {

                    availableCourses.add(course);
                }
            }

            // Sorts courses by how many future courses they unlock
            Collections.sort(availableCourses,
                    (course1, course2) -> {
                        int course1Unlocks = graph.getAdjacencyList().get(course1).size();
                        int course2Unlocks = graph.getAdjacencyList().get(course2).size();

                        if (course1Unlocks != course2Unlocks) {
                            return course2Unlocks - course1Unlocks;
                        }

                        // Fallback to alphabetical
                        return course1.getCode().compareTo(course2.getCode());
                    });

            // Stops if no courses are available
            if (availableCourses.isEmpty()) {

                System.out.println("Invalid degree structure.");
                break;
            }

            ArrayList<Course> studyPeriod = new ArrayList<>();

            // Adds courses up to the study period limit
            for (int i = 0;
                 i < availableCourses.size()
                         && i < maxCoursesPerStudyPeriod;
                 i++) {

                Course course = availableCourses.get(i);

                studyPeriod.add(course);
                completedCourses.add(course);
            }

            // Updates prerequisite counts after completing courses
            for (Course completed : studyPeriod) {

                ArrayList<Course> unlockedCourses =
                        graph.getAdjacencyList().get(completed);

                for (Course unlocked : unlockedCourses) {

                    int currentCount = prerequisiteCounts.get(unlocked);

                    prerequisiteCounts.put(unlocked, currentCount - 1);
                }
            }

            studyPlan.add(studyPeriod);
        }

        return studyPlan;
    }

    public CourseGraph getGraph() {

        return graph;
    }

    public HashMap<Course, Integer> getPrerequisiteCounts() {

        return prerequisiteCounts;
    }
}
