import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {

    Calculator value = new Calculator();

    @Test
    void addition_test() throws CalculatorException {
        double output = Utils.calculate(2, 2, "+");

        assertEquals(4, output);
    }
    @Test
    void subtraction_test() throws CalculatorException {
        double output = Utils.calculate(4, 2, "-");

        assertEquals(2, output);
    }
    @Test
    void multiplication_test() throws CalculatorException {
        double output = Utils.calculate(4, 4, "*");

        assertEquals(16, output);
    }
    @Test
    void division_test() throws CalculatorException {
        double output = Utils.calculate(5, 2, "/");

        assertEquals(2.5, output);
    }
    @Test
    void calculate_text_math_expression_no_whitespace() throws CalculatorException {

        assertEquals(0.08333333333333333,
                value.breakDownString("2/4*3*2"));
        assertEquals(2,
                value.breakDownString("3-1"));
        assertEquals(6,
                value.breakDownString("3-1+4"));
        assertEquals(6,
                value.breakDownString("10-1*4"));
        assertEquals(5,
                value.breakDownString("2+3"));
    }
    @Test
    void calculate_text_math_expression_with_parenthesis() throws CalculatorException {

        assertEquals(800,
                value.breakDownString("2(10)*20*2"));
        assertEquals(800,
                value.breakDownString("2(10) * 20 * 2"));
        assertEquals(402,
                value.breakDownString("2(10) * 20 + 2"));
    }
    @Test
    void calculate_text_math_expression_with_variables() throws CalculatorException {

        assertEquals(270,
                value.breakDownString("nine * three(ten)"));

    }
    @Test
    void calculate_text_math_expression_dual_parenthesis() throws CalculatorException {

        /*
        assertEquals(800,
                value.breakDownString("2(2+5(7+1))")); */
        assertEquals(14,
                value.breakDownString("2(2+5)"));
        assertEquals(-2,
                value.breakDownString("2(2-3)"));
        assertEquals(6,
                value.breakDownString("2(2-3+4)"));
        assertEquals(10,
                value.breakDownString("2(2-3+4-3+5)"));
        assertEquals(24,
                value.breakDownString("2(2+5(2))"));
    }

}
