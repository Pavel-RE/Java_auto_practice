package org.lesson2;

public class Task1 {
    public static boolean isEven(int i) {
        if (i % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
    public static String checkAccess(int i) {
        if (i <= 18) {
            return "Denied";
        } else {
            return "Allowed";
        }
    }
    public static String getGrade(int score) {
        if (score >= 0 && score <= 20) {
            return "E";
        } else if (score >= 21 && score <= 40) {
            return "D";
        } else if (score >= 41 && score <= 60) {
            return "C";
        } else if (score >= 61 && score <= 80) {
            return "B";
        } else if (score >= 81 && score <= 100) {
            return "A";
        } else
            return "incorrect score";
    }

}
