import java.lang.reflect.Array;

import static java.lang.System.arraycopy;

public class MyStringBuilderImpl implements MyStringBuilder {
    char[] charArray;

    int length;

    public MyStringBuilderImpl (){}

    public MyStringBuilderImpl(String s) {
        this.charArray  = s.toCharArray();
        this.length = s.length();
    }

    @Override
    public MyStringBuilderImpl append(char c) {
//        char[] el = new char[]{c};
//        char[] combined = new char[charArray.length + el.length];
        char[] res = new char[++length];
        enumElements(this.charArray, res);
        res[--length] = c;
        this.charArray = res;
        this.length = charArray.length;
//        arraycopy(charArray, 0, combined, 0, charArray.length);
//        arraycopy(el, 0, combined, charArray.length, el.length);
//        this.charArray = combined;
        return this;
    }

    @Override
    public MyStringBuilderImpl append(char[] c) {
        this.charArray = appendArrayChar(c);
        this.length = charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl append(String s) {
        this.charArray = appendArrayChar(s.toCharArray());
        this.length = charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl insert(char c, int index) {
        return null;
    }

    @Override
    public MyStringBuilderImpl insert(char[] c, int index) {
        return null;
    }

    @Override
    public MyStringBuilderImpl insert(String s, int index) {
        return null;
    }

    @Override
    public MyStringBuilderImpl delete(int from, int to) {
        return null;
    }


    @Override
    public MyStringBuilderImpl delete(String s) {
        return null;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public String toString() {
        return new String(charArray);
    }

    public char[] enumElements(char[] chars, char[] res) {
        for(int i = 0; i < chars.length; i++) {
            res[i] = chars[i];
        }
        return res;
    }

    public char[] enumElements(char[] chars, char[] res, int count) {
        for(int i = 0; i < chars.length; i++) {
            res[count] = chars[i];
            count++;
        }
        return res;
    }

    public char[] appendArrayChar(char[] c) {
        char[] res = new char[charArray.length + c.length];
        res = enumElements(charArray, res);
        int count = length;
        res = enumElements(c, res, count);
        return res;
    }

}
