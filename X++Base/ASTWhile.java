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

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType condType = cond.typecheck(env);
        if (!(condType instanceof ASTTBool)) {
            throw new TypeError("Condition of 'while' must be of type bool.");
        }

        body.typecheck(env);  // Typechecking for side effects / scoping
        return new ASTTUnit();  // 'while' returns nothing meaningful
    }
}