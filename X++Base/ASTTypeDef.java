import java.util.*;

public class ASTTypeDef implements ASTNode {
HashMap<String,ASTType> ltd;
ASTNode body;

    public ASTTypeDef(HashMap<String,ASTType>  ltdp, ASTNode b) {
	ltd = ltdp;
    body = b;
    }
    
    public IValue eval(Environment<IValue> env) throws InterpreterError {
        return body.eval(env);
    }

    public ASTType typecheck(Environment<ASTType> tenv) throws TypeError{
        Environment<ASTType> newEnv = tenv.beginScope();

        for (Map.Entry<String, ASTType> entry : ltd.entrySet()) {
            newEnv.assoc(entry.getKey(), entry.getValue());
        }

        return body.typecheck(newEnv);
    }
}
