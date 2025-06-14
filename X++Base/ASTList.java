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

}
