public interface ASTNode {

    IValue eval(Environment<IValue> e) throws InterpreterError;
    ASTType typecheck(Environment<ASTType> env) throws TypeError;
	
}