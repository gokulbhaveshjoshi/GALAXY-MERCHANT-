import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
                double metalCost = (double) totalCredits / xMetal;
                romanNumerals.put(part[part.length - 1].trim(), String.valueOf(metalCost));
            } else {
                romanNumerals.put(parts[0].trim(), parts[1].trim());
            }

        } else {
            String result = calculator.calculateQueryValue(input, romanNumerals);
            if (Objects.equals(result, "No") || Objects.equals(result, "Yes")) {

                printOutput(input, result);
            } else {
                System.out.println(result);
            }
        }
    }

    private void printOutput(String input, String replace) {
        System.out.println( removeFirstLastInInput(input, replace == "No"));

    }

    private String removeFirstLastInInput(String input, boolean b) {
        StringBuilder sb = new StringBuilder();
        for (String str: input.split(" ")) {
            if (Objects.equals(str, "IS") || Objects.equals(str, "DOES") || Objects.equals(str, "?")) {
                continue;
            }
            if (b) {
                if (Objects.equals(str, "LESS")) {
                    sb.append("MORE").append(" ");
                } else if (Objects.equals(str, "MORE")) {
                    sb.append("LESS").append(" ");
                } else if (Objects.equals(str, "LARGER")) {
                    sb.append("SMALLER").append(" ");
                } else if (Objects.equals(str, "SMALLER")) {
                    sb.append("LARGER").append(" ");
                } else {
                    sb.append(str).append(" ");
                }
            } else {
                sb.append(str).append(" ");
            }



        }
        return sb.toString();
    }

    public static void main(String[] args) {
        QueryProcessor queryProcessor = new QueryProcessor();
        queryProcessor.processQueries();
    }
}
