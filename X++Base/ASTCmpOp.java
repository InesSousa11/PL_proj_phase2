public class ASTCmpOp implements ASTNode {
    Token op;
    ASTNode left, right;

    public ASTCmpOp(Token op, ASTNode left, ASTNode right) {
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public IValue eval(Environment<IValue> env)	throws
            InterpreterError {
        IValue v1 = left.eval(env);
        IValue v2 = right.eval(env);

        if (!(v1 instanceof VInt) || !(v2 instanceof VInt)) {
            throw new InterpreterError("Comparison requires integer operands.");
        }

        int i1 = ((VInt) v1).getval();
        int i2 = ((VInt) v2).getval();

        return switch (op.kind) {
            case ParserConstants.EQ -> new VBool(i1 == i2);
            case ParserConstants.GT -> new VBool(i1 > i2);
            case ParserConstants.LT -> new VBool(i1 < i2);
            case ParserConstants.GE -> new VBool(i1 >= i2);
            case ParserConstants.LE -> new VBool(i1 <= i2);
            case ParserConstants.NEQ -> new VBool(i1 != i2);
            default -> throw new InterpreterError("Unknown operator: " + op.image);
        };
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType t1 = left.typecheck(env);
        ASTType t2 = right.typecheck(env);

        if (!(t1 instanceof ASTTInt) || !(t2 instanceof ASTTInt)) {
            throw new TypeError("Comparison operands must be integers");
        }

        return new ASTTBool();
    }
}
