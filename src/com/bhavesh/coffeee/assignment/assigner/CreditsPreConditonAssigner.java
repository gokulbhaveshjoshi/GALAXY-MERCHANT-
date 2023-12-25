package com.bhavesh.coffeee.assignment.assigner;

import com.bhavesh.coffeee.assignment.RomanToDecimalConverter;

import java.util.HashMap;
import java.util.Map;

public class CreditsPreConditonAssigner implements PreconditionAssigner{
    @Override
    public void process(String[] parts, Map<String, String> preCondition) {
        StringBuilder sb = new StringBuilder();
        String[] part = parts[0].split(" ");

        for (int i = 0; i < part.length - 1; i++) {
            String romanValue = preCondition.getOrDefault(part[i], "");
            sb.append(romanValue);
        }
        int xMetal = RomanToDecimalConverter.convertToDecimal(sb.toString());
        int totalCredits = Integer.parseInt( parts[1].split(" ")[0]);
        double metalCost = (double) totalCredits / xMetal;
        preCondition.put(part[part.length - 1].trim(), String.valueOf(metalCost));
    }
}
