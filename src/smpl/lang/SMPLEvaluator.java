package smpl.lang;

import smpl.values.*;
import smpl.sys.*;
import java.util.*;

public class SMPLEvaluator implements SMPLVisitor<SMPLContext, Primitive> {
    private final ArithEvaluator arithEval;
    HashMap<String, SIRBuiltInFun> builtInFunMap;
    HashMap<String, BinOpArith> binOpsMap;
    Primitive lastResult;

    public SMPLEvaluator() {
       this.arithEval = new ArithEvaluator();
       init();
    }

    private void init() {
       lastResult = Primitive.DEFAULT;

       builtInFunMap = new HashMap<>();
        for (SIRBuiltInFun builtInFun : SIRBuiltInFun.values()) {
            builtInFunMap.put(builtInFun.getSymbol(), builtInFun);
        }

        binOpsMap = new HashMap<>();
        for (BinOpArith op: BinOpArith.values()) {
            binOpsMap.put(op.getSymbol(), op);
        }
    }

    public Primitive getResult() {
    	return lastResult;
    }

    /**
     * EvAluate a program, returning the lastResulting frame.
     * @param program The program to be evaluated
     * @param env The top level environment providing bindings to the program
     * @return The frame that results from evaluating the program.  This is 
         not usually a useful value, because the objective of a program is usually
         to display a Primitive (ie a side effect).
     * @throws SMPLException if any semantic errors are encountered during 
     * evaluation
     */
    @Override
    public Primitive visitSIRProgram(SIRProgram program, SMPLContext env) 
            throws SMPLException {
        
    	// traverse the program with the given environment
    	// to obtain the resulting primitive object.
        SIRSequence stmts = program.getSeq();
    	Primitive tmp = stmts.visit(this, env);
    	// don't record null results, so that image on screen will persist
    	if (tmp != Primitive.DEFAULT)
    	   lastResult = tmp;
    	   return lastResult;
    }

    /* Primitive special forms */

    /**
     * Evaluate an assignment statement (creating a binding for the variable on
     * the LHS of the assignment to the value obtained from evaluating the RHS
     * of the assignment).
     * @param assignment The assignment statement
     * @param context The context in which the assignment should be evaluated
     * @return The primitive value yielded by the right hand side of the assignment
     * @throws SMPLException
     */
    @Override   
    public Primitive visitSIRAssignment(SIRAssignment assignment,
                                      SMPLContext context) throws SMPLException {
    	Primitive result = assignment.getExp().visit(this, context);
    	context.putP(assignment.getVar(), result);

    	return lastResult;
    }


    /**
     * Evaluate a statement
     * @param stmt The statement to be evaluated.
     * @param env The environment w.r.t which the sequence is to be evaluated
     * @return The result of the last statement in the sequence.
     * @throws SMPLException if an error was encountered in the sequence.
     */
    @Override 
    public Primitive visitSMPLStatement(SIRStatement stmt, SMPLContext env) throws SMPLException {
        return stmt.getExp().visit(this, env);
    }

    /**
     * Evaluate a sequence of statements
     * @param seq The statement sequence to be evaluated.
     * @param env The environment w.r.t which the sequence is to be evaluated
     * @return The result of the last statement in the sequence.
     * @throws SMPLException if an error was encountered in the sequence.
     */
    @Override
    public Primitive visitSIRSequence(SIRSequence seq, SMPLContext env)
	throws SMPLException
    {
    	ArrayList<SIRStatement> stmts = seq.getStatements();
    	Primitive result = Primitive.DEFAULT;

        for (SIRStatement stmt : stmts) {
            result = stmt.visit(this, env);
        }

    	return result;
    }

    /**
     * @return a freshly created global context suitable for visiting top level
     * expressions.
     */
    public SMPLContext mkInitialContext() {
        return new SMPLCompositer();
    }

    // *** Implement a method for function definition (according to your
    //     modifications to SMPLVisitor interface)
    @Override
    public Primitive visitSIRStatementDef(SIRStatementDef stmtDef, SMPLContext context) throws SMPLException {
        // Todo : finish implementation
        return lastResult;
    }

    /**
     * Evaluate a function call.
     * @param funCall The function call expression
     * @param context The environment w.r.t. which the call is evaluated
     * @return The (primitive) object that arises from applying the function.
     * @throws smpl.sys.SMPLException if something goes wrong while invoking the 
     * function call.
     */
    @Override
    public Primitive visitSIRFunCall(SIRFunCall funCall, SMPLContext context) throws SMPLException {
        String funName = funCall.getFunName();
        SIRBuiltInFun builtInFun = builtInFunMap.get(funName);

        if (builtInFun != null) {
           return builtInFun.apply(context, this);
        }

        // Todo: Finish implementation

        return lastResult;
    }
    
    @Override
    public Primitive visitSIRConditionalStmt(SIRConditionalStmt conditionalStmt, SMPLContext env) throws SMPLException {
        // Todo: Finish implementation
        return lastResult;
    }

    @Override 
    public Primitive visitSIRBinaryExp(SIRBinaryExp sBinOp, SMPLContext env) throws SMPLException {
        // Todo: handle both doubles and integers, dont only return double
        // 3 + 3 = 6
        // 3.0 + 3.0 = 6.0

        String opName = sBinOp.getOperator();
        BinOpArith binOp = binOpsMap.get(opName);

        SIRExp leftExp = sBinOp.getExp1();
        SIRExp rightExp = sBinOp.getExp2();

        Primitive<Integer> leftArg = leftExp.visit(this, env);
        Primitive<Integer> rightArg = rightExp.visit(this, env);
        Integer leftArgVal = leftArg.getPrimitive();
        Integer rightArgVal = rightArg.getPrimitive();

        return new SimplePrimitive<Double>(binOp.apply(Double.valueOf(leftArgVal), Double.valueOf(rightArgVal)));
    }

    /**
     * Evaluate a primitive variable reference.
     * @param var The variable referencing a primitive
     * @param context The context containing the environment in which to look
     * up the variable.
     * @return The primitive object bound to the given variable.
     * @throws SMPLException if there is no primitive bound to the given variable.
     */
    @Override
    public Primitive visitSIRVar(SIRVar var, SMPLContext context)
	throws SMPLException {
    	String id = var.getId();
        SIRPrimitiveSelector objSelector = new SIRPrimitiveSelector(context.getP(id), id);

        return objSelector.getPrimitive();
    }

    @Override
    public Primitive visitSIRLit(SIRLit sirLit, SMPLContext context) throws SMPLException {
        return new SimplePrimitive<Integer>(sirLit.getVal());
    }
}
