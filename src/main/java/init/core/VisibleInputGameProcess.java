package init.core;

import static init.init.io;

public class VisibleInputGameProcess extends GameProcess {
    public boolean allowInput = true;
    public boolean displayInput = true;

    @Override
    public GameProcess update() {
        io.processInput(displayInput);
        if (!allowInput)
        {
            io.clearInput();
        }

        return this;
    }

    public String getInput()
    {
        return io.clearInput();
    }

    public void enableInput()
    {
        displayInput = true;
        allowInput = true;
    }
    public void disableInput()
    {
        displayInput = false;
        allowInput = false;
    }
}
