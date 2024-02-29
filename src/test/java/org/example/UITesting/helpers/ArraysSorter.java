package org.example.UITesting.helpers;

import java.util.Collections;
import java.util.List;

public class ArraysSorter {
    /**
     * Toggles the sort direction of the given list of strings.
     *
     * @param  list  the list of strings to be sorted
     * @return      void
     */
    public static void toggleSortDirection(List<String> list) {
        if (list.isEmpty()) {
            return;
        }

        String firstElement = list.get(0);
        boolean isAscending = firstElement.compareTo(list.get(list.size() - 1)) <= 0;

        if (isAscending) {
            list.sort(Collections.reverseOrder());
        } else {
            Collections.sort(list);
        }
    }
}
