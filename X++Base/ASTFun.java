import java.util.ArrayList;
import java.util.List;

public class ASTFun implements ASTNode	{
    String param;
    ASTNode body;

    public ASTFun(String param, ASTNode body) {
        this.param = param;
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
}
