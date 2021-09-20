import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Calculator {
    ArrayList<String> numbersArray = new ArrayList<>(
            Arrays.asList("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten")
    );

    /*
    (?<=\d) means the previous character is a digit
    (?=\D) means the next character is a non-digit
    (?<=\d)(?=\D) together will match between a digit and a non-digit
    regexA|regexB means either regexA or regexB is matched,
    which is used as above points, but non-digit then digit for the visa-versa logic
    //String[] array = expression.split("(?<=\\d)(?=\\D)|(?<=\\D)(?=\\d)");
    */

    public double breakDownString(String expression) throws CalculatorException {


        List<String> expressions = Pattern.compile("(\\d+|[(*/+\\-)]|[A-Za-z]+)")
                .matcher(expression)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());


        System.out.println(expressions);
        double result = 0.0;

        int ifParensInExpression = getIndices(expressions, "(").size();
        System.out.println("parens " + ifParensInExpression);
        while (expressions.size() > 1) {

            String[] operations = {"(", "*", "/", "-", "+"}; // Priority order

            for (String operation : operations) {
                // Find all operation of x type e.g. calculate all * then all / etc.
                List<Integer> indices = getIndices(expressions, operation);

                if (operation.equals("(") && ifParensInExpression>0) calculateParen(expressions, operation);
                else calculateAllOfSameOperationType(expressions, operation, indices);
            }
            result = Double.parseDouble(expressions.get(0));
        }
        return result;
    }

    private void calculateParen(List<String> expressions, String operation) throws CalculatorException {
        double result, first, second;

        int startParen = getIndices(expressions, "(").get(0);
        int endParen = getIndices(expressions, ")").get(0);

        System.out.println(" s " + startParen + " e " + endParen);

        for(int i = (startParen+2); i <= (endParen-2); startParen = getIndices(expressions, "(").get(0), endParen = getIndices(expressions, ")").get(0) ){

            String checkOperationType = expressions.get(i);

            first = getNumberOrVariable(expressions, i - 1);
            second = getNumberOrVariable(expressions, i + 1);

            result = Utils.calculate(first, second, checkOperationType);

            expressions.remove(i + 1);
            expressions.remove(i);
            expressions.add(i - 1, Double.toString(result));
            expressions.remove(i);
             System.out.println("loop " + i + " first " + first + " sec " +second + " res " + result);


        }
        startParen = getIndices(expressions, "(").get(0);
        endParen = getIndices(expressions, ")").get(0);

        double finalFirst = getNumberOrVariable(expressions, startParen - 1);;
        double finalSecond = getNumberOrVariable(expressions, startParen + 1);

        double finalResult = Utils.calculate(finalFirst, finalSecond, "*");

        expressions.remove(startParen - 1);
        expressions.remove(startParen);
        expressions.remove(startParen);
        expressions.remove(startParen-1);
        expressions.add(startParen-1, Double.toString(finalResult));

        System.out.println(expressions);

    }

    private void calculateAllOfSameOperationType(List<String> expressions, String operation,
                                                 List<Integer> indices) throws CalculatorException {
        double result, first, second;

        for (int index : indices) {
            //String opValue = operation.equals("(") ? "*" : operation;

            //Utils.calculateBetweenParenthesis(expressions, index);

            first = getNumberOrVariable(expressions, index - 1);
            second = getNumberOrVariable(expressions, index + 1);

            result = Utils.calculate(first, second, operation);

            //if (operation.equals("(")) expressions.remove(index + 2);

            expressions.remove(index + 1);
            expressions.remove(index);
            expressions.add(index - 1, Double.toString(result));
            expressions.remove(index);
            // System.out.println(expressions);
        }
    }

    private List<Integer> getIndices(List<String> expressions, String operation) {
        List<Integer> indices = IntStream.range(0, expressions.size())
                //.filter(i -> expressions.get(i).equals(operation))
                .filter(i -> Operations.parseOperation(expressions.get(i)).equals(operation))
                .collect(ArrayList::new, List::add, List::addAll);
        Collections.reverse(indices); // Start with the highest index
        return indices;
    }

    private double getNumberOrVariable(List<String> expressions, int index) {
        return expressions.get(index).matches("[a-zA-z]+") ?
                Double.parseDouble(String.valueOf(numbersArray.indexOf(expressions.get(index)))) :
                Double.parseDouble(expressions.get(index));
    }

}