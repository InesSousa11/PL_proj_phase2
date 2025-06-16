import java.util.Map;

public class ASTStruct implements ASTNode {

    private final Map<String, ASTNode> fields;

    public ASTStruct(Map<String, ASTNode> fields) {
        this.fields = fields;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        Map<String, IValue> evaluatedFields = new java.util.HashMap<>();
        for (Map.Entry<String, ASTNode> entry : fields.entrySet()) {
            evaluatedFields.put(entry.getKey(), entry.getValue().eval(env));
        }
        return new VStruct(evaluatedFields);
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        Map<String, ASTType> typedFields = new java.util.HashMap<>();
        for (Map.Entry<String, ASTNode> entry : fields.entrySet()) {
            ASTType fieldType = entry.getValue().typecheck(env);
            typedFields.put(entry.getKey(), fieldType);
        }
        return new ASTTStruct(new TypeBindList(typedFields));
    }
}