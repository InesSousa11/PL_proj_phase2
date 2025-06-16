class ASTNil implements ASTNode {

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return new VList();  // empty list
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        // Placeholder for unknown element type
        return new ASTTList(new ASTTId("_"));
    }
}