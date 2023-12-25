package com.bhavesh.coffeee.assignment.assigner;

import java.util.Map;

public interface PreconditionAssigner {
    void process(String[] parts, Map<String, String> preCondition);
}
