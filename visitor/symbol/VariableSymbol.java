package visitor.symbol;

import syntaxtree.*;

public class VariableSymbol extends Symbol
{
	private String reg;
	private int offset;
	
	public VariableSymbol(Type t, Identifier n)
	{
		type = t;
		name = n;
	}
	


	/* 
		Set the register name
	*/
	public void setRegister(String reg){
		this.reg = reg;
	}

	public String getRegister(){
		return this.reg;
	}

	/* 
		Set offset
	*/
	public void setOffset(int offset){
		this.offset = offset;
	}

	public int getOffset(){
		return this.offset;
	}
	

	public String toString() {
		return type.toString() + " " + name;
	}

}