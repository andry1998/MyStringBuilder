import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MyStringBuilderImpl implements MyStringBuilder {
    char[] charArray;

    int length;

    Stack<HistoryOperation> undoStack;

    Stack<HistoryOperation> redoStack;

    char[] deletedString;

    List<Integer> indexDeleted;

    public MyStringBuilderImpl (){
        this.charArray = new char[0];
        this.length = charArray.length;
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        deletedString = new char[0];
        indexDeleted = new ArrayList<>();
    }

    public MyStringBuilderImpl(String s) {
        this.charArray  = s.toCharArray();
        this.length = s.length();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
        deletedString = new char[0];
        indexDeleted = new ArrayList<>();
    }

    @Override
    public MyStringBuilderImpl append(char c) {
        return append(String.valueOf(c));
    }

    @Override
    public MyStringBuilderImpl append(char[] c) {
        return append(String.valueOf(c));
    }

    @Override
    public MyStringBuilderImpl append(String s) {
        var curStr = this.charArray;
        this.charArray = appendArrayChars(s.toCharArray());
        this.length = this.charArray.length;
        undoStack.push(
                new HistoryOperation(
                        OperationType.APPEND,
                        Arrays.asList(s),
                        Arrays.asList(curStr.length)
                )
        );
        return this;
    }

    @Override
    public MyStringBuilderImpl insert(char c, int index) {
        return insert(String.valueOf(c), index);
    }

    @Override
    public MyStringBuilderImpl insert(char[] c, int index) {
        return insert(String.valueOf(c), index);
    }

    @Override
    public MyStringBuilderImpl insert(String s, int index) {
        var curl = this.charArray;
        this.charArray = insertArrayChars(s.toCharArray(), index);
        this.length = this.charArray.length;
        undoStack.push(
                new HistoryOperation(
                        OperationType.INSERT,
                        Arrays.asList(s),
                        Arrays.asList(index, s.length())
                )
        );
        return this;
    }

    public MyStringBuilderImpl insertDeleted(String s) {
        char[] res = new char[this.length - s.length() * this.indexDeleted.size()];
        for(Integer i : this.indexDeleted) {
            res = insert(s, i).charArray;
        }
        this.charArray = res;
        this.length = charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl delete(int from, int to) {
        var res = remove(from, to - from);
        undoStack.push(
                new HistoryOperation(
                        OperationType.DELETE,
                        Arrays.asList(String.valueOf(deletedString)),
                        Arrays.asList(from, to - from)
                )
        );
        return res;
    }

    @Override
    public MyStringBuilderImpl delete(String s) {
        char[] value = s.toCharArray();
        int deleteChars = 0;
        int countEqualsChars = 0;
        this.deletedString = new char[s.length()];
        deletedString = enumElements(s.toCharArray(), deletedString, 0, s.length());
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
                    this.indexDeleted.add(i);
                    i+=value.length;
                    deleteChars = countEqualsChars;
                }

                else if(countEqualsChars == value.length && i + value.length + 1 < charArray.length && i != 0) {
                    res[i - deleteChars] = charArray[i + value.length];
                    this.indexDeleted.add(i);
                    i+=value.length;
                    deleteChars += countEqualsChars;
                }

                else if(countEqualsChars == value.length && i + value.length + 1 == charArray.length && i != 0) {
                    res[i - deleteChars] = charArray[i + value.length];
                    this.indexDeleted.add(i);
                    break;
                }

                else if(countEqualsChars == value.length && i != 0 && i+ value.length == charArray.length) {
                    this.indexDeleted.add(i);
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

        undoStack.push(
                new HistoryOperation(
                        OperationType.DELETE_STRING,
                        Arrays.asList(s),
                        Arrays.asList(s.length())
                )
        );
        this.charArray = res;
        this.length = this.charArray.length;
        return this;
    }

    @Override
    public MyStringBuilderImpl remove(int startIndex, int length) {
        deletedString = new char[length];
        char[] res = new char[this.length - length];
        int n = 0;
        int count = startIndex;
        deletedString = enumElements(charArray, deletedString, n,  startIndex, startIndex + length);
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
        deletedString = new char[1];
        deletedString[0] = c;
        int count = 0;
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == c && i == chars.length -1) {
                indexDeleted.add(i);
                break;
            }
            else if(chars[i] == c && chars[i + 1] != c) {
                res[i - count] = chars[i + 1];
                indexDeleted.add(i);
                i++;
                count++;

            }
            else if(chars[i] == c && chars[i + 1] == c) {
                count++;
                indexDeleted.add(i);
            }
            else
                res[i - count] = chars[i];
        }
        return res;
    }

    @Override
    public void undo() {
        if(undoStack.isEmpty())
            return;
        var operation = undoStack.pop();
        redoStack.push(operation);
        switch (operation.operationType) {
            case APPEND -> remove(operation.getIntOperands().get(0), operation.getStringOperands().get(0).length());
            case INSERT -> remove(operation.getIntOperands().get(0), operation.getIntOperands().get(1));
            case DELETE -> insert(operation.getStringOperands().get(0), operation.getIntOperands().get(0));
            case DELETE_STRING -> insertDeleted(operation.getStringOperands().get(0));
        }
    }

    @Override
    public void redo() {
        if(redoStack.isEmpty())
            return;
        var operation = redoStack.pop();
        switch (operation.operationType) {
            case APPEND -> {
                append(operation.getStringOperands().get(0));
                undoStack.clear();
            }
            case INSERT -> {
                insert(operation.getStringOperands().get(0), operation.getIntOperands().get(0));
                undoStack.clear();
            }
            case DELETE -> {
                remove(operation.getIntOperands().get(0), operation.getStringOperands().get(0).length());
                undoStack.clear();
            }
            case DELETE_STRING -> {
                delete(operation.getStringOperands().get(0));
                undoStack.clear();
            }
        }
    }
}
