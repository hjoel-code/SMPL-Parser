package smpl.lang.ops;

public enum BinOpLogic implements BinaryOp<Boolean, Boolean> {
    

    AND("and") {
        @Override
        public Boolean apply(Boolean arg1, Boolean arg2) {
            return arg1 && arg2;
        }
    },


    OR("or") {
        @Override
        public Boolean apply(Boolean arg1, Boolean arg2) {
            return arg1 || arg2;
        }
    },


    NOT("not") {
        @Override
        public Boolean apply(Boolean arg1, Boolean arg2) {
            return !arg1;
        }
    };


    String symbol;

    BinOpLogic(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }
}
