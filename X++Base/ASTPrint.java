public	class ASTPrint implements ASTNode	{

    ASTNode expr;

    public ASTPrint(ASTNode expr)	{
        this.expr = expr;
    }

    public IValue eval(Environment<IValue> env)	throws
            InterpreterError {
        IValue val = expr.eval(env);
        System.out.print(val.toStr());
        return new VVoid(); // prevents extra output
    }

}
