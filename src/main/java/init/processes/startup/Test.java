package init.processes.startup;

import com.badlogic.gdx.math.MathUtils;
import init.core.GameProcess;

public class Test extends GameProcess {
    private static final String top =
            "                         | |\n";
    private static final String tower =
                    "                         | |\n" +
                    "                         |-|\n" +
                    "                         | |\n" +
                    "                         |-|\n" +
                    "                         | |\n" +
                    "                         |-|\n" +
                    "                         | |\n" +
                    "                         |-|\n" +
                    "                        /   \\\n" +
                    "                        |_ -|\n" +
                    "                        | - |\n" +
                    "                        |_ -|\n" +
                    "                        |_#-|\n" +
                    "                        | _#|\n" +
                    "                        |_ -|\n" +
                    "   ________ .$$. ______ | - | _____________\n" +
                    "           .#$$$. __    |-  | ....__\n" +
                    "     _.--' $$$$$$    ` -[__N]        `--a:f-\n" +
                    "           $$$$$$    -.\n" +
                    "      -.    `:/'    _.))        .--.\n" +
                    "             ||   .'.-'     _..-.. _.-.\n" +
                    "      ._.-.  '\"  /  (     .'      `.\n" +
                    "    -'     `.   .    `. -'\n" +
                    "             `. .      `--..\n" +
                    "                 `.";

    int i = 0;
    int lines = 0;
    int max = MathUtils.random(2, 6);
    StringBuilder lastLine = new StringBuilder();

    @Override
    public GameProcess update() {
        clrscr();

        if (i < 2000) {
            for (int a = i; i < a + 10; ++i)
            {
                char c = (char) MathUtils.random(32, 666);

                output(c);

                if (i >= 1900)
                    lastLine.append(c);
            }
            return super.update();
        }

        if (lines < max) {
            ++lines;
            System.out.print(lastLine);
            return super.update();
        }

        for (int i = 0; i < 100; ++i)
        {
            output(top);
        }
        output(tower);


        return new Enable();
    }
}
