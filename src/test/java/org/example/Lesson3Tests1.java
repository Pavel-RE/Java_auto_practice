package org.example;

import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.lesson3.Task1;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("lesson3Task1")
public class Lesson3Tests1 {

    // ТЕСТ 1: isEven
    @Test
    @Order(1)
    void testIsEven() {
        // Четные числа → true
        assertTrue(Task1.isEven(2), "2 должно быть четным");
        assertTrue(Task1.isEven(0), "0 должно быть четным");

        // Нечетные числа → false
        assertFalse(Task1.isEven(3), "3 должно быть нечетным");
        assertFalse(Task1.isEven(-5), "-5 должно быть нечетным");
    }

    // ТЕСТ 2: checkAccess
    @Test
    @Order(2)
    void testCheckAccess() {
        // assertAll выполняет ВСЕ проверки, даже если некоторые упадут
        assertAll("Проверка checkAccess",
                // Возраст 18 и меньше → "Denied" (но мы ожидаем "Prohibited")
                () -> assertEquals("Prohibited", Task1.checkAccess(0), "0 должно быть Prohibited"),
                () -> assertEquals("Prohibited", Task1.checkAccess(17), "17 должно быть Prohibited"),
                () -> assertEquals("Prohibited", Task1.checkAccess(18), "18 должно быть Prohibited"),

                // Возраст больше 18 → "Allowed" (но мы ожидаем "Passed")
                () -> assertEquals("Passed", Task1.checkAccess(19), "19 должно быть Passed"),
                () -> assertEquals("Passed", Task1.checkAccess(25), "25 должно быть Passed"),
                () -> assertEquals("Passed", Task1.checkAccess(100), "100 должно быть Passed"),

                // Отрицательные числа → "Denied" (но мы ожидаем "Prohibited")
                () -> assertEquals("Prohibited", Task1.checkAccess(-5), "-5 должно быть Prohibited")
        );
    }

    // ТЕСТ 3: getGrade
    @Test
    @Order(3)
    void testGetGrade() {
        // Проверка всех границ
        assertEquals("E", Task1.getGrade(0), "0 должно быть E");
        assertEquals("E", Task1.getGrade(20), "20 должно быть E");

        assertEquals("D", Task1.getGrade(21), "21 должно быть D");
        assertEquals("D", Task1.getGrade(40), "40 должно быть D");

        assertEquals("C", Task1.getGrade(41), "41 должно быть C");
        assertEquals("C", Task1.getGrade(60), "60 должно быть C");

        assertEquals("B", Task1.getGrade(61), "61 должно быть B");
        assertEquals("B", Task1.getGrade(80), "80 должно быть B");

        assertEquals("A", Task1.getGrade(81), "81 должно быть A");
        assertEquals("A", Task1.getGrade(100), "100 должно быть A");

        // Некорректные значения
        assertEquals("incorrect score", Task1.getGrade(-1), "-1 должно быть incorrect score");
        assertEquals("incorrect score", Task1.getGrade(101), "101 должно быть incorrect score");
    }

    // ТЕСТ 4: removeSpecificName
    @Test
    @Order(4)
    void testRemoveSpecificName() {
        // Создаем список для теста
        List<String> names = Arrays.asList("Bob", "Lena", "Petrovich", "Pavel", "Alex");

        // Удаляем "Lena"
        List<String> result1 = Task1.removeSpecificName(names, "Lena");
        List<String> expected1 = Arrays.asList("Bob", "Petrovich", "Pavel", "Alex");
        assertEquals(expected1, result1, "После удаления Lena список должен быть [Bob, Petrovich, Pavel, Alex]");

        // Удаляем "Bob"
        List<String> result2 = Task1.removeSpecificName(names, "Bob");
        List<String> expected2 = Arrays.asList("Lena", "Petrovich", "Pavel", "Alex");
        assertEquals(expected2, result2, "После удаления Bob список должен быть [Lena, Petrovich, Pavel, Alex]");

        // Удаляем несуществующее имя
        List<String> result3 = Task1.removeSpecificName(names, "Noname");
        assertEquals(names, result3, "Если имя не найдено, список не должен измениться");

        // Проверка игнорирования регистра
        List<String> result4 = Task1.removeSpecificName(names, "lena");
        assertEquals(expected1, result4, "Должен игнорировать регистр: 'lena' должно удалить 'Lena'");

        List<String> result5 = Task1.removeSpecificName(names, "BOB");
        List<String> expected5 = Arrays.asList("Lena", "Petrovich", "Pavel", "Alex");
        assertEquals(expected5, result5, "Должен игнорировать регистр: 'BOB' должно удалить 'Bob'");
    }
}
