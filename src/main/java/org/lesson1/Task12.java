/* Задача 12
Разработать метод, принимающий список и имя, которое нужно исключить.
Возвращает новый список, не содержащий указанного имени
*/
package org.lesson1;

import java.util.ArrayList;
import java.util.List;

public class Task12 {
    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {

        List<String> remove = new ArrayList<>();

        for (String name : list) {
            if (!name.equalsIgnoreCase(nameToRemove)) {
                remove.add(name);
            }
        }

        return remove;
    }
}