/* Задача 5 Разработать метод, который принимает стартовое число (например, 5) и возвращает строку
со всеми числами до 1 и словом "Поехали!" в конце (например, "5, 4, 3, 2, 1 Поехали!")
*/
package org.lesson1;

public class Task5 {
    public static String blastOff(int start) {
        String answer = "";
        for (int i = start; i >= 1; i--) {
            answer = answer + i;
            if (i > 1) {
                answer = answer + ", ";
            }
        }
        return answer + " Поехали!";
    }
}