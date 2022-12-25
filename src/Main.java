public class Main {
    public static void main(String[] args) {

        char[] chars = new char[] {'w', 'o', 'r', 'l', 'd'};
        String str = "year";

        MyStringBuilderImpl myStringBuilder = new MyStringBuilderImpl("Hello Hello world Hello   Hello Hello world Hello");
        MyStringBuilderImpl myStringBuilder1 = new MyStringBuilderImpl();
        myStringBuilder1.append("eeheeelloeeeeeeeeeeeee");
        myStringBuilder.delete("Hel");
        myStringBuilder1.delete("e");
        System.out.println("My string builder = " + myStringBuilder);
        System.out.println("My string builder = " + myStringBuilder1);


    }
}