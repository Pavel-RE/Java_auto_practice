package org.lesson1;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Task1: " + Task1.isEven(3));
        System.out.println("Task2: " + Task2.isPositive(0));
        System.out.println("Task3: " + Task3.checkAccess(18));
        System.out.println("Task4: " + Task4.getGrade(100));
        System.out.println("Task5: " + Task5.blastOff(5));
        System.out.println("Task6: " + Task6.hasBug(new String[]{"by", "bu", "buggy","BUG"}));
        System.out.println("Task7: " + Task7.sumToN(3));
        System.out.println("Task8: " + Task8.getEvenInRange(1,5));
        System.out.println("Task9: " + Task9.findMax(new int[]{1,3,5,12,-20}));
        System.out.println("Task10: " + Task10.calcAverage(Arrays.asList(11, 2, 1, 2, 10)));
        System.out.println("Task11: " + Task11.reverse(new String[]{"hello", "my", "world"}));
        List<String> names = Arrays.asList("Bob", "Lena", "Petrovich", "Pavel", "Alex");
        System.out.println("Task12: " + Task12.removeSpecificName(names, "Lena"));
    }
}