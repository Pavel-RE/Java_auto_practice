/* Задача 11
Разработать метод, возвращающий новый массив, в котором элементы исходного массива
расположены в обратном порядке
*/
package org.lesson1;

import java.util.Arrays;

public class Task11 {
    public static String reverse(String[] arr) {
        String[] rra = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            rra[i] = arr[arr.length - 1 - i];
        }
        return Arrays.toString(rra);
    }
}