package org.example.UITesting.helpers;

public class Utils {
    /**
     * Joins the elements of the given iterable with the specified delimiter.
     *
     * @param  iterable   the iterable whose elements are to be joined
     * @param  delimiter  the delimiter to be used between the elements
     * @return            the resulting string after joining the elements with the delimiter
     */
    public static String joinIterableWithDelimiter(Iterable<String> iterable, String delimiter) {
        return String.join(delimiter, iterable);
    }
}
