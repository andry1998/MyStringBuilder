public interface MyStringBuilder {

    MyStringBuilderImpl append(char c);

    MyStringBuilderImpl append(char[] c);

    MyStringBuilderImpl append(String s);

    MyStringBuilderImpl remove(int startIndex, int length);

    MyStringBuilderImpl insert(char c, int index);

    MyStringBuilderImpl insert(char[] c, int index);

    MyStringBuilderImpl insert(String s, int index);

    MyStringBuilderImpl delete(int from, int to);

    MyStringBuilderImpl delete(String s);

    String toString();

    int getLength();

    void undo();

    void redo();
}
