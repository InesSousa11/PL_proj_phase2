public class ASTSeq implements ASTNode {
    ASTNode first;
    ASTNode second;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        first.eval(e);         // Evaluate first expression, discard result
        return second.eval(e); // Evaluate and return result of second
    }

    public ASTSeq(ASTNode first, ASTNode second) {
        this.first = first;
        this.second = second;
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        first.typecheck(env);         // type of first expression is ignored
        return second.typecheck(env); // return the type of the second
    }
}
