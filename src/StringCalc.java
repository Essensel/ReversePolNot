import java.util.Arrays;

public class StringCalc {
    public static void main(String[] args) {
        String text = "2+2 /2fdg";
        StringConverter stringConverter = new StringConverter();
        System.out.println( stringConverter.getResult(stringConverter,text));
    }
}
