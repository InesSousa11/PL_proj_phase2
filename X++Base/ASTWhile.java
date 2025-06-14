class ASTWhile implements ASTNode {
    ASTNode cond;
    ASTNode body;

    ASTWhile(ASTNode cond, ASTNode body) {
        this.cond = cond;
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = cond.eval(env);
        if (!(v instanceof VBool))
            throw new InterpreterError("Condition of while must be a boolean.");

        while (((VBool) v).getval()) {
            body.eval(env);
            v = cond.eval(env);
            if (!(v instanceof VBool))
                throw new InterpreterError("Condition of while must be a boolean.");
        }
        return new VVoid();
    }
}