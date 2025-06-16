class ASTNot implements ASTNode  {
    ASTNode expr;

    ASTNot(ASTNode expr) {
        this.expr = expr;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError
    {
        IValue val = expr.eval(env);

        if (!(val instanceof VBool)) {
            throw new InterpreterError("Expected boolean in 'not' operation, got: " + val.getClass().getSimpleName());
        }

        boolean b = ((VBool) val).getval();
        return new VBool(!b);
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType t = expr.typecheck(env);

        if (!(t instanceof ASTTBool)) {
            throw new TypeError("Operand of not must be of type bool.");
        }

        return new ASTTBool();
    }
}
