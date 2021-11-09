public class Calculator {

    protected double getResult(double first, double second, String operation) {
        double result;
        switch (operation) {
            case ("+"):
               result = first + second;
            break;
            case ("-"):
                result = first - second;
            break;
            case ("*"):
                result = first * second;
            break;
            case ("/"):
                 result = first / second;
            break;
            default: result=-999999999;
        }
        return result;
    }
}