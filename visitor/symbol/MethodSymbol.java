package visitor.symbol;

import syntaxtree.*;
import java.util.ArrayList;
import registerallocator.RegisterAllocator;


public class MethodSymbol extends Symbol
{
	private ArrayList<VariableSymbol> parameters;
	public String className;

	public MethodSymbol(Type r, Identifier t)
	{
		type = r;
		name = t;
		parameters = new ArrayList<VariableSymbol>();
	}

	public void addParameter(VariableSymbol v) {
		parameters.add(v);
	}

	public String toString() {
		String s="";
		if(parameters.size() > 0) {
			s = parameters.get(0).toString();
		}
		for(int i=1;i<parameters.size();i++) {
			s += ", " + parameters.get(i);
		}

		return type.toString() + " " + name.s + "(" + s + ")";
	}



	public ArrayList<VariableSymbol> getParameters() {
		return parameters;
	}


	/* 
		Set the registers for the parameters
	*/
	public void setParamRegisters(RegisterAllocator rAllocator){
		for (VariableSymbol var:parameters){
			var.setRegister(rAllocator.allocateNonMapReg());
		}
	}
}