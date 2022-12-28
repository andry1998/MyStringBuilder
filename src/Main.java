import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        MyStringBuilderImpl myStringBuilder = new MyStringBuilderImpl("Hello world");
        myStringBuilder.delete("e");
        System.out.println(myStringBuilder.deletedString);
        //System.out.println(myStringBuilder.indexDeleted);
        try {
//            myStringBuilder.append("qqq");
//            myStringBuilder.delete(1,2);
//            myStringBuilder.undo();
//            myStringBuilder.redo();
//            myStringBuilder.insert("aaa", 3);
//            myStringBuilder.undo();
//            myStringBuilder.redo();
            System.out.println(myStringBuilder.toString());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}