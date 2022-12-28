import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("qwe");
        MyStringBuilderImpl myStringBuilder = new MyStringBuilderImpl("Hello");
//        MyStringBuilderImpl myStringBuilder1 = new MyStringBuilderImpl();
//        myStringBuilder1.append("eeheeelloeeeeeeeeeeeee");
//        myStringBuilder.delete("Hel");
//        myStringBuilder1.delete("e");
//        System.out.println("My string builder = " + myStringBuilder);
//        System.out.println("My string builder = " + myStringBuilder1);
        System.out.println("Remove = " + myStringBuilder.remove(2, 2));
        try {
            myStringBuilder.append('1');
            myStringBuilder.append('2');
            myStringBuilder.append("51231");
            myStringBuilder.undo();
            myStringBuilder.undo();
            myStringBuilder.append(new char[]{'6', '6', '7'});
            myStringBuilder.undo();
            System.out.println(myStringBuilder.toString());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}