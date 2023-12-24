import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ValueCalculator {
    public String calculateQueryValue(String query, HashMap<String, String> romanNumerals) {
        String[] words = query.split(" ");
        System.out.println(query);
        // Check for specific query types
        if (query.startsWith("HOW MUCH IS")) {
            return calculateHowMuchQuery(words, romanNumerals);
        } else if (query.startsWith("HOW MANY CREDITS IS")) {
            return calculateHowManyCreditsQuery(words, romanNumerals);
        } else if (query.startsWith("DOES") && query.contains("HAVE MORE CREDITS THAN")) {
            return calculateComparisonQuery(words, romanNumerals);
        } else if (query.startsWith("IS") && query.contains("LARGER THAN")) {
            return calculateComparisonQuery(words, romanNumerals);
        } else if (query.startsWith("IS") && query.contains("SMALLER THAN")) {
            return calculateComparisonQuery(words, romanNumerals);
        }

        return "I have no idea what you are talking about";
    }

    private String calculateHowMuchQuery(String[] words, HashMap<String, String> romanNumerals) {
        StringBuilder romanNumeral = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (int i = 3; i < words.length - 1; i++) {
            if (romanNumerals.containsKey(words[i])) {
                romanNumeral.append(romanNumerals.get(words[i]));
                query.append(words[i]).append(" ");
            } else {
                return "I have no idea what you are talking about";
            }
        }

        int decimalValue = RomanToDecimalConverter.convertToDecimal(romanNumeral.toString());
        if (decimalValue == -1) {
            return "Requested number is in invalid format";
        }

        return query.toString().trim() + " is " + decimalValue;
    }

    private String calculateHowManyCreditsQuery(String[] words, HashMap<String, String> romanNumerals) {

        StringBuilder romanNumeral = new StringBuilder();
        StringBuilder query = new StringBuilder();

        // Combine Roman numerals to get the value
        for (int i = 4; i < words.length - 2; i++) {
            if (romanNumerals.containsKey(words[i])) {
                romanNumeral.append(romanNumerals.get(words[i]));
                query.append(words[i]).append(" ");
            } else {
                return "I have no idea what you are talking about";
            }
        }

        int decimalValue = RomanToDecimalConverter.convertToDecimal(romanNumeral.toString());
        if (decimalValue == -1) {
            return "Requested number is in an invalid format";
        }
        String metal = romanNumerals.getOrDefault(words[words.length - 2], "0");
        query.append(words[words.length - 2]).append(" ");
        double metalCredits = Double.parseDouble(metal);
        // Look up Credits for the metal

        if (words[2].equals("CREDITS")) {
            // Adjust this logic based on your actual data structure for storing metal values
            // The following line is just a placeholder


            // Calculate Credits based on the decimal value
            double totalCredits = metalCredits * (decimalValue);
            return  query +  " is " + totalCredits + " Credits";
        } else {
            return "I have no idea what you are talking about";
        }
    }

    private String calculateComparisonQuery(String[] words, HashMap<String, String> romanNumerals) {
        // Implementation for handling comparison queries
        // ...
        System.out.println(romanNumerals.entrySet());
        return "Not implemented yet";
    }
}
