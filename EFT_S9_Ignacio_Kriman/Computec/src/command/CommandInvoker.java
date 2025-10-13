package command;

public class CommandInvoker {

    public void run(Command cmd) {
        cmd.execute();
    }
}
