/* Задача 9
Разработать метод, который находит и возвращает самое большое число в переданном массиве
Отрицательные тоже считаем
*/

package org.lesson1;

public class Task9 {
    public static int findMax(int[] arr) {

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }
}