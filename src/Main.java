import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Hello");
        MyStringBuilderImpl myStringBuilder = new MyStringBuilderImpl("Hello");
//        MyStringBuilderImpl myStringBuilder1 = new MyStringBuilderImpl();
//        myStringBuilder1.append("eeheeelloeeeeeeeeeeeee");
//        myStringBuilder.delete("Hel");
//        myStringBuilder1.delete("e");
//        System.out.println("My string builder = " + myStringBuilder);
//        System.out.println("My string builder = " + myStringBuilder1);

        Stack<StringBuilder> stack = new Stack<>();
        stack.push(sb.append('h'));
        stack.push(sb.append('e'));
        stack.push(sb.append('l'));

        sb.toString();
        System.out.println(sb);
        System.out.println(stack);

        Stack<MyStringBuilder> myStack = new Stack<>();
        myStringBuilder.append('h');
        myStringBuilder.append('e');
        myStringBuilder.append('l');
        myStringBuilder.append(new char[] {'l', 'o'});
        myStringBuilder.remove(new char[] {'l', 'o'});
        System.out.println(myStringBuilder.toString());


    }
}