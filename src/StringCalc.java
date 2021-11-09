import java.util.Arrays;

public class StringCalc {
    public static void main(String[] args) {
        String text = "2 3312 1 + * 2 2 / *"; //5
        StringConverter element = new StringConverter();
  //      System.out.println( Arrays.toString( element.getTokensArray(text)));
    //    System.out.println(element.getRevPoNot(element.getTokensArray(text)));
        System.out.println(element.getResultFromRPN(text));
    }
}
