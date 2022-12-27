import java.util.LinkedList;
import java.util.Stack;

public class AppendCommand implements Command{

    final MyStringBuilder myStringBuilder;

    char c;

    char arr[];

    String s;

    final Stack<String> undoStack = new Stack<>();

    final Stack<String> redoStack = new Stack<>();


    public AppendCommand(MyStringBuilder myStringBuilder, char c) {
        this.myStringBuilder = myStringBuilder;
        this.c = c;
    }

    public AppendCommand(MyStringBuilder myStringBuilder, char[] arr) {
        this.myStringBuilder = myStringBuilder;
        this.arr = arr;
    }

    public AppendCommand(MyStringBuilder myStringBuilder, String str) {
        this.myStringBuilder = myStringBuilder;
        this.s = str;
    }

    public AppendCommand() {
        this.myStringBuilder = null;
    }


    @Override
    public void execute() {
        undoStack.push("append");
    }

    @Override
    public void undo() {
        redoStack.push(undoStack.pop());
    }

    @Override
    public void redo() {

    }
}
