interface Operations {

    static String parseOperation(String operation) {
        return switch (operation) {
            case "(" -> "(";
            case "+", "add", "plus" -> "+";
            case "-", "minus", "sub" -> "-";
            case "*", "multiplied", "times" -> "*";
            case "/", "divided", "div" -> "/";
            case ")" -> ")";
            default -> "no";//throw new RuntimeException("Internal error " + operation);
        };
    }

    /*
    enum Sign {
        PLUS,
        MINUS,
        DIVIDE,
        MULTIPLY
    } */

/*
    static boolean isOperation(String operation) {
        return "(+-/*".contains(operation);
    }
*/
}