package init.processes.startup;

import init.core.GameProcess;

import java.awt.*;

import static init.init.window;

public class Intro extends GameProcess {
    @Override
    public GameProcess update() {
        super.update();
        clrscr();

        window.dispose();
        window.setTitle("");
        window.setResizable(false);
        window.setUndecorated(true);
        window.setExtendedState(Frame.MAXIMIZED_BOTH);

        window.pack();
        window.setLocation(0, 0);
        window.setVisible(true);

        output("Testing Stability. ");


        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 50; ++i) {
            sb.setLength(0);
            sb.append("1");
            sb.append(" 2").append(" 3").append(" ");

            output(sb.toString());
        }

        return new Test();

        /*for (int i = 0; i < 10; ++i)
        {
            sleep(500);
            output(". ");
        }
        outputln("\nInitializing test.");
        sleep(150);
        return new Enable();*/
    }
}
