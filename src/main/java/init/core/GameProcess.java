package init.core;

import init.init;

import static init.init.io;

public abstract class GameProcess {
    public GameProcess update()
    {
        io.processInput(false);
        return this;
    } //return self, or return a different process to change processes. Return null to end.

    public void clrscr()
    {
        init.io.clrscr();
    }
    public void output(String s)
    {
        init.io.output(s);
    }
    public void output(char c)
    {
        init.io.output(c);
    }
    public void outputln() {
        outputln("");
    }
    public void outputln(String s)
    {
        init.io.outputln(s);
    }
    public void sleep(long ms)
    {
        init.io.sleep(ms);
    }
}
