public class ASTFieldAccess implements ASTNode	{

    ASTNode target;
    String field;

    public ASTFieldAccess(ASTNode target, String field) {
        this.target = target;
        this.field = field;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue structVal = target.eval(env);

        if (!(structVal instanceof VStruct)) {
            throw new InterpreterError("Expected struct in field access, but got: " + structVal.getClass().getSimpleName());
        }

        VStruct struct = (VStruct) structVal;
        IValue fieldVal = struct.get(field);

        if (fieldVal == null) {
            throw new InterpreterError("Field '" + field + "' not found in struct.");
        }

        return fieldVal;
    }

    public String toString() {
        return target.toString() + "." + field;
    }

}
