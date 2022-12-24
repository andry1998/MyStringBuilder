public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder("Hello");
        System.out.println("String builder = " + stringBuilder);

        char[] chars = new char[] {'w', 'o', 'r', 'l', 'd'};
        String str = "year";

        MyStringBuilderImpl myStringBuilder = new MyStringBuilderImpl("Hello Hello world Hello   Hello Hello world Hello");
        MyStringBuilderImpl myStringBuilder1 = new MyStringBuilderImpl("Hello");
        myStringBuilder.delete("Hel");
        myStringBuilder1.delete("el");
        System.out.println("My string builder = " + myStringBuilder);
        System.out.println("My string builder = " + myStringBuilder1);


    }
}