public class ASTSub implements ASTNode {

    ASTNode lhs, rhs;

	public ASTSub(ASTNode l, ASTNode r) {
		lhs = l;
		rhs = r;
	}

    public IValue eval(Environment<IValue> e) throws InterpreterError {
		IValue v1 = lhs.eval(e);
		IValue v2 = rhs.eval(e);
		if (v1 instanceof VInt && v2 instanceof VInt) {
			return new VInt(((VInt) v1).getval() - ((VInt) v2).getval());
		} else {
			throw new InterpreterError("Illegal types to + operator.");
		}
    }

	public ASTType typecheck(Environment<ASTType> env) throws TypeError {
		ASTType t1 = lhs.typecheck(env);
		ASTType t2 = rhs.typecheck(env);

		if (!(t1 instanceof ASTTInt && t2 instanceof ASTTInt)) {
			throw new TypeError("Both sides of '-' must be of type int.");
		}

		return new ASTTInt();
	}
}
