package smpl.lang.ops;

public enum BinOpBool implements BinaryOp<Double, Boolean> {
    
    GT(">") {
        @Override
        public Boolean apply(Double arg1, Double arg2) {
            return arg1 > arg2;
        }
    },


    LT("<") {
        @Override
        public Boolean apply(Double arg1, Double arg2) {
            return arg1 < arg2;
        }
    },


    EQ("=") {
        @Override
        public Boolean apply(Double arg1, Double arg2) {
            return arg1 == arg2;
        }
    }, 

    GTE(">=") {
        @Override
        public Boolean apply(Double arg1, Double arg2) {
            return arg1 >= arg2;
        }
    }, 

    LTE("<=") {
        @Override
        public Boolean apply(Double arg1, Double arg2) {
            return arg1 <= arg2;
        }
    };

    
    String symbol;

    BinOpBool(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
