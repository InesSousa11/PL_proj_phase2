public	class ASTId implements ASTNode	{	

    String id;	
    
    public ASTId(String id)	{
        this.id = id;
    }

    public IValue eval(Environment<IValue> env)	throws
    InterpreterError {
    return env.find(id);	
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        try {
            return env.find(id);
        } catch (InterpreterError e) {
            throw new TypeError("Undefined variable: " + id);
        }
    }
}	
