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

        // First pass: register all declared types (for recursive references)
        for (Bind b : decls) {
            ASTType declared = b.getDeclaredType();
            if (declared != null) {
                tenv.assoc(b.getId(), declared);  // allow forward reference
            }
        }

        // Second pass: typecheck all expressions
        for (Bind b : decls) {
            ASTType inferred = b.getExp().typecheck(tenv);
            ASTType declared = b.getDeclaredType();

            if (declared != null) {
                if (!inferred.isSubtypeOf(declared)) {
                    throw new TypeError("Declared type for '" + b.getId() + "' does not match inferred type.\n" +
                            "Declared: " + declared.toStr() + ", Inferred: " + inferred.toStr());
                }
            } else {
                tenv.assoc(b.getId(), inferred);
            }
        }

        return body.typecheck(tenv);
    }

}
