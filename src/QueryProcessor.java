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
                queries.add(line);
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
        String[] parts = input.split(" is ");

        if (parts.length == 2 && (!input.endsWith("?"))) {
            if (parts[1].endsWith("Credits")) {
                StringBuilder sb = new StringBuilder();
                String[] part = parts[0].split(" ");

                for (int i = 0; i < part.length - 1; i++) {
                    String romanValue = romanNumerals.getOrDefault(part[i], "");
                    sb.append(romanValue);
                }
                int xMetal = RomanToDecimalConverter.convertToDecimal(sb.toString());
                int totalCredits = Integer.parseInt( parts[1].split(" ")[0]);
                double metalCost = (double) totalCredits / xMetal;
                romanNumerals.put(part[part.length - 1].trim(), String.valueOf(metalCost));
            } else {
                romanNumerals.put(parts[0].trim(), parts[1].trim());
            }

        } else {
            String result = calculator.calculateQueryValue(input, romanNumerals);

                System.out.println(result);

        }
    }

}
