package smpl.sys;

import smpl.values.*;
import java.util.ArrayList;

public class SMPLCompositer implements SMPLContext {
  public SMPLEnvironment<Object> primEnv;
  public SMPLEnvironment<SMPLFunction> funEnv;
  
  public SMPLCompositer(SMPLEnvironment<Object> primEnv, SMPLEnvironment<SMPLFunction> funEnv) {
    this.primEnv = primEnv;
    this.funEnv = funEnv;
  }

  public SMPLCompositer() {
    this(new SMPLEnvironment<Object>(), new SMPLEnvironment<SMPLFunction>());
  }

  public SMPLContext extendF(ArrayList<String> fParams, ArrayList<SMPLFunction> args) {
    return new SMPLCompositer(primEnv, new SMPLEnvironment(funEnv, fParams, args));
  }

  public SMPLContext extendD(ArrayList<String> nParams, ArrayList<Double> vals) {
    return new SMPLCompositer(new SMPLEnvironment(primEnv, nParams, vals), funEnv);
  }

  public SMPLFunction getF(String name) throws SMPLException {
    return funEnv.get(name);
  }

  public Object getP(String name) throws SMPLException {
    return primEnv.get(name);
  }

  public SMPLEnvironment<Object> getPEnv() {
    return this.primEnv;
  }

  public SMPLEnvironment<SMPLFunction> getFunEnv() {
    return this.funEnv;
  }

  public void putF(String name, SMPLFunction f) {
    funEnv.put(name, f);
  }

  public void putP(String name, Object n) {
    primEnv.put(name, n);
  }
}