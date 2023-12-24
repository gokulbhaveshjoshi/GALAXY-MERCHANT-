import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QueryProcessor {
    private final HashMap<String, String> romanNumerals;
    private final ValueCalculator calculator;

    public QueryProcessor() {
        romanNumerals = new HashMap<>();
        calculator = new ValueCalculator();
    }

    public void processQueries() {
        try {
            FileInputStream fileInputStream = new FileInputStream("InputFile");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            String line;
            List<String> queries = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                queries.add(line.toUpperCase());
            }
            processQueries(queries);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processQueries(List<String> queries) {
        for (String query : queries) {
            processInput(query);
        }
    }

    private void processInput(String input) {

        String[] parts = input.split(" IS ");

        if (parts.length == 2 && (!input.endsWith("?"))) {
            if (parts[1].endsWith("CREDITS")) {
                StringBuilder sb = new StringBuilder();
                String[] part = parts[0].split(" ");

                for (int i = 0; i < part.length - 1; i++) {
                    String romanValue = romanNumerals.getOrDefault(part[i], "");
                    sb.append(romanValue);
                }
                int xMetal = RomanToDecimalConverter.convertToDecimal(sb.toString());
                int totalCredits = Integer.parseInt( parts[1].split(" ")[0]);
                int metalCost = totalCredits / xMetal;
                romanNumerals.put(part[part.length - 1].trim(), String.valueOf(metalCost));
            } else {
                romanNumerals.put(parts[0].trim(), parts[1].trim());
            }
            //System.out.println(romanNumerals.entrySet());
        } else {
            System.out.println("Question");
            String result = calculator.calculateQueryValue(input, romanNumerals);
            System.out.println(result);
        }

        /*
        // handle input "glob is I"
        if (parts.length == 2 && (! input.endsWith("?")) && (! input.endsWith("Credits")) ) {
            // Handle statements like "glob is I" and metal value assignments
            romanNumerals.put(parts[0].trim(), parts[1].trim());
        } else if (parts[1].endsWith("Credit")) {
            String[] metals = parts[0].split(" ");
            StringBuilder roman = new StringBuilder();
            for (int i = 0; i < metals.length - 1; i++) {
                if (romanNumerals.containsKey(metals[i])) {
                    roman.append(romanNumerals.get(metals[i]));
                }
            }
            int decimalValue = RomanToDecimalConverter.convertToDecimal(roman.toString());
            int creditValue = Integer.parseInt(parts[1].split(" ")[0]);
            int metalValue = creditValue / decimalValue;
            romanNumerals.put(metals[metals.length - 1], String.valueOf(metalValue));
        }
        else {
            // Handle queries like "how much is pish tegj glob glob?"
            System.out.println("statement output");

            System.out.println(result);
        }*/
    }

    public static void main(String[] args) {
        QueryProcessor queryProcessor = new QueryProcessor();
        queryProcessor.processQueries();
    }
}
