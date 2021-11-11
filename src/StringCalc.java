import java.util.Arrays;

public class StringCalc {
    public static void main(String[] args) {
        String text = "-123*-2";
        StringConverter element = new StringConverter();
        System.out.println(Arrays.toString(element.getTokensArray(text)));
    //    String text2 = element.getRevPoNot(element.getTokensArray(text));

 //     System.out.println(text2);
    //    System.out.println(element.getResultFromRPN(text2));
    }
}
