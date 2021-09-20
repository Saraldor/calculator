import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

interface Utils {
    static double add(double first, double second) {
        return first + second;
    }

    static double subtract(double first, double second) {
        return first - second;
    }

    static double multiplication(double first, double second) {
        return first * second;
    }

    static double division(double first, double second) {
        return first / second;
    }

    static double calculate(double first, double second, String type) throws CalculatorException {
        System.out.println(first + "  " + type + "  " + second);
        return switch (type) {
            case "+" -> add(first, second);
            case "-" -> subtract(first, second);
            case "*" -> multiplication(first, second);
            case "/" -> division(first, second);
            default -> throw new CalculatorException("Illegal Argument");
        };
    }
/*
    static double calculateBetweenParenthesis(List<String> expressions, int index)
            throws CalculatorException {
        double sum = 0.0;

        OptionalInt whereIsEndParenIndex = IntStream.range(index, expressions.size())
                .filter(i -> expressions.get(i).equals(")"))
                .findFirst();

        if (whereIsEndParenIndex.isPresent()) {
            System.out.println("End parenthesis " + whereIsEndParenIndex.getAsInt() + " start " +  index);
            System.out.println(expressions.get(index + 1) + " " + expressions.get(index + 2) + " " + expressions.get(index + 3));
        }

        return sum;
    }

 */
}