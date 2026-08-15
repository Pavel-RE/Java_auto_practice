package org.example;

import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.lesson1.Task1;
import org.lesson1.Task2;
import org.lesson1.Task3;
import org.lesson1.Task4;
import org.lesson1.Task5;
import org.lesson1.Task6;
import org.lesson1.Task7;
import org.lesson1.Task8;
import org.lesson1.Task9;
import org.lesson1.Task10;
import org.lesson1.Task11;
import org.lesson1.Task12;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("lesson3Task2")
public class Lesson3Tests2 {

    private static final Random random = new Random();

    // ТЕСТ 1: isEven (10 раз - случайные числа)
    @RepeatedTest(10)
    @Order(1)
    @Tag("Task1")
    void testIsEven() {
        int number = random.nextInt(100) + 1;
        boolean result = Task1.isEven(number);

        System.out.println("---------TEST.TASK1");
        System.out.println("isEven(" + number + ") = " + result);

        boolean expected = (number % 2 == 0);
        assertEquals(expected, result,
                "isEven(" + number + ") должно быть " + expected +
                        ", но получено " + result);
    }


    // ТЕСТ 2: isPositive (10 раз - случайные числа)
    @RepeatedTest(10)
    @Order(2)
    @Tag("Task2")
    void testIsPositive() {
        int number = random.nextInt(100) - 50;
        boolean result = Task2.isPositive(number);

        System.out.println("---------TEST.TASK2");
        System.out.println("isPositive(" + number + ") = " + result);

        boolean expected = (number > 0);
        assertEquals(expected, result,
                "isPositive(" + number + ") должно быть " + expected +
                        ", но получено " + result);
    }


    // ТЕСТ 3: checkAccess (10 раз - случайные возраста)
    @RepeatedTest(10)
    @Order(3)
    @Tag("Task3")
    void testCheckAccess() {
        int age = random.nextInt(100);
        String result = Task3.checkAccess(age);

        System.out.println("---------TEST.TASK3");
        System.out.println("checkAccess(" + age + ") = " + result);

        String expected = (age > 18) ? "Allowed" : "Denied";
        assertEquals(expected, result,
                "checkAccess(" + age + ") должно быть '" + expected +
                        "', но получено '" + result + "'");
    }

    // ТЕСТ 4: getGrade (10 раз - случайные баллы)
    @RepeatedTest(10)
    @Order(4)
    @Tag("Task4")
    void testGetGrade() {
        int score = random.nextInt(102) - 1; // от -1 до 100
        String result = Task4.getGrade(score);

        System.out.println("---------TEST.TASK4");
        System.out.println("getGrade(" + score + ") = " + result);

        String expected;
        if (score >= 81) {
            expected = "A";
        } else if (score >= 61) {
            expected = "B";
        } else if (score >= 41) {
            expected = "C";
        } else if (score >= 21) {
            expected = "D";
        } else if (score >= 0) {
            expected = "E";
        } else {
            expected = "incorrect score";
        }

        assertEquals(expected, result,
                "getGrade(" + score + ") должно быть '" + expected +
                        "', но получено '" + result + "'");
    }


    // ТЕСТ 5: blastOff (10 раз - случайные числа)
    @RepeatedTest(10)
    @Order(5)
    @Tag("Task5")
    void testBlastOff() {
        int number = random.nextInt(10) + 1;
        String result = Task5.blastOff(number);

        System.out.println("---------TEST.TASK5");
        System.out.println("blastOff(" + number + ") = " + result);

        String expected = "";
        for (int i = number; i >= 1; i--) {
            expected += i;
            if (i > 1) {
                expected += ", ";
            }
        }
        expected += " Поехали!";

        assertEquals(expected, result,
                "blastOff(" + number + ") должно быть '" + expected +
                        "', но получено '" + result + "'");
    }


    // ТЕСТ 6: hasBug (10 раз - случайные массивы. Заранее заготавливаем список слов для формирования)
    @RepeatedTest(10)
    @Order(6)
    @Tag("Task6")
    void testHasBug() {
        // Генерируем случайный массив строк
        String[] array = generateRandomStringArray();
        boolean result = Task6.hasBug(array);

        // Проверяем вручную, есть ли "bug" в массиве
        boolean expected = false;
        for (String s : array) {
            if (s.equalsIgnoreCase("bug")) {
                expected = true;
                break;
            }
        }

        System.out.println("---------TEST.TASK6");
        System.out.println("Input: " + Arrays.toString(array));
        System.out.println("Result: " + result);

        assertEquals(expected, result,
                "hasBug(" + Arrays.toString(array) + ") должно быть " + expected +
                        ", но получено " + result);
    }

    // ТЕСТ 7: sumToN (10 раз - случайные числа)
    @RepeatedTest(10)
    @Order(7)
    @Tag("Task7")
    void testSumToN() {
        int number = random.nextInt(21); // от 0 до 20
        int result = Task7.sumToN(number);

        System.out.println("---------TEST.TASK7");
        System.out.println("sumToN(" + number + ") = " + result);

        // Вычисляем ожидаемый результат вручную
        int expected = 0;
        for (int i = 1; i <= number; i++) {
            expected += i;
        }

        assertEquals(expected, result,
                "sumToN(" + number + ") должно быть " + expected +
                        ", но получено " + result);
    }


    // ТЕСТ 8: getEvenInRange (10 раз - случайные диапазоны)
    @RepeatedTest(10)
    @Order(8)
    @Tag("Task8")
    void testGetEvenInRange() {
        int start = random.nextInt(11); // от 0 до 10
        int end = start + random.nextInt(11); // от start до start+10
        String result = Task8.getEvenInRange(start, end);

        System.out.println("---------TEST.TASK8");
        System.out.println("getEvenInRange(" + start + ", " + end + ") = " + result);

        // Вычисляем ожидаемый результат вручную
        StringBuilder expected = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (expected.length() > 0) {
                    expected.append(" ");
                }
                expected.append(i);
            }
        }

        assertEquals(expected.toString(), result,
                "getEvenInRange(" + start + ", " + end + ") должно быть '" + expected +
                        "', но получено '" + result + "'");
    }

    // ТЕСТ 9: findMax (10 раз - случайные массивы)
    @RepeatedTest(10)
    @Order(9)
    @Tag("Task9")
    void testFindMax() {
        int[] array = generateRandomIntArray();
        int result = Task9.findMax(array);

        System.out.println("---------TEST.TASK9");
        System.out.println("findMax(" + Arrays.toString(array) + ") = " + result);

        // Вычисляем ожидаемый результат вручную
        int expected = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > expected) {
                expected = array[i];
            }
        }

        assertEquals(expected, result,
                "findMax(" + Arrays.toString(array) + ") должно быть " + expected +
                        ", но получено " + result);
    }

    // ТЕСТ 10: calcAverage (10 раз - случайные списки)
    @RepeatedTest(10)
    @Order(10)
    @Tag("Task10")
    void testCalcAverage() {
        List<Integer> list = generateRandomIntegerList();
        int result = Task10.calcAverage(list);

        System.out.println("---------TEST.TASK10");
        System.out.println("Input: " + list);
        System.out.println("calcAverage(...) = " + result);

        // Вычисляем ожидаемый результат вручную
        int expected = 0;
        if (!list.isEmpty()) {
            int sum = 0;
            for (int num : list) {
                sum += num;
            }
            expected = sum / list.size();
        }

        assertEquals(expected, result,
                "calcAverage(" + list + ") должно быть " + expected +
                        ", но получено " + result);
    }

    // ТЕСТ 11: reverse (10 раз - случайные массивы)
    @RepeatedTest(10)
    @Order(11)
    @Tag("Task11")
    void testReverse() {
        String[] input = generateRandomStringArray();
        String result = Task11.reverse(input);

        System.out.println("---------TEST.TASK11");
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("reverse(...) = " + result);

        // Вычисляем ожидаемый результат вручную
        String[] reversed = new String[input.length];
        for (int i = 0; i < input.length; i++) {
            reversed[i] = input[input.length - 1 - i];
        }
        String expected = Arrays.toString(reversed);

        assertEquals(expected, result,
                "reverse(" + Arrays.toString(input) + ") должно быть '" + expected +
                        "', но получено '" + result + "'");
    }


    // ТЕСТ 12: removeSpecificName (10 раз - случайные списки)
    @RepeatedTest(10)
    @Order(12)
    @Tag("Task12")
    void testRemoveSpecificName() {
        List<String> input = generateRandomStringList();
        String nameToRemove = input.isEmpty() ? "none" : input.get(random.nextInt(input.size()));
        List<String> result = Task12.removeSpecificName(input, nameToRemove);

        System.out.println("---------TEST.TASK12");
        System.out.println("Input: " + input);
        System.out.println("Remove: '" + nameToRemove + "'");
        System.out.println("Result: " + result);

        // Вычисляем ожидаемый результат вручную
        List<String> expected = new java.util.ArrayList<>();
        for (String name : input) {
            if (!name.equalsIgnoreCase(nameToRemove)) {
                expected.add(name);
            }
        }

        assertEquals(expected, result,
                "removeSpecificName(" + input + ", '" + nameToRemove + "') должно быть " + expected +
                        ", но получено " + result);
    }


    // ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ДЛЯ ГЕНЕРАЦИИ ДАННЫХ

    private static String[] generateRandomStringArray() {
        String[] words = {"apple", "bug", "cat", "dog", "BUG", "hello", "world", "buggy", "test", "code"};
        int size = random.nextInt(6) + 1; // от 1 до 6
        String[] array = new String[size];
        for (int i = 0; i < size; i++) {
            array[i] = words[random.nextInt(words.length)];
        }
        return array;
    }

    private static int[] generateRandomIntArray() {
        int size = random.nextInt(6) + 1; // от 1 до 6
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(101) - 50; // от -50 до 50
        }
        return array;
    }

    private static List<Integer> generateRandomIntegerList() {
        int size = random.nextInt(5) + 1; // от 1 до 5
        List<Integer> list = new java.util.ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(random.nextInt(21)); // от 0 до 20
        }
        return list;
    }

    private static List<String> generateRandomStringList() {
        String[] names = {"Bob", "Lena", "Petrovich", "Pavel", "Alex", "John", "Mary", "Peter", "Anna", "Mike"};
        int size = random.nextInt(5) + 2; // от 2 до 6
        List<String> list = new java.util.ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(names[random.nextInt(names.length)]);
        }
        return list;
    }
}