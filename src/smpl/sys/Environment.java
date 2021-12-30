package smpl.sys;

import java.util.ArrayList;
import java.util.HashMap;




public class Environment<T> {

    HashMap<String, T> dictionary;
    private SMPLContext context;

    public Environment() {
        dictionary = new HashMap<>();
    }

    public Environment(SMPLContext context) {
        this();
        this.context = context;
    }

    public SMPLContext getContext() {
        return context;
    }

    public Environment(Environment<T> p, ArrayList<String> ids, ArrayList<T> values) {
        for (int i = 0; i < ids.size(); i++) {
            dictionary.put(ids.get(i), values.get(i));
        }
    }

    public void put(String id, T value) {
        dictionary.put(id, value);
    }

    public T get(String id) throws SMPLException {
        T result = dictionary.get(id);
        if (result == null)
            throw new SMPLException("Unbound variable " + id);
        else
            return result;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();

        for (String name : dictionary.keySet()) {
            result = result.append(name);
            result = result.append(" ");
        }
        return result.toString();
    }

    public void print() {
        for (String key: dictionary.keySet()){  
			System.out.println(key+ " = " + dictionary.get(key));
		} 
    }
}
