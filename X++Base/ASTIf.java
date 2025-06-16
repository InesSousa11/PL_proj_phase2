class ASTIf implements ASTNode {
    ASTNode cond, ifBranch, elseBranch;

    ASTIf(ASTNode cond, ASTNode ifBranch, ASTNode elseBranch) {
        this.cond = cond;
        this.ifBranch = ifBranch;
        this.elseBranch = elseBranch;
    }

    public IValue eval(Environment<IValue> env) throws InterpreterError {
        IValue v = cond.eval(env);
        if (!(v instanceof VBool)) {
            throw new InterpreterError("Condition of if must be a boolean");
        }
        if (((VBool) v).getval()) {
            return ifBranch.eval(env);
        }
        else {
            return elseBranch.eval(env);
        }
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType condType = cond.typecheck(env);
        if (!(condType instanceof ASTTBool)) {
            throw new TypeError("Condition of 'if' must be of type bool.");
        }

        ASTType thenType = ifBranch.typecheck(env);
        ASTType elseType = elseBranch.typecheck(env);

        if (!thenType.toStr().equals(elseType.toStr())) {
            throw new TypeError("Types of 'if' and 'else' branches must match. Found: "
                    + thenType.toStr() + " and " + elseType.toStr());
        }

        return thenType;
    }
}
