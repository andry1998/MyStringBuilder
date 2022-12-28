import java.util.List;

public class HistoryOperation {

    public OperationType operationType;

    public List<String> stringOperands;

    public List<Integer> intOperands;
//
//    public List<String> stringResults;
//
//    public List<String> intResults;

    public HistoryOperation(OperationType operationType, List<String> stringOperands, List<Integer> intOperands) {
        this.operationType = operationType;
        this.stringOperands = stringOperands;
        this.intOperands = intOperands;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public List<String> getStringOperands() {
        return stringOperands;
    }

    public void setStringOperands(List<String> stringOperands) {
        this.stringOperands = stringOperands;
    }

    public List<Integer> getIntOperands() {
        return intOperands;
    }

    public void setIntOperands(List<Integer> intOperands) {
        this.intOperands = intOperands;
    }
}
