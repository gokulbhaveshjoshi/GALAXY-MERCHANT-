package com.bhavesh.coffeee.assignment.assigner;

import java.util.Map;

public class RomainPreConditionAssigner implements PreconditionAssigner{
    @Override
    public void process(String[] parts, Map<String, String> preCondition) {
        preCondition.put(parts[0].trim(), parts[1].trim());
    }
}
