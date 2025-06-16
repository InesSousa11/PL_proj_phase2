public	class ASTTId implements ASTType	{	

    String id;	
    
    public ASTTId(String id)	{
        this.id = id;
    }

    public String toStr() {
        return id;
    }

    public boolean isSubtypeOf(ASTType other) {
        if (other instanceof ASTTId o) {
            return this.id.equals(o.id);
        }
        return false;
    }
}	
