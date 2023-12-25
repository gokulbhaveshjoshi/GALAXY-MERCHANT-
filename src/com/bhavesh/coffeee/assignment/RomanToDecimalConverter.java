package com.bhavesh.coffeee.assignment;

import java.util.HashMap;

public class RomanToDecimalConverter {
    private static final HashMap<Character, Integer> romanNumerals = new HashMap<>();

    static {
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);
        romanNumerals.put('C', 100);
        romanNumerals.put('D', 500);
        romanNumerals.put('M', 1000);
    }

    public static int convertToDecimal(String romanNumeral) {
        int decimalNumber = 0;
        int prevValue = 0;
        int consecutiveCount = 1;  // Count consecutive repeated characters

        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            int value = romanNumerals.get(romanNumeral.charAt(i));

            if (value == prevValue) {
                consecutiveCount++;
                // Check for more than three consecutive occurrences
                if (consecutiveCount > 3) {
                    return -1;  // Invalid sequence
                }
            } else {
                consecutiveCount = 1;
            }

            if (value < prevValue) {
                decimalNumber -= value;
            } else {
                decimalNumber += value;
            }
            prevValue = value;
        }

        return decimalNumber;
    }
}
