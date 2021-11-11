public class Calculator {

    protected double getResult(double first, double second, String operation) {
        double result =  0;
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
        }
        return result;
    }
}