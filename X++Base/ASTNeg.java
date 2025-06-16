public class ASTNeg implements ASTNode {

    ASTNode exp;

	public ASTNeg(ASTNode e)
	{
		exp = e;
	}

    public IValue eval(Environment <IValue>e) throws InterpreterError { 
		IValue v0 = exp.eval(e);
		if (v0 instanceof VInt) {
			return new VInt(-((VInt)v0).getval());
		} else {
			throw new InterpreterError("Illegal types to neg operator.");
		}
    }

	public ASTType typecheck(Environment<ASTType> env) throws TypeError {
		ASTType t = exp.typecheck(env);

		if (!(t instanceof ASTTInt)) {
			throw new TypeError("Operand of neg must be of type int.");
		}

		return new ASTTInt();
	}
}

