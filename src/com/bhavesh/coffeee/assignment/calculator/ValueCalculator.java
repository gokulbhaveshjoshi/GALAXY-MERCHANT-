package com.bhavesh.coffeee.assignment.calculator;

import com.bhavesh.coffeee.assignment.Literals;
import com.bhavesh.coffeee.assignment.RomanToDecimalConverter;

import java.util.HashMap;
import java.util.Objects;

public class ValueCalculator {
    public String calculateQueryValue(String query, HashMap<String, String> romanNumerals) {
        String[] words = query.split(" ");


        if (query.startsWith("how much is")) {
            return calculateHowMuchQuery(words, romanNumerals);
        }
        if (query.startsWith("how many Credits is")) {
            return calculateHowManyCreditsQuery(words, romanNumerals);
        }
        if (query.toLowerCase().startsWith("does") && query.contains("have more Credits than") || query.contains("has more Credits than")) {
            return calculateComparisonQuery(words, romanNumerals, "more");
        }
        if (query.toLowerCase().startsWith("does") && (query.contains("have less Credits than") || query.contains("has less Credits than")) ) {
            return calculateComparisonQuery(words, romanNumerals, "less");
        }
        if (query.toLowerCase().startsWith("is") && query.contains("larger than")) {
            return calculateComparisonQuery(words, romanNumerals, "larger");
        }
        if (query.toLowerCase().startsWith("is") && query.contains("smaller than")) {
            return calculateComparisonQuery(words, romanNumerals, "smaller");
        }

        return Literals.differentSting;
    }

    public String calculateHowMuchQuery(String[] words, HashMap<String, String> romanNumerals) {
        StringBuilder romanNumeral = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (int i = 3; i < words.length - 1; i++) {
            if (romanNumerals.containsKey(words[i])) {
                romanNumeral.append(romanNumerals.get(words[i]));
                query.append(words[i]).append(" ");
            } else {
                return Literals.differentSting;
            }
        }

        int decimalValue = RomanToDecimalConverter.convertToDecimal(romanNumeral.toString());
        if (decimalValue == -1) {
            return Literals.wrongString;
        }

        return query.toString().trim() + " is " + decimalValue;
    }

    public String calculateHowManyCreditsQuery(String[] words, HashMap<String, String> romanNumerals) {

        StringBuilder romanNumeral = new StringBuilder();
        StringBuilder query = new StringBuilder();


        for (int i = 4; i < words.length - 2; i++) {
            if (romanNumerals.containsKey(words[i])) {
                romanNumeral.append(romanNumerals.get(words[i]));
                query.append(words[i]).append(" ");
            } else {
                return Literals.differentSting;
            }
        }

        int decimalValue = RomanToDecimalConverter.convertToDecimal(romanNumeral.toString());
        if (decimalValue == -1) {
            return Literals.wrongString;
        }
        String metal = romanNumerals.getOrDefault(words[words.length - 2], "0");
        query.append(words[words.length - 2]).append(" ");
        double metalCredits = Double.parseDouble(metal);

        if (words[2].equals("Credits")) {


            double totalCredits = metalCredits * (decimalValue);
            return  query.toString().trim() +  " is " + totalCredits + " Credits";
        } else {
            return Literals.differentSting;
        }
    }

    private String calculateComparisonQuery(String[] words, HashMap<String, String> romanNumerals, String comparisonType) {
        StringBuilder sequence1 = new StringBuilder();
        StringBuilder sequence2 = new StringBuilder();

        boolean foundThan = false;

        double decimalValue1 = 1;
        double decimalValue2 =  1;

        for (String word : words) {
            if (word.equals("than")) {
                foundThan = true;
                continue;
            }
            String value = romanNumerals.getOrDefault(word, "");


            if (!foundThan) {
                if (isNumeric(value)) {
                    decimalValue1 = Double.parseDouble(value);
                } else {
                    sequence1.append(romanNumerals.getOrDefault(word, ""));
                }

            } else {
                if (isNumeric(value)) {
                    decimalValue2 = Double.parseDouble(value);
                } else {
                    sequence2.append(romanNumerals.getOrDefault(word, ""));
                }
            }

        }


        decimalValue1 *= RomanToDecimalConverter.convertToDecimal(sequence1.toString());
        decimalValue2 *= RomanToDecimalConverter.convertToDecimal(sequence2.toString());

        if (decimalValue1 == -1 || decimalValue2 == -1) {
            return Literals.wrongString;
        }

        return switch (comparisonType) {
            case "more", "larger" -> getOutput(words, decimalValue1 > decimalValue2);
            case "less", "smaller" -> getOutput(words, decimalValue1 < decimalValue2);
            default -> Literals.differentSting;
        };

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private  String getOutput(String[] input, boolean isNotReplace) {
        StringBuilder sb = new StringBuilder();
        boolean startsWithIs = false;
        for (String str: input) {
            if (Objects.equals(str, "Is") || Objects.equals(str, "Does") || Objects.equals(str, "?")) {
                startsWithIs = Objects.equals(str, "Is");
                continue;
            }
            if (isNotReplace) {
                if (startsWithIs) {
                    sb.append("is ").append(str).append(" ");
                } else {
                    sb.append(str).append(" ");
                }
            } else {
                switch (str) {
                    case "less" -> {
                        if (startsWithIs) {
                            sb.append("is more ");
                        } else {
                            sb.append("more ");
                        }
                    }
                    case "more" -> {
                        if (startsWithIs) {
                            sb.append(" is less ");
                        } else {
                            sb.append("less ");
                        }
                    }
                    case "larger" -> {
                        if (startsWithIs) {
                            sb.append("is smaller ");
                        } else {
                            sb.append("smaller ");
                        }
                    }
                    case "smaller" -> {
                        if (startsWithIs) {
                            sb.append("is larger ");
                        } else {
                            sb.append("larger ");
                        }
                    }
                    case null, default -> sb.append(str).append(" ");
                }
            }

        }
        return sb.toString();
    }
}
