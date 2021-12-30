package smpl.values;

/* 
* Primitive variable implementation, made to be dynamic to any type. 
* PT: Type of the variable
*/

public abstract class Primitive<PT> {
    public static final Primitive DEFAULT = new SimplePrimitive<String>("DEFAULT");

    public abstract PT getPrimitive();
}
