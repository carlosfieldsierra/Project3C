import java_cup.runtime.Symbol;
import java.io.*;
import java.util.*;

import ir.Quadruple;
import visitor.*;
import visitor.symbol.*;
import visitor.symbol.SymbolTable.Binding;
import syntaxtree.Identifier;
import syntaxtree.Program;
import codegen.MipsGenerator;

public class MiniJavaC {
	public static void main(String[] args) {
		/* 
			If there is no args  exit and show error
		*/
		if(args.length != 1) {
			System.err.println("ERROR: Invalid number of command line arguments.");
			System.err.println("Usage: java MiniJavaC file.mini");
			System.exit(1);
		}
		/* 
			Will hold the ast
		*/
		Symbol parse_tree = null;
		/* 
			Bind together
		*/
		SymbolTable table = new SymbolTable();
		NameVisitor nameVisitor = new NameVisitor(table);
		TypeDepthFirstVisitor typeVisitor = new TypeDepthFirstVisitor(table);
		IRVisitor irVisitor = new IRVisitor(table);

		try {
			MiniJavaParser parser_obj = new MiniJavaParser(new MiniJavaLexer(new FileReader(args[0])));
			parse_tree = parser_obj.parse();
			Program p = (Program)parse_tree.value;
			System.out.println("****** Name Checking ************");
			nameVisitor.visit(p);
			System.out.println("****** Type Checking ************");
			typeVisitor.visit(p);
			
			if(nameVisitor.hadError() || typeVisitor.hadError()) {
				System.out.println("Errors detected. Compilation aborted.");
				System.exit(1);
			}
			System.out.println("****** IR Generation ************");
			irVisitor.visit(p);

			/* 
				Handle pre reg allocation
			*/
			handlePreRegAlloc(table);


			/* 
				Mips Generator
			*/
			String filePath = args[0];
			MipsGenerator mipsGenerator = new MipsGenerator(filePath,irVisitor.methods);
			mipsGenerator.generateMips();



			// irVisitor.dumpIR();
			// System.out.println(irVisitor.methods.get("Tree_Insert"));
			
			
		} catch (IOException e) {
			System.err.println("ERROR: Unable to open file: " + args[0]);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}

	public static void handlePreRegAlloc(SymbolTable table){
		HashMap<Identifier, Binding> classes = table.getClasses();
		for (Identifier classId: classes.keySet()){
			System.out.println(classId);
		}
	}
}
