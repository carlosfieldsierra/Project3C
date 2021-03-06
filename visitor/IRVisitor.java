package visitor;

import java.util.*;
import ir.*;
import visitor.symbol.*;
import syntaxtree.*;

public class IRVisitor implements SymbolVisitor
{
	
	SymbolTable symbolTable;

	int	temporaryNumber	= 0;

	public ArrayList<Quadruple> IR = new ArrayList<Quadruple>();

	public HashMap<String, ArrayList<Quadruple>> methods = new HashMap<String, ArrayList<Quadruple>>();

	private void reset() {
		temporaryNumber	= 0;
		IR = new ArrayList <Quadruple>();
	}

	private void addMethod(String s) {
		methods.put(s,IR);
	}

	public void dumpIR() {
		ArrayList<Quadruple> temp = methods.get("main");
		
		System.out.println("main:");
		for(int i=0; i<temp.size();i++) {
			System.out.println("\t" + i + ": " + temp.get(i));
		}

		for(Iterator i=methods.keySet().iterator(); i.hasNext(); ) {
			String s = (String)i.next();
			if(s.equals("main")) {
				continue;
			}
			temp = methods.get(s);

			System.out.println(s + ":");

			for(int j=0; j<temp.size();j++) {
				System.out.println("\t" + j + ": " + temp.get(j));
			}
		}
	}

	public IRVisitor(SymbolTable s) {
		symbolTable = s;
		//Add two "system calls" to the symbol table. We'll call assembly routines for them directly.
		MethodSymbol print = new MethodSymbol(new IdentifierType("void"), new Identifier("System.out.println"));
		print.addParameter(new VariableSymbol(new IntegerType(), new Identifier("x")));
		MethodSymbol exit = new MethodSymbol(new IdentifierType("void"), new Identifier("System.exit"));
		symbolTable.addSymbol(print);
		symbolTable.addSymbol(exit);
	}

	// MainClass m;
	// ClassDeclList cl;
	public Symbol visit(Program n) {
		n.m.accept (this);
		for	(int i = 0;	i <	n.cl.size (); i++) {
			n.cl.elementAt(i).accept(this);
		}
		return null;
	}

	// Identifier i1,i2;
	// Statement s;
	public Symbol visit(MainClass n) {
		symbolTable.enterScope(n.i1);
    
		Identifier main = new Identifier("main");
		symbolTable.enterScope(main);

		n.i1.accept(this);
		n.i2.accept(this);

		n.s.accept(this);

		IR.add(new CallQuadruple(symbolTable.getSymbol(new Identifier("System.exit")), null, true));

		addMethod("main");
		reset();

		symbolTable.leaveScope();
		symbolTable.leaveScope();

		return null;
	} 

	// Identifier i;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Symbol visit (ClassDeclSimple n)
	{
		symbolTable.enterScope(n.i);

		n.i.accept (this);
		for	(int i = 0;	i <	n.vl.size (); i++) {
			n.vl.elementAt (i).accept (this);
		} 
		for	(int i = 0;	i <	n.ml.size (); i++) {
			n.ml.elementAt (i).accept (this);
		} 
		
		symbolTable.leaveScope();
		
		return null;
	} 

	// Identifier i;
	// Identifier j;
	// VarDeclList vl;
	// MethodDeclList ml;
	public Symbol visit(ClassDeclExtends n) {
		symbolTable.enterScope(n.i);

		n.i.accept(this);
		n.j.accept(this);
		for	(int i = 0;	i <	n.vl.size (); i++) {
			n.vl.elementAt(i).accept(this);
		} 
		for	(int i = 0;	i <	n.ml.size (); i++) {
			n.ml.elementAt(i).accept(this);
		} 

		symbolTable.leaveScope();

		return null;
	} 

	// Type	t;
	// Identifier i;
	public Symbol visit (VarDecl n) {
		n.t.accept (this);
		n.i.accept (this);

		return null;
	} 

	// Type	t;
	// Identifier i;
	// FormalList fl;
	// VarDeclList vl;
	// StatementList sl;
	// Exp e;
	public Symbol visit (MethodDecl n)
	{
		String cName = symbolTable.getScopeName().s;

		symbolTable.enterScope(n.i);
		
		n.t.accept (this);
		n.i.accept (this);
		for	(int i = 0;	i <	n.fl.size (); i++)
		{
			n.fl.elementAt (i).accept (this);
		} 
		for	(int i = 0;	i <	n.vl.size (); i++)
		{
			n.vl.elementAt (i).accept (this);
		} 
		for	(int i = 0;	i <	n.sl.size (); i++)
		{
			n.sl.elementAt (i).accept (this);
		} 
		
		Symbol ret = n.e.accept (this);
		IR.add(new ReturnQuadruple(ret));

		addMethod(cName + "_" + n.i);
		reset();

		symbolTable.leaveScope();
		
		return null;
	} 

	// Type	t;
	// Identifier i;
	public Symbol visit (Formal n)
	{
		n.t.accept (this);
		n.i.accept (this);

		return null;
	} 

	public Symbol visit (IntArrayType n) {

		return null;
	} 

	public Symbol visit (BooleanType n) {
		return null;
	} 

	public Symbol visit (IntegerType n) {
		return null;
	} 

	// String s;
	public Symbol visit (IdentifierType n) {
		return null;
	} 

	// StatementList sl;
	public Symbol visit (Block n) {
		for	(int i = 0;	i <	n.sl.size (); i++) {
			n.sl.elementAt (i).accept (this);
		} 
		return null;
	} 


	/****************************************************************
		Statements do not yield a value, and so return null 
	*****************************************************************/

	// Exp e;
	// Statement s1,s2;
	public Symbol visit (If n) {
		/*
		if(cond) {							cond
											iffalse cond goto ELSE
		
		}									goto END
		else {					ELSE:
											
		}						END:
		*/

		Symbol condition = n.e.accept (this);
		
		Label l_else = new Label();
		IR.add(new IfQuadruple(condition, l_else));

		n.s1.accept(this);
		
		Label l_end = new Label();
		IR.add(new GotoQuadruple(l_end));

		int label = IR.size();
		l_else.backpatch(label);

		n.s2.accept(this);
		
		label = IR.size();
		l_end.backpatch(label);
		
		return null;
	} 

	// Exp e;
	// Statement s;
	public Symbol visit (While n) {

		/*
		while(cond) {			BACKEDGE:	cond
											iffalse cond goto EXIT
						
											goto BACKEDGE
		}						EXIT:
		*/
		
		int backedge = IR.size();
		Label l_cond = new Label(backedge);
		
		Symbol condition = n.e.accept (this);
		
		Label l_exit = new Label();
		IR.add(new IfQuadruple(condition, l_exit));

		n.s.accept(this);

		IR.add(new GotoQuadruple(l_cond));

		int label = IR.size();
		l_exit.backpatch(label);
		
		return null;
	} 

	// Exp e;
	public Symbol visit (Print n) {
		Symbol s = n.e.accept (this);
		
		IR.add(new ParameterQuadruple(s));
		IR.add(new CallQuadruple(symbolTable.getSymbol(new Identifier("System.out.println")),null,true));
		
		return null;
	} 

	// Identifier i;
	// Exp e;
	public Symbol visit (Assign n) {
		Symbol lhs = n.i.accept(this);
		Symbol rhs = n.e.accept(this);
		IR.add(new CopyQuadruple(rhs, lhs));
		return null;
	} 

	// Identifier i;
	// Exp e1,e2;
	public Symbol visit (ArrayAssign n) {
		Symbol array = n.i.accept(this);
		Symbol subscript = n.e1.accept(this);
		Symbol rhs = n.e2.accept(this);

		IR.add(new ArrayAssignmentQuadruple(subscript, rhs, array));

		return null; 
	}

	/*********************************************************************
		Expressions return the symbol they assign to, which has been added 
		to the symbol table 
	***********************************************************************/

	// Exp e1,e2;
	public Symbol visit (And n) {
		Symbol lhs = n.e1.accept(this);
		Symbol rhs = n.e2.accept(this);

		Symbol temp = new VariableSymbol(symbolTable.getType(lhs.getName()), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new AssignmentQuadruple(AssignmentQuadruple.AND, lhs, rhs, temp));
		return temp;
	} 

	// Exp e1,e2;
	public Symbol visit (LessThan n) {
		Symbol lhs = n.e1.accept(this);
		Symbol rhs = n.e2.accept(this);

		Symbol temp = new VariableSymbol(symbolTable.getType(lhs.getName()), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new AssignmentQuadruple(AssignmentQuadruple.LT, lhs, rhs, temp));
		return temp;
	} 

	// Exp e1,e2;
	public Symbol visit(Plus n) {
		Symbol lhs = n.e1.accept(this);
		Symbol rhs = n.e2.accept(this);

		Symbol temp = new VariableSymbol(symbolTable.getType(lhs.getName()), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new AssignmentQuadruple(AssignmentQuadruple.ADD, lhs, rhs, temp));
		return temp;
	}
	
	// Exp e1,e2;
	public Symbol visit (Minus n) {
		Symbol lhs = n.e1.accept(this);
		Symbol rhs = n.e2.accept(this);

		Symbol temp = new VariableSymbol(symbolTable.getType(lhs.getName()), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new AssignmentQuadruple(AssignmentQuadruple.SUB, lhs, rhs, temp));
		return temp;
	}
	
	// Exp e1,e2;
	public Symbol visit (Times n) {
		Symbol lhs = n.e1.accept(this);
		Symbol rhs = n.e2.accept(this);

		Symbol temp = new VariableSymbol(symbolTable.getType(lhs.getName()), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new AssignmentQuadruple(AssignmentQuadruple.MUL, lhs, rhs, temp));
		return temp;
	} 

	// Exp e1,e2;
	public Symbol visit (ArrayLookup n) {
		Symbol array = n.e1.accept(this);
		Symbol subscript = n.e2.accept(this);

		Symbol temp = new VariableSymbol(new IntegerType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);

		IR.add(new ArrayLookupQuadruple(array, subscript, temp));

		return temp;
	} 

	// Exp e;
	public Symbol visit (ArrayLength n) {
		Symbol array = n.e.accept (this);
		
		Symbol temp = new VariableSymbol(new IntegerType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);

		IR.add(new ArrayLengthQuadruple(array, temp));

		return temp;
	} 

	// Exp e;
	// Identifier i;
	// ExpList el;
	public Symbol visit (Call n) {
		ArrayList<Quadruple> params = new ArrayList<Quadruple>();
		Symbol thisParam = n.e.accept(this);

		//Implicit this parameter is always the first one
		//We can't add it right to IR yet as we will omit other code
		// as we visit other expressions.
		params.add(new ParameterQuadruple(thisParam));

		n.i.accept(this);

		Symbol func = symbolTable.getMethodByName(new Identifier(((IdentifierType)thisParam.getType()).s), n.i);		
		
		for	(int i = 0;	i <	n.el.size(); i++) {
			Symbol param = n.el.elementAt(i).accept(this);
			params.add(new ParameterQuadruple(param));
		}

		IR.addAll(params);

		Symbol temp = new VariableSymbol(func.getType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);

		IR.add(new CallQuadruple(func, temp));

		return temp;
	} 

	// int i;
	public Symbol visit (IntegerLiteral n) {
		Symbol temp = new VariableSymbol(new IntegerType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new CopyQuadruple(new Constant(new IntegerType(), n.i), temp));
		return temp;
	} 

	/**
		I choose to use -1 for true since booleans are opaque in java
	*/
	public Symbol visit (True n) {
		Symbol temp = new VariableSymbol(new BooleanType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new CopyQuadruple(new Constant(new BooleanType(), -1), temp));
		return temp;
	} 

	public Symbol visit (False n) {
		Symbol temp = new VariableSymbol(new BooleanType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new CopyQuadruple(new Constant(new BooleanType(), 0), temp));
		return temp;
	} 

	// String s;
	public Symbol visit (IdentifierExp n) {
		return symbolTable.getSymbol(new Identifier(n.s));
	}

	public Symbol visit (This n) {
		return symbolTable.getSymbol(new Identifier("this"));
	} 

	// Exp e;
	public Symbol visit (NewArray n) {
		Symbol size = n.e.accept (this);
		
		Symbol temp = new VariableSymbol(new IntArrayType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new NewArrayQuadruple(size, temp));

		return temp;
	}

	// Identifier i;
	public Symbol visit (NewObject n) {
		Symbol c = n.i.accept (this);
		
		Symbol temp = new VariableSymbol(c.getType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new NewObjectQuadruple(c, temp));
		
		return temp;
	} 

	// Exp e;
	public Symbol visit (Not n) {
		Symbol rhs = n.e.accept(this);

		Symbol temp = new VariableSymbol(new BooleanType(), new Identifier("_t" + temporaryNumber++));
		symbolTable.addSymbol(temp);
		
		IR.add(new UnaryAssignmentQuadruple(UnaryAssignmentQuadruple.NOT, rhs, temp));
		return temp;
	} 

	// String s;
	public Symbol visit(Identifier n) {
		return symbolTable.getSymbol(n);
	} 
}

