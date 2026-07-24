/* Задача 6. Разработать метод, который принимает массив строк и возвращает true,
если хотя бы одна строка равна Bug
*/
package org.lesson1;

public class Task6 {
    public static boolean hasBug(String[] messages) {
        for (int i = 0; i < messages.length; i++) {
            if (messages[i].equalsIgnoreCase("bug")) {
                return true;
            }
        }
        return false;

    }
}