/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.sys;

import smpl.values.SMPLFunction;
import smpl.values.*;
import java.util.ArrayList;

public interface SMPLContext {

    /**
     * Create a new context in which the function environment is extended with
     * new bindings.
     * @param fParams The names to be bound in the new function frame.
     * @param args The corresponding values for the names
     * @return A newly created context containing the new function environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendF(ArrayList<String> fParams, ArrayList<SMPLFunction> args);

    /**
     * Create a new context in which the numerical environment is extended with
     * new bindings.
     * @param nParams The names to be bound in the new numerical environment
     * frame.
     * @param vals The corresponding values for the names
     * @return A newly created context containing the new numerical environment,
     * but leaving all the other components of the context unchanged.
     */
    public SMPLContext extendD(ArrayList<String> nParams, ArrayList<Double> vals);

    /**
     * Lookup a reference to a SMPL function.
     * @param name The identifier of the SMPL function
     * @return The SMPL function associated with the given name in this context
     * @throws SMPLException if the name is not bound to a primitive in this
     * context
     */
    public SMPLFunction getF(String name) throws SMPLException;

    /**
     * Lookup a reference to a number
     * @param name The identifier of the Double
     * @return The number associated with the given name in this context
     * @throws SMPLException if the name is not bound to a number in this
     * context
     */
    public Object getP(String name) throws SMPLException;

    /**
     *
     * @return The numerical environment associated with this context.
     */
    public SMPLEnvironment<Object> getPEnv();

    /**
     *
     * @return The function environment associated with this context.
     */
    public SMPLEnvironment<SMPLFunction> getFunEnv();

    /**
     * Store a binding for the given name to the given SMPL function.
     * @param name The identifier of the binding
     * @param p The SMPL function to be associated with the name
     */
    public void putF(String name, SMPLFunction p);

    /**
     * Store a binding for the given name to the given number.
     * @param name The identifier of the binding
     * @param n The numerical value to be associated with the name
     */
    public void putP(String name, Object n);
}
