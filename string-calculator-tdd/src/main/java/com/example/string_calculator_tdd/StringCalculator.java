package com.example.string_calculator_tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    // Extract method for better readability
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

            // Handle multiple delimiters in bracket notation (any length)
            if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
                // Extract all delimiters wrapped in brackets
                Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
                Matcher matcher = pattern.matcher(delimiterPart);

                List<String> delimiters = new ArrayList<>();
                while (matcher.find()) {
                    String del = matcher.group(1);
                    // Escape special regex characters for any length delimiter
                    del = del.replaceAll("([\\[\\]\\\\*+?.()|^$])", "\\\\$1");
                    delimiters.add(del);
                }

                if (!delimiters.isEmpty()) {
                    delimiter = String.join("|", delimiters);
                }
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