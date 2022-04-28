package visitor.symbol;

import syntaxtree.*;
import java.util.ArrayList;

public class ClassSymbol extends Symbol
{
	private ArrayList<MethodSymbol> methods;
	private ArrayList<VariableSymbol> variables;

	private Type extendsType;
	private ClassSymbol baseClass;

	private int size;
	private int offset;

	public ClassSymbol(Identifier n)
	{
		type = new IdentifierType(n.s);
		name = n;
		methods = new ArrayList<MethodSymbol>();
		variables = new ArrayList<VariableSymbol>();
		size = -1;
		offset = -1;
	}
	
	
	
	public ClassSymbol(Identifier n, Identifier e)
	{
		type = new IdentifierType(n.s);
		name = n;
		extendsType = new IdentifierType(e.s);
		methods = new ArrayList<MethodSymbol>();
		variables = new ArrayList<VariableSymbol>();
	}

	public void addMethod(MethodSymbol m) {
		methods.add(m);
	}

	public void addVariable(VariableSymbol v) {
		variables.add(v);
	}

	public ArrayList<MethodSymbol> getMethods() {
		return methods;
	}

	public ArrayList<VariableSymbol> getVariables() {
		return variables;
	}

	public Type getBaseClass() {
		return extendsType;
	}

	public void extendsClass(ClassSymbol c) {
		baseClass = c;
		methods.addAll(c.methods);
		variables.addAll(c.variables);
	}

	public  String getClassName(){
		return name.s;
	}

	public String toString() {
		String c = "";
		if(extendsType != null)
			c = "class " + name.s + " extends " + extendsType.toString();
		else
			c = "class " + name.s;

		for(int i=0;i<variables.size();i++)
		{
			c += "\n\t"+variables.get(i).toString() ;
		}

		for(int i=0;i<methods.size();i++)
		{
			c += "\n\t"+methods.get(i).toString();
		}
		return c;
	}

	/* 	
		Used to caluclate the size
	*/
	public int getSize(){
		if (this.size==-1){
			this.size = this.reCalcSize();
		}
		return this.size;
	}

	private int reCalcSize(){
		int curSize = 0;
		if (baseClass!=null){
			curSize+= baseClass.getSize();
		}
		curSize += variables.size()*4;
		return curSize;
	}

	
	/* 
		Used to calcule the offset
	*/
	public int getOffset(){
		if (this.offset==-1){
			this.offset = this.reCalcOffset();
		}
		return this.offset;
	}

	public int reCalcOffset(){
		int curOffset = 0;
		if (baseClass!=null) curOffset=baseClass.getOffset();
		return 0;
	}


	/* 	
		Use to make the var offsets
	*/
	public void setVarsOffsets(){
		int oldOffset = this.getOffset();
		int newOffset = 0;

		for (VariableSymbol var: variables){
			var.setOffset(oldOffset+newOffset);
			newOffset+=4;
		}
	}

}