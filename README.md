
# GALAXY MERCHANT TRADING 

This project implements an intergalactic trading system where users can input queries to convert and calculate values based on Roman numerals. The main components of the project include `com.bhavesh.coffeee.assignment.Main.java`, `com.bhavesh.coffeee.assignment.processor.QueryProcessor.java`, `com.bhavesh.coffeee.assignment.calculator.ValueCalculator.java`, `com.bhavesh.coffeee.assignment.RomanToDecimalConverter.java`, and a test file `com.bhavesh.coffeee.assignment.calculator.ValueCalculatorTest.java` using JUnit.

## Files

### 1. `com.bhavesh.coffeee.assignment.Main.java`

This file serves as the entry point for the program. It initializes the `com.bhavesh.coffeee.assignment.processor.QueryProcessor` and handles user input.

### 2. `com.bhavesh.coffeee.assignment.processor.QueryProcessor.java`

`com.bhavesh.coffeee.assignment.processor.QueryProcessor` class takes care of processing user queries. It splits the query, identifies the type of query, and delegates the calculation to the `com.bhavesh.coffeee.assignment.calculator.ValueCalculator` class.

### 3. `com.bhavesh.coffeee.assignment.calculator.ValueCalculator.java`

`com.bhavesh.coffeee.assignment.calculator.ValueCalculator` class performs the actual calculations. It includes methods for handling queries related to conversion, calculating credits, and comparing values. The class uses `com.bhavesh.coffeee.assignment.RomanToDecimalConverter` to convert Roman numerals to decimal.

### 4. `com.bhavesh.coffeee.assignment.RomanToDecimalConverter.java`

`com.bhavesh.coffeee.assignment.RomanToDecimalConverter` is a utility class responsible for converting a Roman numeral string to its decimal equivalent.

### 5. `com.bhavesh.coffeee.assignment.calculator.ValueCalculatorTest.java`

### 6. `InputFile` is a plain text file which is responsible for input the program, if you want to change or update inputs you should go to `InputFile'

`com.bhavesh.coffeee.assignment.calculator.ValueCalculatorTest` is a JUnit test class that includes test cases for the methods in the `com.bhavesh.coffeee.assignment.calculator.ValueCalculator` class. It verifies the correctness of the conversion and calculation logic.


## Sample Queries

Sample queries include conversions, credit calculations, and value comparisons. Refer to the provided sample queries in the code comments or create your own queries based on the rules described in the problem description.

## Testing

To run the tests:

```bash
java -cp .:junit-platform-console-standalone-<version>.jar org.junit.platform.console.ConsoleLauncher --class-path . --scan-class-path
```

Replace `<version>` with the version number of the JUnit platform jar file.

## Contributing

Feel free to contribute to the project by adding new features, improving existing ones, or fixing issues. Create a pull request with your changes.

---

Note: Make sure to replace `<version>` with the actual version number of the JUnit platform jar file you are using. Also, update the README as needed based on your project structure and specific requirements.