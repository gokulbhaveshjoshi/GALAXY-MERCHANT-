import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class ValueCalculatorTest {

    @Test
    public  void testProcess() {
        HashMap<String, String> romanNumerals = new HashMap<>();
        romanNumerals.put("glob", "I");
        romanNumerals.put("prok", "V");
        romanNumerals.put("pish", "X");
        romanNumerals.put("tegj", "L");

        // Create the ValueCalculator instance
        ValueCalculator calculator = new ValueCalculator();

        // Test the calculateHowMuchQuery method
        String query = "how much is pish tegj glob glob ?";
        String result = calculator.calculateHowMuchQuery(query.split(" "), romanNumerals);
        assertEquals("pish tegj glob glob is 42", result);

    }

    @Test
    public void testCalculateHowManyCreditsQuery() {
        // Set up the test data
        HashMap<String, String> romanNumerals = new HashMap<>();
        romanNumerals.put("glob", "I");
        romanNumerals.put("prok", "V");
        romanNumerals.put("pish", "X");
        romanNumerals.put("tegj", "L");

        // Create the ValueCalculator instance
        ValueCalculator calculator = new ValueCalculator();

        // Test the calculateHowManyCreditsQuery method
        String query = "how many Credits is glob prok Silver ?";
        String result = calculator.calculateHowManyCreditsQuery(query.split(" "), romanNumerals);
        assertEquals("glob prok Silver is 0.0 Credits", result);
    }

}
