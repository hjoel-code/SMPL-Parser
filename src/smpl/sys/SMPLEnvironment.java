package smpl.sys;

import java.util.*;
import smpl.values.SMPLFunction;

/**
 * An instance of class <code>SMPLEnvironment</code> maintains a
 * collection of bindings from valid identifiers to T objects.
 * It supports storing and retrieving bindings, just as would be
 * expected in any dictionary.
 *
 * @author <a href="mailto:dcoore@uwimona.edu.jm">Daniel Coore</a>
 * @version 1.0
 * @param <T> The type of datum to be bound to variables in this environment
 */
public class SMPLEnvironment<T> {

    HashMap<String, T> dictionary;
    SMPLEnvironment<T> parent;
//    HashMap<String, SMPLFunction> fnBindings;

    /**
     * Create a new (empty) top level SMPL Environment.
     *
     */
    public SMPLEnvironment() {
	parent = null;		// global by default
	dictionary = new HashMap<>();
//        fnBindings = new HashMap<>();
    }

    /**
     * Creates a new <code>SMPLEnvironment</code> instance that is
     * initialised with the given collection of bindings (presented as
     * separate arrays of names and values), and extends the given
     * environment.
     *
     * @param p The parent environment that this one extends.
     * @param ids the collection of identifiers to be bound.
     * @param values the corresponding collection of values for the
     * identifiers.  Note that the two arrays must have the same length.
     */
    public SMPLEnvironment(SMPLEnvironment<T> p, ArrayList<String> ids, 
            ArrayList<T> values) {
        this();
	parent = p;
	for (int i = 0; i < ids.size(); i++) {
	    dictionary.put(ids.get(i), values.get(i));
	}
    }

    /**
     * Store a binding for the given identifier to the given T
     * object within this environment.
     *
     * @param id the name to be bound
     * @param value the value to which the name is bound.
     */
    public void put(String id, T value) {
	dictionary.put(id, value);
    }

    /**
     * Return the T object associated with the given identifier.
     *
     * @param id the identifier.
     * @return the T object associated with the identifier in
     * this environment.
     * @exception SMPLException if <code>id</code> is unbound
     */
    public T get(String id) throws SMPLException {
	T result =  dictionary.get(id);
	if (result == null)
            if (parent == null)
                throw new SMPLException("Unbound variable " + id);
            else
                return parent.get(id);
	else
	    return result;
    }
//    
//    /**
//     * Store a binding to a function.
//     * @param id The name of the function
//     * @param fn The function object to be bound.
//     */
//    public void putFn(String id, SMPLFunction fn) {
//        fnBindings.put(id, fn);
//    }
//    
//    /**
//     * Retrieve the function bound to the given name.
//     * @param id The name of the function
//     * @return The value of the nearest binding for the given name in the 
//     * function name space.
//     * @throws SMPLException if no function is bound to the given name either in
//     * this frame's function namespace or in that of any of its ancestors.
//     */
//    public SMPLFunction getFn(String id) throws SMPLException {
//        SMPLFunction result = fnBindings.get(id);
//        if (result == null)
//            if (parent == null)
//                throw new SMPLException("Unbound function " + id);
//            else
//                return parent.getFn(id);
//        else
//            return result;
//    }

    /**
     * Create a string representation of this SMPL environment.
     *
     * @return a string of all the names bound in this environment.
     */
    @Override
    public String toString() {
	StringBuffer result = new StringBuffer();

	for (String name : dictionary.keySet()) {
	    result = result.append(name);
	    result = result.append(" ");
	}
	return result.toString();
    }

}
