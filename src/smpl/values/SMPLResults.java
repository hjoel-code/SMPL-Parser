package smpl.values;

import java.util.ArrayList;

public class SMPLResults extends Primitive<Primitive> {


    ArrayList<Primitive> results;

    public static final SMPLResults DEFAULT = new SMPLResults();


    public SMPLResults() {
        super("result");
        this.results = new ArrayList<>();
    }

    public void addPrimitive(Primitive result) {
        this.results.add(result);
    }

    

    @Override
    public Primitive getPrimitive() {
        int len = results.size();
        if (len == 0) {
            return null;
        }
        return results.get(len-1);
    }

    @Override
    public String getOutput() {
        String result = "";

        for (Primitive priv : this.results) {
            result += priv.getOutput();
        }

        return result;
    }
    
}
