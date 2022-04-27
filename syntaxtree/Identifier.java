package syntaxtree;
import visitor.Visitor;
import visitor.TypeVisitor;
import visitor.SymbolVisitor;
import visitor.symbol.Symbol;

public class Identifier extends ASTNode {
  public String s;

  public Identifier(String as) { 
    s=as;
  }

  public void accept(Visitor v) {
    v.visit(this);
  }

  public Type accept(TypeVisitor v) {
    return v.visit(this);
  }

  public Symbol accept(SymbolVisitor v) {
    return v.visit(this);
  }

  public String toString(){
    return s;
  }

  public boolean equals(Object o) {
	  if(o instanceof Identifier) {
		  return ((Identifier)o).s.equals(this.s);
	  }
	  return false;
  }

  public int hashCode() {
	  return s.hashCode();
  }
}
