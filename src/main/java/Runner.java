/**
 * File: Runner.java
 * Description: Runs the degree planning program.
 * Author: Fabian DiGrazia
 * Student ID: 2911898
 * Email ID: diyfy006
 * AI Tool Used:
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter degree file name: ");
        String filename = scanner.nextLine();

        System.out.print("Enter maximum courses per study period: ");
        int maxCourses = scanner.nextInt();

        DegreePlanner planner = new DegreePlanner();

        planner.loadFile(filename);

        System.out.println("\nGraph adjacency list:");
        planner.getGraph().displayGraph();

        ArrayList<ArrayList<Course>> studyPlan =
                planner.generateStudyPlan(maxCourses);

        // Displays the study plan
        for (int i = 0; i < studyPlan.size(); i++) {

            System.out.println("Study Period " + (i + 1) + ": "
                    + studyPlan.get(i));
        }

        scanner.close();
        
    }
    
}
