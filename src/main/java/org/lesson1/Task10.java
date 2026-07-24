/* Задача 10
Разработать метод, который вычисляет и возвращает среднее арифметическое всех чисел в списке
*/
package org.lesson1;

import java.util.List;

public class Task10 {
    public static int calcAverage(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }

        return sum / list.size();
    }
}