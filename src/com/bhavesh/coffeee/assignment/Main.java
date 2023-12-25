package com.bhavesh.coffeee.assignment;

import com.bhavesh.coffeee.assignment.processor.QueryProcessor;


public class Main {
    public static void main(String[] args) {
        QueryProcessor queryProcessor = new QueryProcessor();
        queryProcessor.processQueries();
    }
}