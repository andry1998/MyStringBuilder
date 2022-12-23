public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder("Hello");
        System.out.println("String builder = " + stringBuilder);

        char[] chars = new char[] {'w', 'o', 'r', 'l', 'd'};
        String str = "year";

        MyStringBuilderImpl myStringBuilder = new MyStringBuilderImpl("Hello");
        myStringBuilder.delete("llo");
        System.out.println("My string builder = " + myStringBuilder);
        System.out.println("My string builder = " + myStringBuilder.getLength());


    }
}