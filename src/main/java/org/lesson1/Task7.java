/* Задача 7. Разработать метод, который возвращает сумму всех целых чисел от 1 до n
если 0, то 0
если 1, то 1
*/
package org.lesson1;

public class Task7 {
    public static int sumToN(int n) {
        if (n <= 0) {
            return 0;
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + i;
        }
        return sum;
    }
}