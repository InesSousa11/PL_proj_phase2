class ASTNil implements ASTNode {

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VList();  // empty list
    }

}