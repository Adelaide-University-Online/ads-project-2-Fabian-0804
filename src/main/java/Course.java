/**
 * File: Course.java
 * Description: Represents a course object used in the degree graph.
 * Author: Fabian DiGrazia
 * Student ID: 2911898
 * Email ID: diyfy006
 * AI Tool Used:
 * This is my own work as defined by
 *    the University's Academic Integrity Policy.
 **/

import java.util.Objects;

public class Course {

    // Stores the course code
    private String code;

    public Course(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }

    @Override
    public boolean equals(Object obj) {

        // Checks if both objects are the same
        if (this == obj) {
            return true;
        }

        // Checks if the object is null or a different class
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Course course = (Course) obj;

        // Compares course codes
        return Objects.equals(code, course.code);
    }

    @Override
    public int hashCode() {

        // Generates hash code using the course code
        return Objects.hash(code);
    }
}
