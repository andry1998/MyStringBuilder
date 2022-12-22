public class Main {
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder("Hello");
        stringBuilder.append('w');
        System.out.println("String builder = " + stringBuilder);

        char[] chars = new char[] {'w', 'o', 'r', 'l', 'd'};

        MyStringBuilderImpl myStringBuilder = new MyStringBuilderImpl("Hello");
        myStringBuilder.append(chars);
        System.out.println("My string builder = " + myStringBuilder);
        System.out.println("My string builder = " + myStringBuilder.getLength());


    }
}