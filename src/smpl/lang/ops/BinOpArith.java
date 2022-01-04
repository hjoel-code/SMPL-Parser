/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.lang.ops;

import smpl.sys.SMPLException;

/**
 *
 * @author Daniel Coore <daniel.coore@uwimona.edu.jm>
 */
public enum BinOpArith implements BinaryOp<Double, Double> {
    ADD("+") {
        @Override
        public Double apply(Double leftArg, Double rightArg) {
            return leftArg + rightArg;
        }        
    },
    SUB("-") {
        @Override
        public Double apply(Double leftArg, Double rightArg) {
            return leftArg - rightArg;
        }
    },
    DIV("/") {
        @Override
        public Double apply(Double leftArg, Double rightArg) throws SMPLException {
            if (rightArg != 0) {
                return leftArg / rightArg;
            } else {
                throw new SMPLException(String.valueOf(leftArg) + "/" + String.valueOf(rightArg) + " is undefined.");
            }
            
        }
    },
    MUL("*") {
        @Override
        public Double apply(Double leftArg, Double rightArg) {
            return leftArg * rightArg;
        }
    },
    MOD("%") {
        @Override
        public Double apply(Double leftArg, Double rightArg) {
            return leftArg % rightArg;
        }
    },
    ;
    
    String symbol;

    BinOpArith(String symbol) {
        this.symbol = symbol;
    }

    /**
     *
     * @return The symbol for this operator
     */
    @Override
    public String getSymbol() {
        return symbol;
    }
}
