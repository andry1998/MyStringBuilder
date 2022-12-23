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
//        arraycopy(charArray, 0, combined, 0, charArray.length);
//        arraycopy(el, 0, combined, charArray.length, el.length);
//        this.charArray = combined;
        char[] res = new char[++length];
        enumElements(this.charArray, res, 0, this.charArray.length);
        res[--length] = c;
        this.charArray = res;
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl append(char[] c) {
        this.charArray = appendArrayChars(c);
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl append(String s) {
        this.charArray = appendArrayChars(s.toCharArray());
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl insert(char c, int index) {
        char[] res = new char[++length];
        enumElements(this.charArray, res, 0, index);
        res[index] = c;
        for(int i = index + 1; i < res.length; i++) {
            res[i] = this.charArray[i-1];
        }
        this.charArray = res;
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl insert(char[] c, int index) {
        this.charArray = insertArrayChars(c, index);
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl insert(String s, int index) {
        this.charArray = insertArrayChars(s.toCharArray(), index);
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl delete(int from, int to) {
        char[] res = new char[this.charArray.length - (to - from)];
        enumElements(this.charArray, res, 0, from);
        enumElements(this.charArray, res, from, to, this.charArray.length);
        this.charArray = res;
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl delete(String s) {
        char[] value = s.toCharArray();
        int count = 0;
        int countIn = numberOfOccurrences(s);
        char[] res = new char[charArray.length - s.length() * countIn];

        for(int i = 0; i < charArray.length; i++) {
            for(int j = 0; j < value.length; j++) {
                if(charArray[i + j] == value[j]) {
                    count++;
                }
                else {
                    count = 0;
                    break;
                }
            }

            if(count == value.length && i == 0) {
                res[i] = charArray[i + value.length];
                i+=value.length;
            }

            else if(count == value.length && i + value.length - 1 < charArray.length && i != 0) {
                break;
            }

            else if(count == value.length && i != 0 && i+ value.length >= charArray.length) {
                res[i- value.length] = charArray[i + value.length];
            }

            else if(count != value.length && i == 0) {
                res[i] = charArray[i];
            }

            else if(count != value.length && i != 0) {
                res[i] = charArray[i];
            }

        }




//        char[] res = new char[this.charArray.length - s.length()];
//
//
//        for(int i = 0; i < charArray.length; i++) {
//            for(int j = 0; j < value.length; j++) {
//                if(charArray[i + j] == value[j]) {
//                    count++;
//                }
//                else {
//                    count = 0;
//                    break;
//                }
//            }
//            if(count == value.length) {
//                if(i + value.length < charArray.length) {
//                    res[i] = this.charArray[i + value.length];
//                }
//                else
//                    charArray = res;
//            }
//            else if(i < res.length) {
//                res[i] = charArray[i];
//            }
//        }

        this.charArray = res;
        this.length = this.charArray.length;
        return this;

    }

    @Override
    public int getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        return new String(this.charArray);
    }

    public char[] enumElements(char[] chars, char[] res, int start, int stop) {
        for(int i = start; i < stop; i++) {
            res[i] = chars[i];
        }
        return res;
    }

    public char[] enumElements(char[] chars, char[] res, int count, int start, int stop) {
        for(int i = start; i < stop; i++) {
            res[count] = chars[i];
            count++;
        }
        return res;
    }

    public char[] appendArrayChars(char[] c) {
        char[] res = new char[this.length + c.length];
        res = enumElements(this.charArray, res, 0, this.charArray.length);
        int count = this.length;
        res = enumElements(c, res, count, 0, c.length);
        return res;
    }

    public char[] insertArrayChars(char[] c, int index) {
        char[] res = new char[this.charArray.length + c.length];
        res = enumElements(charArray, res, 0, index);
        int count = index;
        res = enumElements(c, res, count, 0, c.length);
        count = index + c.length;
        res = enumElements(this.charArray, res, count, index, this.charArray.length);
        return res;
    }

    public int numberOfOccurrences(String s) {
        int count = 0;
        int countIn = 0;
        char[] value = s.toCharArray();
        for(int i = 0; i < charArray.length; i++) {
            for(int j = 0; j < value.length; j++) {
                if(charArray[i + j] == value[j]) {
                    count++;
                }
                else {
                    count = 0;
                    break;
                }
            }
            if(count == value.length) {
                countIn++;
            }
        }
        return countIn;
    }

}
