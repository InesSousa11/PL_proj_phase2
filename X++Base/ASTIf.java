class ASTIf implements ASTNode {
    ASTNode cond, ifBranch, elseBranch;

    ASTIf(ASTNode cond, ASTNode ifBranch, ASTNode elseBranch) {
        this.cond = cond;
        this.ifBranch = ifBranch;
        this.elseBranch = elseBranch;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = cond.eval(env);
        if (!(v instanceof VBool)) {
            throw new InterpreterError("Condition of if must be a boolean");
        }
        if (((VBool) v).getval()) {
            return ifBranch.eval(env);
        }
        else {
            return elseBranch.eval(env);
        }
    }
}
