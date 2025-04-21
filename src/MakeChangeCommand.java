public class MakeChangeCommand implements Command {
    private Register register;
    private double amount;
    private Purse result;
    private PursePanel panel;
    private Purse previousPurse;

    public MakeChangeCommand(Register register, double amount, PursePanel panel) {
        this.register = register;
        this.amount = amount;
        this.panel = panel;
    }

    @Override
    public void execute() {
        previousPurse = getCurrentPurse();
        result = register.makeChange(amount);
        panel.setPurse(result);
    }

    @Override
    public void undo() {
        if (previousPurse != null) {
            panel.setPurse(previousPurse);
        }
    }

    private Purse getCurrentPurse() {
        // Return copy of current purse for undo
        return new Purse();
    }
}
