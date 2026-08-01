//Задача 3. Разработать метод, который возвращает Allowed, если age больше 18 (включительно)
// иначе - Denied

package org.lesson1;

public class Task3 {
    public static String checkAccess(int i) {
        if (i <= 18) {
            return "Denied";
        } else {
            return "Allowed";
        }
    }
}