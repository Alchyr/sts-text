package init.processes.startup;

import init.core.GameProcess;

import static init.init.io;

public class Enable extends GameProcess {
    private boolean awaitInput = false;
    @Override
    public GameProcess update() {
        if (!awaitInput) {
            outputln();
            outputln("Expected identity: " + System.getProperty("user.name"));
            output("Is this correct (y/n)? ");
            io.activateInput();
            awaitInput = true;
        }
        else {
            if (io.input().contains("y"))
            {
                io.clearInput();
                clrscr();
                outputln("Processed.");
                return new Name();
            }
            else if (io.newInput().contains("n")) {
                io.clearInput();
                clrscr();
                outputln("Identify yourself.");
                return new Name();
            }
        }

        return super.update();
    }
}
