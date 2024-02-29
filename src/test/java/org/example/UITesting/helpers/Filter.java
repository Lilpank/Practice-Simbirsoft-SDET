package org.example.helpers;

import java.util.ArrayList;
import java.util.regex.*;

public class Filter {
    /**
     * Retrieves the first names from the input string using a regular expression pattern.
     *
     * @param  input  the input string to extract first names from customers
     * @return        a sublist of first names extracted from the input string
     */
    public static Iterable<String> getFirstNamesFromCustomers(String input) {
        Pattern pattern = Pattern.compile("^(\\w+)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(input);

        var result = new ArrayList<String>();
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result.subList(1, result.size());
    }
}
