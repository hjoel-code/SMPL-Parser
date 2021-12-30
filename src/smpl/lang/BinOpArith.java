package smpl.lang;

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
        public Double apply(Double leftArg, Double rightArg) {
            return leftArg / rightArg;
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
     * @return The symbol for this operator
     */
    @Override
    public String getSymbol() {
        return symbol;
    }
}
