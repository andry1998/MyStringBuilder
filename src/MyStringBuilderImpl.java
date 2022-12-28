import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MyStringBuilderImpl implements MyStringBuilder {
    char[] charArray;

    int length;

    Stack<HistoryOperation> redoHistory;

    Stack<HistoryOperation> undoHistory;

    public MyStringBuilderImpl (){
        this.charArray = new char[0];
        this.length = charArray.length;
        redoHistory = new Stack<>();
        undoHistory = new Stack<>();
    }

    public MyStringBuilderImpl(String s) {
        this.charArray  = s.toCharArray();
        this.length = s.length();
        redoHistory = new Stack<>();
        undoHistory = new Stack<>();
    }

    @Override
    public MyStringBuilderImpl append(char c) {
        var curStr = this.charArray;
        char[] res = new char[++length];
        enumElements(this.charArray, res, 0, this.charArray.length);
        res[--length] = c;
        this.charArray = res;
        this.length = this.charArray.length;
        redoHistory.push(
                new HistoryOperation(
                        OperationType.APPEND,
                        Arrays.asList(String.valueOf(c)), Arrays.asList(curStr.length))
        );
        return this;
    }

    @Override
    public MyStringBuilderImpl append(char[] c) {
        var curStr = this.charArray;
        this.charArray = appendArrayChars(c);
        this.length = this.charArray.length;
        redoHistory.push(
                new HistoryOperation(OperationType.APPEND, Arrays.asList(String.valueOf(c)), Arrays.asList(curStr.length))
        );
        return this;
    }

    @Override
    public MyStringBuilderImpl append(String s) {
        var curStr = this.charArray;
        this.charArray = appendArrayChars(s.toCharArray());
        this.length = this.charArray.length;
        redoHistory.push(
                new HistoryOperation(OperationType.APPEND, Arrays.asList(s), Arrays.asList(curStr.length))
        );
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
        int deleteChars = 0;
        int countEqualsChars = 0;
        int countIn = numberOfOccurrences(s);
        char[] res = new char[charArray.length - s.length() * countIn];

        if(s.length() == 1) {
            res = deleteChar(value[0], charArray, res);
        }
        else {
            for(int i = 0; i < charArray.length; i++) {
                countEqualsChars = countChars(s, i, charArray);

                if(countEqualsChars == value.length && i == 0) {
                    res[i] = charArray[i + value.length];
                    i+=value.length;
                    deleteChars = countEqualsChars;
                }

                else if(countEqualsChars == value.length && i + value.length + 1 < charArray.length && i != 0) {
                    res[i - deleteChars] = charArray[i + value.length];
                    i+=value.length;
                    deleteChars += countEqualsChars;
                }

                else if(countEqualsChars == value.length && i + value.length + 1 == charArray.length && i != 0) {
                    res[i - deleteChars] = charArray[i + value.length];
                    break;
                }

                else if(countEqualsChars == value.length && i != 0 && i+ value.length == charArray.length) {
                    break;
                }

                else if(countEqualsChars != value.length && i == 0) {
                    res[i] = charArray[i];
                }

                else if(countEqualsChars != value.length && i != 0) {
                    res[i - deleteChars] = charArray[i];
                }

            }
        }

        this.charArray = res;
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl remove(int startIndex, int length) {
        char[] res = new char[this.length - length];
        int count = startIndex;
        res = enumElements(charArray, res, 0, startIndex);
        res = enumElements(charArray, res, count, startIndex + length, charArray.length);
        charArray = res;
        this.length = charArray.length;
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
            count = countChars(s, i, charArray);
            if(count == value.length) {
                countIn++;
            }
        }
        return countIn;
    }

    public int countChars(String s, int i, char[] chars) {
        char[] value = s.toCharArray();
        int count = 0;
        for(int j = 0; j < value.length; j++) {
            if(chars[i + j] == value[j]) {
                count++;
            }
            else {
                return count;
            }
        }
        return count;
    }

    public char[] deleteChar(char c, char[] chars, char[] res) {
        int count = 0;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == c && i == chars.length -1) {
                break;
            }
            else if(chars[i] == c && chars[i + 1] != c) {
                res[i - count] = chars[i + 1];
                i++;
                count++;
            }
            else if(chars[i] == c && chars[i + 1] == c) {
                count++;
            }
            else
                res[i - count] = chars[i];
        }
        return res;
    }

    public char[] removeArrayChars(char[] c) {
        char[] res = new char[length - c.length];
        enumElements(charArray, res, 0, res.length);
        return res;
    }

    public void undo() throws Exception {
        if(redoHistory.isEmpty())
            return;
        var operation = redoHistory.pop();
        undoHistory.push(operation);
        switch (operation.operationType) {
            case APPEND:
                remove(operation.getIntOperands().get(0), operation.getStringOperands().get(0).length());
                break;
            case INSERT:
                throw new Error("Insert");
            case DELETE:
                throw new Error("Delete");
            default: throw new Exception("Undefined type");
        }
    }
}
