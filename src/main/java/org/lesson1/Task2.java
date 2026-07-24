//Задача 2. Разработать метод, который проверяет знак через тернарный оператор:
// возвращает true, если число больше или равно 0, и false, если меньше

package org.lesson1;

public class Task2 {
    public static boolean isPositive(int i) {
        return (i >= 0) ? true : false;
    }
}