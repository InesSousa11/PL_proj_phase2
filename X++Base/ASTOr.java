class ASTOr implements ASTNode {

    ASTNode lhs, rhs;

    ASTOr(ASTNode lhs, ASTNode rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue l = lhs.eval(env);
        IValue r = rhs.eval(env);

        if (l instanceof VBool && r instanceof VBool) {
            return new VBool(((VBool) l).getval() || ((VBool) r).getval());
        }
        throw new InterpreterError("Expected booleans in ||");
    }

}