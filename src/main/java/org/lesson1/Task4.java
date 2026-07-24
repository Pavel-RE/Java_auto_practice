/* Задача 4. Разработать метод, который преобразует баллы (0-100) в символ оценки:
0-20:   E
21-40:  D
41-60:  C
61-80:  B
81-100: A
*/
package org.lesson1;

public class Task4 {
    public static String getGrade(int score) {
        if (score >= 0 && score <= 20) {
            return "E";
        } else if (score >= 21 && score <= 40) {
            return "D";
        } else if (score >= 41 && score <= 60) {
            return "C";
        } else if (score >= 61 && score <= 80) {
            return "B";
        } else if (score >= 81 && score <= 100) {
            return "A";
        } else
            return "incorrect score";
    }
}