public class ASTMatch implements ASTNode {

    ASTNode list, body, nil_body;
    String head_parameter, tail_parameter;

    public ASTMatch(ASTNode list, ASTNode nil_body, String head_parameter, String tail_parameter, ASTNode body) {
        this.list = list;
        this.body = body;
        this.nil_body = nil_body;
        this.head_parameter = head_parameter;
        this.tail_parameter = tail_parameter;
    }

    public IValue eval(Environment<IValue> env)	throws
            InterpreterError {
        IValue ivlist = list.eval(env);
        if (!(ivlist instanceof VList)) {
            throw new InterpreterError("Match input must be a list.");
        }
        VList vlist = (VList) ivlist;
        if (vlist.empty) {
            return this.nil_body.eval(env);
        }
        else {
            Environment<IValue> e = env.beginScope();
            if (vlist.in_match) { // It's a recursive match so everything's already reduced (evaluated)
                e.assoc(this.head_parameter, vlist.vhead);
                e.assoc(this.tail_parameter, vlist.vtail);
                return this.body.eval(e);
            }
            else {
                e.assoc(this.head_parameter, vlist.head.eval(env));
                if (vlist.tail instanceof ASTList) {
                    ((ASTList) vlist.tail).inside_match();
                }
                e.assoc(this.tail_parameter, vlist.tail.eval(env));
                return this.body.eval(e);
            }
        }
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType listType = list.typecheck(env);

        if (!(listType instanceof ASTTList listT)) {
            throw new TypeError("Match expression must be over a list, but got: " + listType.toStr());
        }

        ASTType elementType = listT.getElementType();

        // Type check nil_body in the same environment
        ASTType nilType = nil_body.typecheck(env);

        // Extend environment for the cons case
        Environment<ASTType> newEnv = env.beginScope();
        newEnv.assoc(head_parameter, elementType);
        newEnv.assoc(tail_parameter, new ASTTList(elementType));

        ASTType consType = body.typecheck(newEnv);

        // Both branches must return the same type
        if (!nilType.toStr().equals(consType.toStr())) {
            throw new TypeError("Mismatched types in match branches: "
                    + nilType.toStr() + " vs " + consType.toStr());
        }

        return nilType;
    }
}
