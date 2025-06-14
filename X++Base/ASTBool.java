class ASTBool implements ASTNode  {
    boolean b;

    ASTBool(boolean b0) {
        b = b0;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError
    {
        return new VBool(b);
    }

}
