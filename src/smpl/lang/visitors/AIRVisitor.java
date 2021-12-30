package smpl.lang.visitors;

import smpl.sys.SMPLException;
import smpl.lang.*;
import smpl.lang.arith.AIRLit;
import smpl.lang.arith.ArithCalc;
/**
 * Visitor interface for arithmetic expressions within SMPL contexts
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 * @param <S> The type of the state used by the visitor
 * @param <T> The type returned by the visitor
 */
public interface AIRVisitor<E extends SIRExp<E>, S, T> extends SIRVisitor<E,S,T>   {

    public T visitAIRExpInt(AIRLit exp, S state) throws SMPLException; 
    public T visitArithCalc(ArithCalc exp, S state) throws SMPLException;
    
}