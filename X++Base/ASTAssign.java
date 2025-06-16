public class ASTAssign implements ASTNode {
    ASTNode t1;
    ASTNode t2;

    public ASTAssign(ASTNode t1, ASTNode t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

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

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        if (t1 instanceof ASTId idNode) {
            String id = idNode.id;
            ASTType t1Type;
            try {
                t1Type = env.find(id);
            } catch (InterpreterError e) {
                throw new TypeError("Variable '" + id + "' not found in type environment.");
            }

            if (!(t1Type instanceof ASTTRef refType)) {
                throw new TypeError("Left-hand side of := must be a reference type.");
            }

            ASTType t2Type = t2.typecheck(env);
            if (!t2Type.isSubtypeOf(refType.getType())) {
                throw new TypeError("Type mismatch in assignment. Expected: " +
                        refType.getType().toStr() + ", but got: " + t2Type.toStr());
            }

            return new ASTTUnit();
        }

        throw new TypeError("Left-hand side of assignment must be a variable.");
    }
}
