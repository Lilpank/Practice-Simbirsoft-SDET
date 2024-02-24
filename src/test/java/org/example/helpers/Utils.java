package org.example.helpers;

public class Utils {
    public static String joinIterableWithDelimiter(Iterable<String> iterable, String delimiter) {
        return String.join(delimiter, iterable);
    }
}
