package com.bhavesh.coffeee.assignment.processor;

import com.bhavesh.coffeee.assignment.assigner.CreditsPreConditonAssigner;
import com.bhavesh.coffeee.assignment.assigner.RomainPreConditionAssigner;
import com.bhavesh.coffeee.assignment.calculator.ValueCalculator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class QueryProcessor {
    private final HashMap<String, String> romanNumerals;
    private final ValueCalculator calculator;

    private RomainPreConditionAssigner romainPreConditionAssigner;
    private CreditsPreConditonAssigner creditsPreConditonAssigner;

    public QueryProcessor() {
        romanNumerals = new HashMap<>();
        calculator = new ValueCalculator();
        romainPreConditionAssigner = new RomainPreConditionAssigner();
        creditsPreConditonAssigner = new CreditsPreConditonAssigner();
    }

    public void processQueries() {
        try {
            FileInputStream fileInputStream = new FileInputStream("InputFile");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fileInputStream));

            List<String> queries = new ArrayList<>();
            String line = reader.readLine();
            while(line != null) {
                queries.add(line);
                line = reader.readLine();
            }

            processQueries(queries);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void processQueries(List<String> queries) {
        for (String query : queries) {
            processInput(query);
        }
    }

    private void processInput(String input) {
        String[] parts = input.split(" is ");

        if (isDataAssigner(input, parts)) {
            if (parts[1].endsWith("Credits")) {
                creditsPreConditonAssigner.process(parts,romanNumerals);
            } else {
                romainPreConditionAssigner.process(parts, romanNumerals);
            }

        } else {
            String result = calculator.calculateQueryValue(input, romanNumerals);

                System.out.println(result);

        }
    }

    private static boolean isDataAssigner(String input, String[] parts) {
        return parts.length == 2 && (!input.endsWith("?"));
    }

}
