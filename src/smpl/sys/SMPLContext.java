package smpl.sys;

import smpl.values.*;

public interface SMPLContext {
    

    public abstract Environment<Primitive> getGlobalEnvironment();
    public abstract Environment<String> getVariableEnvironment();
    public abstract SMPLContext extendEnvironment();


}
