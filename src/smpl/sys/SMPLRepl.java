package smpl.sys;

import java_cup.runtime.*;
import java.io.*;
import smpl.lang.*;
import smpl.lang.evaluators.*;
import smpl.values.Primitive;

public class SMPLRepl {
    static SMPLEvaluator interp;
    static SMPLContext globalEnv;

    private static final String MESSAGE = "Type your input at the prompt." +
            "  Terminate with a '.' on a line by itself.\n" +
            "Quit by entering a '.' as the only line or by sending EOF to input.";

    public static void main(String args[]) {
        setup();

        boolean interactive = true;
        for (String arg : args) {
            if (arg.equals("-e")) {
                interactive = false;
            } else {
                try {
                    parseEvalShow(new FileReader(new File(arg)), globalEnv);
                } catch (FileNotFoundException fnfe) {
                    System.out.println("Could not find file " + arg);
                }
            }
        }

        if (interactive) {
            repl(System.in, globalEnv);
        }
    }

    public static void setup() {
        interp = new SMPLEvaluator();
        globalEnv = interp.mkInitialContext();
    }

    /**
     * In an infinite loop: read SMPL commands in from standard input,
     * evaluate them as a sequence with respect to the given
     *
     * @param is   the input stream of characters encoding the SMPL commands
     * @param genv a <code>SMPLEnvironment</code> value
     */
    public static void repl(InputStream is, SMPLContext genv) {
        final String FIRST_PROMPT = "SMPL> ";
        final String NEXT_PROMPT = " ...  ";
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try {
            while (true) {
                System.out.print(FIRST_PROMPT);
                StringBuffer input = new StringBuffer();
                String line = reader.readLine();
                while (line != null && !line.equals(".")) {
                    System.out.print(NEXT_PROMPT);
                    input.append(line);
                    line = reader.readLine();
                }
                StringReader r = new StringReader(new String(input));
                parseEvalShow(r, genv);
            }
        } catch (IOException ex) {
            System.out.println("Bye bye!");
        }
    }

    /**
     * 
     * @param r   The reader containing the program fragment to be interpreted
     * @param env The environment w.r.t. which the fragment should be evaluated
     */
    public static void parseEvalShow(Reader r, SMPLContext env) {
        SMPLLexer lexer;
        SMPLParser parser;
        SIRProgram commands = null;

        try {
            lexer = new SMPLLexer(r);
            parser = new SMPLParser(lexer);
            commands = (SIRProgram) parser.parse().value;
        } catch (Exception e) {
            System.out.println("Syntax Error: " + e.getMessage());
        }

        if (commands != null) {
            try {
                Primitive result;
                result = commands.visit(interp, env);
                if (result.getPrimitive() != null) {
                    System.out.println("\n" + result.getOutput());
                } else {
                    System.out.println("\nNo result");
                }
            } catch (SMPLException smple) {
                System.out.println("Runtime Error: " + smple.report());
            }
        }
    }
}