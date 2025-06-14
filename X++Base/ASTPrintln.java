public	class ASTPrintln implements ASTNode	{

    ASTNode expr;

    public ASTPrintln(ASTNode expr)	{
        this.expr = expr;
    }

    public IValue eval(Environment<IValue> env)	throws
            InterpreterError {
        IValue val = expr.eval(env);
        System.out.println(val.toStr());
        return new VVoid(); // prevents extra output
    }

}
