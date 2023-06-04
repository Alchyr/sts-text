package init;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.ui.ModSelectWindow;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import init.processes.startup.Intro;
import init.core.GameProcess;
import init.core.IO;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.lang.reflect.Field;

import static java.lang.Thread.sleep;

public class init {
    private static final String prefix = "" + (char) 239;

    public static ModSelectWindow window;
    public static IO io;

    public static class test
    {
        @SpirePatch(
                clz = CardCrawlGame.class,
                method = SpirePatch.CONSTRUCTOR
        )
        public static class P
        {
            @SpireInsertPatch(
                    rloc = 0
            )
            public static void i(CardCrawlGame __instance, String s)
            {

            }
        }

        @SpireEnum
        public static CardCrawlGame.GameMode f; //causes class to be loaded

        static {
            try
            {
                init();
            }
            catch (Exception ignored)
            {
                System.out.println("AAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\nAAAAAAAAAAAAA\n");
            }
        }

        public static void init() throws InterruptedException {
            GameProcess run = null;

            try {
                Field windowField = Loader.class.getDeclaredField("ex");
                windowField.setAccessible(true);
                window = (ModSelectWindow) windowField.get(null);

                outer: for (Component a : window.getContentPane().getComponents())
                {
                    if (a instanceof JScrollPane)
                    {
                        for (Component b : ((JScrollPane) a).getViewport().getComponents())
                        {
                            if (b instanceof JTextArea)
                            {
                                JTextArea in = (JTextArea) b;
                                //this is the text area that displays log text.
                                Document d = in.getDocument();

                                io = new IO(in);
                                io.activateInput();
                                break outer;
                            }
                        }
                    }
                }

                run = new Intro();
            }
            catch (Exception e)
            {
                run = null;
            }


            while (run != null && !IO.lastIn.contains("`")) {
                run = run.update();
                Thread.sleep(5);
            }

            System.exit(0);
        }
    }
}
