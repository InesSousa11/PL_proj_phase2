class ASTList implements ASTNode	{

    ASTNode head, tail;
    boolean in_match;
    boolean lazy;  // NEW: per-node flag

    public ASTList(ASTNode head, ASTNode tail, boolean lazy)	{
        this.head = head;
        this.tail = tail;
        this.in_match = false;
        this.lazy = lazy;
    }

    public IValue eval(Environment<IValue> env)	throws
            InterpreterError {
        if (this.in_match || this.lazy)
            return new VList(this.head.eval(env), this.tail.eval(env)); // evaluated head & tail
        else
            return new VList(this.head, this.tail); // lazy by default
    }

    public void inside_match() {
        this.in_match = true;
        if (this.tail instanceof ASTList) {
            ((ASTList) this.tail).inside_match();
        }
    }

    public ASTType typecheck(Environment<ASTType> env) throws TypeError {
        ASTType headType = head.typecheck(env);
        ASTType tailType = tail.typecheck(env);

        // Tail must be a list of the same type as head
        if (tailType instanceof ASTTList) {
            ASTTList tailListType = (ASTTList) tailType;
            if (!headType.toStr().equals(tailListType.toStr().substring(5, tailListType.toStr().length() - 1))) {
                throw new TypeError("Type mismatch in list: head type " + headType.toStr() + " does not match tail type " + tailType.toStr());
            }
            return tailType;
        }

        if (tail instanceof ASTNil) {
            return new ASTTList(headType);
        }

        throw new TypeError("Tail of a list must be another list or nil, but got: " + tailType.toStr());
    }
}
