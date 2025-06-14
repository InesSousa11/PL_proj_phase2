public class ASTAssign implements ASTNode {
    ASTNode t1;
    ASTNode t2;

    public IValue eval(Environment<IValue> e) throws InterpreterError {
        if (t1 instanceof ASTId) {
            String s = ((ASTId) t1).id;
            if (e.find(s) instanceof VBox) {
                IValue v = t2.eval(e);
                if (v instanceof VBox) {
                    e.assoc(s, v);
                }
                else {
                    e.assoc(s, new VBox(v));
                }
                return v;
            }
        }
        throw new InterpreterError("illegal types to := operator");
    }

    public ASTAssign(ASTNode t1, ASTNode t2) {
        this.t1 = t1;
        this.t2 = t2;
    }
}
