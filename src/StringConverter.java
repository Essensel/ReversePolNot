import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Stack;

public class StringConverter {

    private int index;
    private String curentToken;
    private int tokenPriority;
    private Stack<String> stack = new Stack<>();
    private String resultExpretion = "";

    /**
     * метод добавляет знак "0" в выражени между операциями
     * для осуществления вычислений с отрицательными значениями
     * @param expretion изначальное выражение
     * @return corectExpr
     */
   private String getCorrectExpretion(String expretion) {
        String corectExpr = "";
        for (int i = 0; i < expretion.length(); i++) {
            char curentChar = expretion.charAt(i);
            if (curentChar == '-') {
                if (i == 0) {
                    corectExpr += "0";
                }
                else if ((expretion.charAt(i - 1) == '(')) {
                    corectExpr += "0";
                }
            }
            corectExpr += curentChar;
        }
        return corectExpr;
    }

    private String[] getTokensArray(String corectExpretion) {
        char curentChar;
        String token = "";
        // для разделения строки на токены вставляем пробелы в исходную строку, разделяя операнды и знаки операций.
        for (int i = 0; i < corectExpretion.length(); i++) {
            curentChar = corectExpretion.charAt(i);
            if ((curentChar == '(') || (curentChar == ')') || (curentChar == '*') || (curentChar == '/') || (curentChar == '+') || (curentChar == '-')) {
                token += " " + curentChar + " ";
            } else token += curentChar;
        }
        // исходная строка разбивается на токены по разделителю: " "(в массиве есть лишние  нулевые элементы если в исходной строке операнды находлись рядом)
        token = token.trim();
        String[] arrayWithZero = token.split(" ");
        String[] tokensArray;

        // находиться размер результирующего массива
        int count = 0;
        for (int i = 0; i < arrayWithZero.length; i++) {
            if (arrayWithZero[i].equals("")) {
                count++;
            }
        }

        // результирующий массив очищается от нулевых элементов
        tokensArray = new String[arrayWithZero.length - count];
        int number = 0;
        for (int i = 0; i < arrayWithZero.length; i++) {
            if (!arrayWithZero[i].equals("")) {
                tokensArray[number] = arrayWithZero[i];
                number++;
            }
        }
        return tokensArray;
    }

  private int getPriorityOfElement(String element) {
        switch (element) {
            case "*":
            case "/":
                index = 3;
                break;
            case "+":
            case "-":
                index = 2;
                break;
            case ")":
                index = 1;
                break;
            case "(":
                index = 0;
                break;
            default:
                index = -1;
        }
        return index;
    }

    private String getRevPoNot(String[] tokens) {

        for (int i = 0; i < tokens.length; i++) {
            curentToken = tokens[i];
            tokenPriority = getPriorityOfElement(curentToken);

//Пpосматpивается исходная стpока символов слева напpаво, опеpанды пеpеписываются в выходную стpоку
            if (tokenPriority == -1) {
                resultExpretion += curentToken + " ";
            }

// опеpация выталкивает из стека все опеpации с большим или pавным пpиоpитетом в выходную стpоку;
            if (tokenPriority > 1 && !stack.empty()) {
                while (!stack.empty()) {
                    if (tokenPriority <= getPriorityOfElement(stack.peek())) {
                        resultExpretion += stack.pop() + " ";
                    } else break;
                }
                stack.push(curentToken);
            }

            // если стек пуст, то опеpация из входной стpоки пеpеписывается в стек
            if (stack.empty() && tokenPriority > 1) {
                stack.push(curentToken);

            }

            //     если очеpедной символ из исходной стpоки есть откpывающая скобка, то он пpоталкивается в стек
            if (tokenPriority == 0) {
                stack.push(curentToken);
            }

//   закpывающая кpуглая скобка выталкивает все опеpации из стека до ближайшей откpывающей скобки, сами скобки в выходную стpоку не пеpеписываются, а уничтожают дpуг дpуга.
            if (tokenPriority == 1) {
                while (getPriorityOfElement(stack.peek()) != 0) {
                    resultExpretion += stack.pop() + " ";
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            resultExpretion += stack.pop() + " ";
        }
        return resultExpretion;
    }


    private double getResultFromRPN(String textRPN) {
        double result = 0;
        Calculator calculator = new Calculator();
        String[] tokenRpn = textRPN.split(" ");
        Stack<String> resultStack = new Stack<>();

        for (int i = 0; i < tokenRpn.length; i++) {
            // Если на вход подан операнд, он помещается на вершину стека
            if (getPriorityOfElement(tokenRpn[i]) == -1) {
                resultStack.push(tokenRpn[i]);
                // Если на вход подан знак операции, то соответствующая операция выполняется над требуемым количеством значений,
                // извлечённых из стека, взятых в порядке добавления. Результат выполненной операции кладётся на вершину стека
            }
            if (getPriorityOfElement(tokenRpn[i]) > 1) {

                try {
                    double second = Double.parseDouble(resultStack.pop());
                    double first = Double.parseDouble(resultStack.pop());
                    result = calculator.getResult(first, second, tokenRpn[i]);
                    resultStack.push(Double.toString(result));
                } catch (Exception e) {
                    System.out.println("что то пошло не так!");
                }
            }
        }
        return result;
    }
    protected double getResult(StringConverter stringConverter, String expretion){
    double result =  stringConverter.getResultFromRPN(stringConverter.getRevPoNot(stringConverter.getTokensArray(stringConverter.getCorrectExpretion(expretion))));
        return result;
    }

}

