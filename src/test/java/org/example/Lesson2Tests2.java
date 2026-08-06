package org.example;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Lesson2Tests2 {

    private static final Random random = new Random();

    // ТЕСТ 1: isEven (1 раз - случайный выбор)
    @Test
    @Order(1)
    void testIsEven() {
        int number = random.nextInt(100) + 1; // от 1 до 100
        boolean result = Task1.isEven(number);

        System.out.println("---------TEST.TASK1");
        System.out.println("isEven(" + number + ") = " + result);

        // Проверка: четное число → true, нечетное → false
        boolean expected = (number % 2 == 0);
        if (result == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 2: isPositive (2 раза - случайный выбор)
    @RepeatedTest(2)
    @Order(2)
    void testIsPositive() {
        int i = random.nextInt(100) - 50; // от -50 до 49
        boolean result = Task2.isPositive(i);

        System.out.println("---------TEST.TASK2");
        System.out.println("isPositive(" + i + ") = " + result);

        // Проверка: положительное число → true, отрицательное или ноль → false
        boolean expected = (i > 0);
        if (result == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 3: CheckAccess (Параметризованный)
    @ParameterizedTest
    @ValueSource(ints = {0, 17, 18, 19, 50})
    @Order(3)
    void testCheckAccess(int age) {
        String result = Task3.checkAccess(age);

        System.out.println("---------TEST.TASK3");
        System.out.println("checkAccess(" + age + ") = " + result);

        // Проверка: age > 18 → "Allowed", иначе → "Denied"
        String expected = (age > 18) ? "Allowed" : "Denied";
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 4: GetGrade (Параметризованный)
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 20, 21, 40, 41, 60, 61, 80, 81, 100, 101})
    @Order(4)
    void testGetGrade(int score) {
        String result = Task4.getGrade(score);

        System.out.println("---------TEST.TASK4");
        System.out.println("getGrade(" + score + ") = " + result);

        // Проверка: определяем ожидаемую оценку по баллам
        String expected;
        if (score >= 81) {
            expected = "A";
        } else if (score >= 61) {
            expected = "B";
        } else if (score >= 41) {
            expected = "C";
        } else if (score >= 21) {
            expected = "D";
        } else {
            expected = "E";
        }

        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 5: blastOff (1 раз - случайный выбор)
    @Test
    @Order(5)
    void testBlastOff() {
        int number = random.nextInt(10) + 1; // от 1 до 10
        String result = Task5.blastOff(number);

        System.out.println("---------TEST.TASK5");
        System.out.println("blastOff(" + number + ") = " + result);


        // Проверка: числа от start до 1 через запятую + " Поехали!"
        String expected = "";
        for (int i = number; i >= 1; i--) {
            expected += i;
            if (i > 1) {
                expected += ", ";
            }
        }
        expected += " Поехали!";

        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED"); //Тест просто так не провалится, но можно убрать запятые в ожидаемом результате
        }
        System.out.println();


    }
    // ТЕСТ 6: HasBug (csv набор)
    @ParameterizedTest
    @CsvSource({
            "'by,bu,buggy,BUG', true",
            "'hello,world', false",
            "'bug,test,code', true",
            "'one,two,three', false"
    })
    @Order(6)
    void testHasBug(String csvLine, boolean expectedResult) {
        String[] array = csvLine.isEmpty() ? new String[0] : csvLine.split(",");

        boolean result = Task6.hasBug(array);
        System.out.println("---------TEST.TASK6");
        System.out.println("Input: " + java.util.Arrays.toString(array));
        System.out.println("Result: " + result);

        if (result == expectedResult) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 7: sumToN (Параметризованный тест)
    @ParameterizedTest
    @MethodSource("randomScores")
    @Order(7)
    void testSumToN(int score) {
        int result = Task7.sumToN(score);

        System.out.println("---------TEST.TASK7");
        System.out.println("sumToN(" + score + ") = " + result);

        // Проверка
        int expected = 15;

        if (result == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 8: getEvenInRange (Простой тест)
    @Test
    @Order(8)
    void testGetEvenInRange() {
        int number1 = 1;
        int number2 = 5;
        String result = Task8.getEvenInRange(number1, number2);

        System.out.println("---------TEST.TASK8");
        System.out.println("getEvenInRange(" + number1 + ", "+ number2 + ") = " + result);

        // Проверка: четные числа от 1 до 5 → "2 4"
        String expected = "2 4";

        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();

    }


    // ТЕСТ 9: findMax (Простой тест)
    @Test
    @Order(9)
    void testFindMax() {
        int[] array = {1, 3, 5, 12, -20};
        int expected = 12;

        int result = Task9.findMax(array);

        System.out.println("---------TEST.TASK9");
        System.out.println("findMax(" + java.util.Arrays.toString(array) + ") = " + result);

        if (result == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 10: calcAverage (3 раза с разными списками)
    @RepeatedTest(3)
    @Order(10)
    void testCalcAverage(RepetitionInfo repetitionInfo) {
        List<Integer> list;
        int expected;

        // Определяем список и ожидаемый результат в зависимости от повтора
        int repetition = repetitionInfo.getCurrentRepetition();

        if (repetition == 1) {
            list = Arrays.asList(2, 4, 6, 8);
            expected = 5; // (2+4+6+8) / 4 = 20/4 = 5
        } else if (repetition == 2) {
            list = Arrays.asList(10, 20, 30, 40);
            expected = 25; // (10+20+30+40) / 4 = 100/4 = 25
        } else {
            list = Arrays.asList(1, 2, 3, 4, 5);
            expected = 3; // (1+2+3+4+5) / 5 = 15/5 = 3
        }

        int result = Task10.calcAverage(list);

        System.out.println("---------TEST.TASK10");
        System.out.println("Input: " + list);
        System.out.println("calcAverage(...) = " + result);

        if (result == expected) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }
    // ТЕСТ 11: reverse (Простой тест)
    @Test
    @Order(11)
    void testReverse() {
        // Создаем массив строк
        String[] input = {"hello", "my", "world"};

        // Вызываем метод
        String result = Task11.reverse(input);

        // Ожидаемый результат: ["world", "my", "hello"] в виде строки
        String expected = "[world, my, hello]";

        System.out.println("---------TEST.TASK11");
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("reverse(...) = " + result);
        System.out.println("Expected: " + expected);

        // Проверяем
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // ТЕСТ 12: removeSpecificName (2 раза)
    @RepeatedTest(2)
    @Order(12)
    void testRemoveSpecificName(RepetitionInfo repetitionInfo) {
        List<String> input;
        String nameToRemove;
        List<String> expected;

        int repetition = repetitionInfo.getCurrentRepetition();

        if (repetition == 1) {
            // Первый запуск: удаляем "Lena"
            input = Arrays.asList("Bob", "Lena", "Petrovich", "Pavel", "Alex");
            nameToRemove = "Lena";
            expected = Arrays.asList("Bob", "Petrovich", "Pavel", "Alex");
        } else {
            // Второй запуск: удаляем "Bob"
            input = Arrays.asList("Bob", "Lena", "Petrovich", "Pavel", "Alex");
            nameToRemove = "Bob";
            expected = Arrays.asList("Lena", "Petrovich", "Pavel", "Alex");
        }

        // Вызываем метод
        List<String> result = Task12.removeSpecificName(input, nameToRemove);

        System.out.println("---------TEST.TASK12 (Run #" + repetition + ")");
        System.out.println("Input: " + input);
        System.out.println("Remove: '" + nameToRemove + "'");
        System.out.println("Result: " + result);
        System.out.println("Expected: " + expected);

        // Проверяем
        if (result.equals(expected)) {
            System.out.println("TEST PASSED");
        } else {
            System.out.println("TEST FAILED");
        }
        System.out.println();
    }

    // Поставщик случайных чисел
    static IntStream randomScores() {
        return random.ints(10, 0, 101); // 10 чисел от 0 до 100 (в условии не было сказано, сколько должно быть чисел в массиве)
    }

}