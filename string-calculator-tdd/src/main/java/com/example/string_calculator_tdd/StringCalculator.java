package com.example.string_calculator_tdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StringCalculator {
    private boolean isValidNumber(int number) {
        return number >= 0 && number <= 1000;
    }

    private void validateNoNegatives(String[] parts) {
        List<String> negatives = new ArrayList<>();
        for (String part : parts) {
            int num = Integer.parseInt(part);
            if (num < 0) {
                negatives.add(part);
            }
        }

        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " +
                    String.join(", ", negatives));
        }
    }
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = "[,\n]";

        if (numbers.startsWith("//")) {
            int delimiterEnd = numbers.indexOf('\n');
            String delimiterPart = numbers.substring(2, delimiterEnd);
            numbers = numbers.substring(delimiterEnd + 1);

            // Handle bracket notation for any length delimiters
            if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
                delimiter = delimiterPart.substring(1, delimiterPart.length() - 1);
                // Escape special regex characters
                delimiter = delimiter.replaceAll("([\\[\\]\\\\*+?.()|^$])", "\\\\$1");
            } else {
                delimiter = delimiterPart.replaceAll("([\\[\\]\\\\*+?.()|^$])", "\\\\$1");
            }
        }

        String[] parts = numbers.split(delimiter);
        validateNoNegatives(parts);

        int sum = 0;
        for (String part : parts) {
            int num = Integer.parseInt(part);
            if (num <= 1000) {
                sum += num;
            }
        }
        return sum;
    }

}
