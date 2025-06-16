import java.util.ArrayList;
import java.util.List;

public class ASTFun implements ASTNode	{
    String param;
    ASTNode body;
    ASTType paramType;

    public ASTFun(String param, ASTType paramType, ASTNode body) {
        this.param = param;
        this.paramType = paramType;
        this.body = body;
    }

    public void setBody(ASTNode body) {
        this.body = body;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        List<String> params = new ArrayList<>();
        ASTNode bodyNode = this;

        while (bodyNode instanceof ASTFun funNode) {
            params.add(funNode.param);
            bodyNode = funNode.body;
        }

        return new VFunc(env, params, bodyNode);
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        Environment<ASTType> extendedEnv = env.beginScope();
        extendedEnv.assoc(param, paramType); // no lookup, just use known type

        ASTType resultType = body.typecheck(extendedEnv);
        return new ASTTArrow(paramType, resultType);
    }
}
