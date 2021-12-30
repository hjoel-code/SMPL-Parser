package smpl.values;

import smpl.sys.*;
import smpl.lang.*;
import java.util.*;

/**
 * Representation for SMPL Functions. SMPL is statically scoped, so we have
 * to maintain a closing environment for a function.
 */
public class SMPLFunction {

    String name;
    ArrayList<String> numericalParams;
    SIRSequence body;
    SMPLContext closingEnv;

    /**
     * Create a new function instance with a given name, parameter list, body
     * and closing environment.
     * @param id The name of the function
     * @param nParams The function's formal numerical parameters.
     * @param b The body of the function
     * @param env The closing environment of the function.
     */
    public SMPLFunction(String id, ArrayList<String> nParams,
                       SIRSequence b, SMPLContext env) {
	   name = id;
       numericalParams = nParams;
	   body = b;
       closingEnv = env;
    }

    /**
     * @return The function's name
     */
    public String getName() {
        return name;
    }

    public ArrayList<String> getNumericalParams() {
        return numericalParams;
    }

    /**
     * @return The body of this function
     */
    public SIRSequence getBody() {
        return body;
    }

    /**
     * @return The closing environment for this function
     */
    public SMPLContext getClosingEnv() {
        return closingEnv;
    }
}
