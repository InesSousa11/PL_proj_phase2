import java.util.List;

public class ASTApp implements ASTNode {

    ASTNode function;
    ASTNode argument;

    ASTApp(ASTNode function, ASTNode argument) {
        this.function = function;
        this.argument = argument;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue fval = function.eval(env);

        if (!(fval instanceof VFunc vf)) {
            throw new InterpreterError("Trying to apply a non-function value");
        }

        if (vf.params.isEmpty()) {
            throw new InterpreterError("Function has no parameters left to apply.");
        }

        String param = vf.params.get(0);
        IValue argVal = argument.eval(env);

        // Create a new environment with this param bound
        Environment<IValue> extended = new Environment<>(vf.e);
        extended.assoc(param, argVal);

        // If more parameters remain, return a new VFunc
        if (vf.params.size() > 1) {
            List<String> remainingParams = vf.params.subList(1, vf.params.size());
            return new VFunc(extended, remainingParams, vf.body);
        }

        // Final parameter: evaluate body
        return vf.body.eval(extended);
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType funcType = function.typecheck(env);

        if (!(funcType instanceof ASTTArrow arrowType)) {
            throw new TypeError("Trying to apply a non-function type: " + funcType.toStr());
        }

        ASTType argType = argument.typecheck(env);
        ASTType expectedArgType = arrowType.dom;
        ASTType returnType = arrowType.codom;

        if (!argType.toStr().equals(expectedArgType.toStr())) {
            throw new TypeError("Function expected argument of type " + expectedArgType.toStr() +
                    ", but got: " + argType.toStr());
        }

        return returnType;
    }
}
