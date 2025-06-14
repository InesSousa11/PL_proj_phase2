class ASTList implements ASTNode	{

    ASTNode head, tail;
    boolean in_match;

    public ASTList(ASTNode head, ASTNode tail)	{
        this.head = head;
        this.tail = tail;
        this.in_match = false;
    }

    public IValue eval(Environment<IValue> env)	throws
            InterpreterError {
        if (this.in_match)
            return new VList(this.head.eval(env), this.tail.eval(env));
        else
            return new VList(this.head, this.tail);
    }

    public void inside_match() {
        this.in_match = true;
        if (this.tail instanceof ASTList) {
            ((ASTList) this.tail).inside_match();
        }
    }

}
