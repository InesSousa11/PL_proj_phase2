public class ASTBox implements ASTNode	{
    ASTNode v;

    public ASTBox(ASTNode v)	{
        this.v = v;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        if (v instanceof ASTSeq) {
            throw new InterpreterError("Box must contain exactly one expression, not a sequence.");
        }

        return new VBox(v.eval(env));
    }
}
