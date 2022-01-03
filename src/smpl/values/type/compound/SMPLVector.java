package smpl.values.type.compound;

import smpl.values.CompoundPrimitive;

import smpl.values.Primitive;

public class SMPLVector extends CompoundPrimitive<Primitive[]>  {

    private Primitive[] arr;

    public SMPLVector(Primitive[] arr) {
        super("vector");
        this.arr = arr;
    }


    public Primitive getElement(int index){
        return arr[index];
    }

    @Override
    public Primitive[] getPrimitive() {
        return arr;
    }
    
    @Override
    public String getOutput() {
        String opt = "[ ";
        
        for (int i = 0; i < arr.length; i++) {

            opt += arr[i].getOutput();
            opt += i == (arr.length-1) ? " " : " ";
        }

        opt += "]";


        return opt;
    }
    
}
