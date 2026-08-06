package org.example;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.lesson2.Task1;

import java.util.Random;
import java.util.stream.IntStream;

public class Lesson2Tests1 {

    private static final Random random = new Random();

    // ТЕСТ 1: isEven (1 раз)
    @Test
    void testIsEven() {
        System.out.println("========================");
        System.out.println("Test method start");

        int number = random.nextInt(100) + 1; // от 1 до 100
        boolean result = Task1.isEven(number);
        System.out.println("isEven(" + number + ") = " + result);

        System.out.println("Test method end");
        System.out.println("========================");
        System.out.println();
    }

    // ТЕСТ 2: checkAccess (20 раз)
    @RepeatedTest(20)
    void testCheckAccess() {
        System.out.println("========================");
        System.out.println("Test method start");

        int age = random.nextInt(100); // от 0 до 99
        String result = Task1.checkAccess(age);
        System.out.println("checkAccess(" + age + ") = " + result);

        System.out.println("Test method end");
        System.out.println("========================");
        System.out.println();
    }

    // ТЕСТ 3: getGrade (параметризованный)
    @ParameterizedTest
    @MethodSource("randomScores")
    void testGetGrade(int score) {
        System.out.println("========================");
        System.out.println("Test method start");

        String result = Task1.getGrade(score);
        System.out.println("getGrade(" + score + ") = " + result);

        System.out.println("Test method end");
        System.out.println("========================");
        System.out.println();
    }


    // Поставщик случайных чисел
    static IntStream randomScores() {
        return random.ints(10, 0, 101); // 10 чисел от 0 до 100 (в условии не было сказано, сколько должно быть чисел в массиве)
    }
}