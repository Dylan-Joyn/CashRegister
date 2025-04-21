import java.util.Stack;

public class CommandHistory {
    private Stack<Command> history = new Stack<>();

    public void add(Command command) {
        history.push(command);
    }

    public void undo() {
        if (!history.isEmpty()) {
            Command command = history.pop();
            command.undo();
        }
    }

    public boolean canUndo() {
        return !history.isEmpty();
    }
}