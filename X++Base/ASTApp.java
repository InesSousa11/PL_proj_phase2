public class ASTApp implements ASTNode {

    ASTNode function;
    ASTNode argument;

    ASTApp(ASTNode function, ASTNode argument) {
        this.function = function;
        this.argument = argument;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue fval = function.eval(env);

        if (!(fval instanceof VFunc)) {
            throw new InterpreterError("Trying to apply a non-function value");
        }

        VFunc vf = (VFunc) fval;
        if (vf.params.size() != 1) {
            throw new InterpreterError("Function expected 1 argument, but has: " + vf.params.size());
        }

        IValue argVal = argument.eval(env);
        Environment<IValue> extended = new Environment<>(vf.e);
        extended.assoc(vf.params.get(0), argVal);

        return vf.body.eval(extended);
    }
}
