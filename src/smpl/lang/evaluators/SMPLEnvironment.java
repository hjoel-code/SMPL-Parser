package smpl.lang.evaluators;

import smpl.sys.SMPLContext;
import smpl.sys.Environment;



import smpl.values.*;

public class SMPLEnvironment implements SMPLContext {

    Environment<Primitive> globalEnvironment;
    Environment<String> variableEnvironment;

    public SMPLEnvironment() {
        globalEnvironment = new Environment<>(this);
        variableEnvironment = new Environment<>(this);
    }


    public Environment<String> getVariableEnvironment() {
        return variableEnvironment;
    }

    @Override
    public Environment<Primitive> getGlobalEnvironment() {
        return globalEnvironment;
    }

    @Override
    public SMPLContext extendEnvironment() {
        return this.cloneEnvironment();
    }

    

    public SMPLEnvironment  cloneEnvironment() {
        SMPLEnvironment context = new SMPLEnvironment();
        context.globalEnvironment = this.globalEnvironment;
        context.variableEnvironment = this.variableEnvironment;
        return context;
    }

    

}