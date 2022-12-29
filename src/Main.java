import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        MyStringBuilder myStringBuilder = new MyStringBuilderImpl("1234567");
        myStringBuilder.append('c');
        myStringBuilder.insert('a', 2);
        System.out.println(myStringBuilder.toString());

    }
}