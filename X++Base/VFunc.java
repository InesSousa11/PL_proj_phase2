import java.util.List;

class VFunc implements IValue {
    Environment<IValue> e;
    List<String> params;
    ASTNode body;

    VFunc(Environment<IValue> e, List<String> params, ASTNode body) {
        this.e = e;
        this.params = params;
        this.body = body;
    }

    public String toStr() {
        return "<function(" + String.join(", ", params) + ")>";
    }
}