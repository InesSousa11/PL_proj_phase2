class ASTAnd implements ASTNode {

    ASTNode lhs, rhs;

    ASTAnd(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue l = lhs.eval(env);
        IValue r = rhs.eval(env);
        if (l instanceof VBool && r instanceof VBool) {
            return new VBool(((VBool) l).getval() && ((VBool) r).getval());
        }
        throw new InterpreterError("Expected booleans in &&.");
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType leftType = lhs.typecheck(env);
        ASTType rightType = rhs.typecheck(env);

        if (!(leftType instanceof ASTTBool && rightType instanceof ASTTBool)) {
            throw new TypeError("Both sides of '&&' must be of type bool.");
        }

        return new ASTTBool();
    }
}