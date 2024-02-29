package org.example.uiTesting.helpers;

import java.util.ArrayList;
import java.util.Random;

public class Generator {

    private static int module26(int number) {
        return number % 26;
    }

    /**
     * Generates a random 10-digit post code.
     *
     * @return         	the generated random 10-digit post code
     */
    public static String generatePostCode() {
        Random random = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            number.append(random.nextInt(10));
        }
        return number.toString();
    }
    /**
     * A function to generate a first name from a given number.
     *
     * @param  number   the input number
     * @return          the generated first name
     */
    public static String generateFirstName(String number) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 2; i <= number.length(); i += 2) {
            result.add(convertToLetters(number.substring(i - 2, i)));
        }
        return String.join("", result);
    }

    /**
     * Converts the input number to a letter.
     *
     * @param  number  the number to be converted
     * @return         the converted letter
     */
    private static String convertToLetters(String number) {
        StringBuilder letters = new StringBuilder();
        int digit = module26(Integer.parseInt(number));
        char letter = (char) (digit + 'A');
        letters.append(letter);
        return letters.toString().toLowerCase();
    }
}
