import java.util.Map;
import java.util.AbstractMap.SimpleEntry;

public class ASTCase implements ASTNode {

    ASTNode target;
    Map<String, SimpleEntry<String, ASTNode>> branches; // label -> (bound id, branch expression)

    public ASTCase(ASTNode target, Map<String, SimpleEntry<String, ASTNode>> branches) {
        this.target = target;
        this.branches = branches;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = target.eval(env);
        if (!(v instanceof VUnion u)) {
            throw new InterpreterError("Expected union value in case expression.");
        }

        String label = u.getLabel();
        SimpleEntry<String, ASTNode> branch = branches.get(label);

        if (branch == null) {
            throw new InterpreterError("No branch for label: " + label);
        }

        Environment<IValue> e2 = env.beginScope();
        e2.assoc(branch.getKey(), u.getValue());  // getKey() = bound variable
        return branch.getValue().eval(e2);        // getValue() = branch body
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType targetType = target.typecheck(env);

        if (!(targetType instanceof ASTTUnion uType)) {
            throw new TypeError("Expected union type in case expression, got: " + targetType.toStr());
        }

        Map<String, ASTType> unionAlts = uType.getAlternatives();
        ASTType resultType = null;

        for (Map.Entry<String, SimpleEntry<String, ASTNode>> entry : branches.entrySet()) {
            String label = entry.getKey();
            SimpleEntry<String, ASTNode> branch = entry.getValue();

            ASTType altType = unionAlts.get(label);
            if (altType == null) {
                throw new TypeError("Label '" + label + "' not found in union type.");
            }

            Environment<ASTType> branchEnv = env.beginScope();
            branchEnv.assoc(branch.getKey(), altType);

            ASTType branchType = branch.getValue().typecheck(branchEnv);

            if (resultType == null) {
                resultType = branchType;
            } else if (!branchType.isSubtypeOf(resultType)) {
                throw new TypeError("Branches of case must return compatible types. "
                        + "Got " + branchType.toStr() + " vs " + resultType.toStr());
            }
        }

        if (resultType == null) {
            throw new TypeError("No valid branches in case expression.");
        }

        return resultType;
    }
}
