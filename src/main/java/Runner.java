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

public class Runner {
    
    public static void main(String[] args) {

        DegreePlanner planner = new DegreePlanner();

        planner.loadFile("XBDA.txt");

        ArrayList<ArrayList<Course>> studyPlan =
                planner.generateStudyPlan(2);

        // Displays the study plan
        for (int i = 0; i < studyPlan.size(); i++) {

            System.out.println("Study Period " + (i + 1) + ": "
                    + studyPlan.get(i));
        }
        
    }
    
}
