package init.core;

import javax.swing.*;
import javax.swing.text.*;

public class IO {
    private JTextArea source;
    private Document d;
    private static boolean inputActive = false;
    private static boolean useFilter = true;
    private static boolean clearBypass = false;
    public static String lastIn = "";
    public static String totalInput = "";

    public IO(JTextArea source)
    {
        this.source = source;
        this.d = source.getDocument();
    }

    public void clrscr() {
        try {
            clearBypass = true;
            d.remove(0, d.getLength());
        }
        catch (Exception ignored)
        {
            clearBypass = false;
        }
    }

    public String clearInput()
    {
        String returnVal = totalInput;
        totalInput = "";
        lastIn = "";

        return returnVal;
    }

    public void processInput(boolean output)
    {
        if (!lastIn.isEmpty())
        {
            if (output)
                output(lastIn);
            lastIn = "";
        }
    }
    public void outputln(String s)
    {
        useFilter = false;
        System.out.println(s);
    }
    public void output(String s)
    {
        useFilter = false;
        System.out.print(s);
    }
    public void output(char c)
    {
        useFilter = false;
        System.out.print(c);
    }

    public boolean activateInput()
    {
        source.requestFocusInWindow();
        if (!inputActive)
        {
            inputActive = true;
            source.setEditable(true);

            if (d instanceof AbstractDocument)
            {
                InputFilter f = new InputFilter(d);

                ((AbstractDocument) d).setDocumentFilter(f);
            }
            else
            {
                outputln("Error occurred. Local source not of correct type.");
                //error!
                return false;
            }
        }
        else {
            clearInput();
        }
        return true;
    }

    public String input() {
        return totalInput;
    }

    public String newInput() {
        return lastIn;
    }

    public boolean hasInput() {
        return totalInput.length() > 0;
    }


    public void sleep(long ms)
    {
        try {
            Thread.sleep(ms);
        }
        catch (Exception e)
        {
            outputln("Cannot sleep. Enemies are nearby.");
        }
    }


    private static class InputFilter extends DocumentFilter {
        private Document d;

        public InputFilter(Document d)
        {
            this.d = d;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {
            if (!useFilter)
            {
                super.insertString(fb, d.getLength(), string, attr);
                useFilter = true;
            }
            else
            {
                lastIn += string;
                if (inputActive)
                {
                    totalInput += lastIn;
                }
                //super.insertString(fb, d.getLength(), string, attr);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            if (!useFilter || clearBypass)
                super.remove(fb, offset, length);

            clearBypass = false;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (!useFilter)
                super.replace(fb, offset, length, text, attrs);
            else
            {
                lastIn += text;
                if (inputActive)
                {
                    totalInput += lastIn;
                }
                //super.insertString(fb, d.getLength(), text, attrs);
            }
        }
    }
}
