/* Задача 8. Разработать метод, который принимает границы диапазона и возвращает строку,
состоящую только из четных чисел внутри этого промежутка
*/
package org.lesson1;

import java.util.Arrays;

public class Task8 {
    public static String getEvenInRange(int start, int end) {
        int count = 0;
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                count++;
            }
        }

        int[] ar = new int[count];
        int index = 0;

        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                ar[index] = i;
                index++;
            }
        }

        return Arrays.toString(ar);
    }
}