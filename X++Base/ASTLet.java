import java.util.List;

public class ASTLet implements ASTNode {
    List<Bind> decls;
    ASTNode body;

    public ASTLet(List<Bind> decls, ASTNode b) {
        this.decls = decls;
        body = b;
    }

    public IValue eval(Environment<IValue> e) throws InterpreterError {
	    Environment<IValue> en = e.beginScope();
        for (Bind b : decls) {
            IValue val = b.getExp().eval(en); // evaluate the expression part of the binding
            en.assoc(b.getId(), val);         // associate the variable name with the value
        }

        return body.eval(en);                 // evaluate the body in this new scope
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        Environment<ASTType> tenv = env.beginScope();

        for (Bind b : decls) {
            ASTType type = b.getExp().typecheck(tenv);
            tenv.assoc(b.getId(), type);
        }

        return body.typecheck(tenv);
    }
}
