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
}
