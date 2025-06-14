class VList implements IValue {
    ASTNode head, tail;
    IValue vhead, vtail;
    boolean empty;
    boolean in_match;

    public VList()	{
        this.empty = true;
    }

    public VList(ASTNode head, ASTNode tail)	{
        this.head = head;
        this.tail = tail;
        this.empty = false;
        this.in_match = false;
    }

    public VList(IValue head, IValue tail)	{
        this.vhead = head;
        this.vtail = tail;
        this.empty = false;
        this.in_match = true;
    }

    public String toStr() {
        if (this.empty) {
            return "nil";
        }
        if (this.in_match)
            return this.vhead.toStr() + "::" + this.vtail.toStr();
        else
            return "Can't print lazy list outside match operator.";
    }
}