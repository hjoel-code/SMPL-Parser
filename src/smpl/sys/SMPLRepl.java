package smpl.sys;

import java.io.*;
import smpl.lang.*;
import smpl.values.*;

public class SMPLRepl {
    static SMPLEvaluator interp;
    static SMPLContext globalEnv;

    /**
     * Setup a global environment, execute any programs supplied on
     * the command line, and if desired execute the Read Eval Print
     * Loop.
     *
     * @param args a <code>String[]</code> value
     */
    public static void main(String[] args) {
	// setup graphics, interpreter and global environment
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

	if (interactive)
	    repl(System.in, globalEnv);
    }

    public static void setup() {
    	interp = new SMPLEvaluator(); 
        globalEnv = interp.mkInitialContext();
    }

    /**
     * In an infinite loop: read SMPL commands in from standard input,
 evaluate them as a sequence with respect to the given
 environment, and display the lastResult.
     *
     * @param is the input stream of characters encoding the SMPL commands
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
                    input.append("\n");
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
     * @param r The reader containing the program fragment to be interpreted
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

    	Primitive result;
    	if (commands != null) {
    	    try {
        		result = commands.visit(interp, env);
        		if (result != Primitive.DEFAULT) {
                    System.out.println("\n" + result.getPrimitive());
        		} else {
        		    System.out.println("\nNo result");
        		}
    	    } catch (SMPLException smple) {
    		  System.out.println("Runtime Error: " + smple.report());
    	    }
        }
    }

}
