package codegen;
import ir.*;
import visitor.symbol.MethodSymbol;
import visitor.symbol.*;
import registerallocator.RegisterAllocator;
import syntaxtree.IdentifierType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class MipsGenerator {
    /* 
        The IR instructions for each method
    */
    HashMap<String,ArrayList<Quadruple>> methodsIR;
    /* 
        The file to write to
    */
    FileWriter fw;
    /* 
        Register Allocator
    */
    RegisterAllocator regAlloc;
    /* 
        Current Class
    */
    String curClass;
    /* 
        param num
    */
    private int paramNum = 0;
    private final int MAX_PARAM_NUM = 4;

    /* 
        Reg Types
    */
    private final int TEMP = 0;
    private final int CONST = 1;
    private final int VAR = 2;
    


    

    
    
    public MipsGenerator(String filePath,RegisterAllocator regAlloc,HashMap<String,ArrayList<Quadruple>> methodsIR){
        /* 
            Set regAlloc
        */
        this.regAlloc = regAlloc;
        /* 
            Make mips file to write too
        */
        String newFilePath = this.getMipsFilePath(filePath);
        try {
            this.fw = new FileWriter(newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*  
            Set the IR as a field in the class
        */
        this.methodsIR = methodsIR;
        /* 
            Create the RegisterAllocator
        */
        this.regAlloc = new RegisterAllocator();
       
    }

    /* 
        Creates the file path for the new mips file
    */
    private String getMipsFilePath(String filePath){
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        String[]fileNameLst = fileName.split("\\.");
        return "mips/"+fileNameLst[0]+".asm";
    }

    /* 
        Over all generator
    */
    public void generateMips(){
        /* 
            Put main first
        */
        // Methods Ir
        ArrayList<Quadruple> irList = methodsIR.get("main");
        /* 
            Write method label
        */
        try {
           
            fw.write("main"+":\n");
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("main");
        /* 
            Generate mips on method per method basis
        */
        for (Quadruple quadruple: irList){
            System.out.println("\t\t"+quadruple.getClass());
            System.out.println("\t\t"+quadruple+"\n");
            methodFactory(quadruple);
            // System.out.println("\n");
        }


        /* 
            Loop through methods Ir
        */
        for (String methodNameKey: methodsIR.keySet()){
            if (methodNameKey.equals("main")){
                continue;
            }

            // Methods Ir
            irList = methodsIR.get(methodNameKey);
        
            /* 
                Write method label
            */
            try {
                if (methodNameKey.equals("main")){
                    fw.write(methodNameKey+":\n");
                } else {
                    fw.write(methodNameKey.split("_")[1]+":\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(methodNameKey.split("_").length>1?methodNameKey.split("_")[1]:methodNameKey);
            /* 
                Generate mips on method per method basis
            */
            for (Quadruple quadruple: irList){
                System.out.println("\t\t"+quadruple.getClass());
                System.out.println("\t\t"+quadruple+"\n");
                methodFactory(quadruple);
                // System.out.println("\n");
            }
        }   
    
        /* 
            Link runtime.asm & Close the file writter
        */
        try {
            this.linkRunTime();
            this.fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }   

    /* 
        Links the runtime mips with the file
    */
    public void linkRunTime() throws IOException{
        try {
            this.fw.write("\n");
            File myObj = new File("codegen/runtime.asm");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              this.fw.write(data+"\n");
              
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }


    /* 
        Finds the instance and cast it to that 
        instance and runs the right method
    */  
    public void methodFactory(Quadruple quadruple){
        try {
            if (quadruple instanceof CopyQuadruple){
                CopyQuadruple q = (CopyQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof ArrayAssignmentQuadruple){
                ArrayAssignmentQuadruple q = (ArrayAssignmentQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof ArrayLengthQuadruple){
                ArrayLengthQuadruple q = (ArrayLengthQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof IfQuadruple){
                IfQuadruple q = (IfQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof NewArrayQuadruple){
                NewArrayQuadruple q = (NewArrayQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof ArrayLookupQuadruple){
                ArrayLookupQuadruple q = (ArrayLookupQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof NewObjectQuadruple){
                NewObjectQuadruple q = (NewObjectQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof AssignmentQuadruple){
                AssignmentQuadruple q = (AssignmentQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof ParameterQuadruple){
                ParameterQuadruple q = (ParameterQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof CallQuadruple){
                CallQuadruple q = (CallQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof ReturnQuadruple){
                ReturnQuadruple q = (ReturnQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof GotoQuadruple){
                GotoQuadruple q = (GotoQuadruple) quadruple;
                generate(q);
            } else if (quadruple instanceof UnaryAssignmentQuadruple){
                UnaryAssignmentQuadruple q = (UnaryAssignmentQuadruple) quadruple;
                generate(q);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* 
        Tell if String is numeirc or not
    */
    private boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }  


    /* 
        Tells you what type of reg is needed for such ir argument or result
    */
    private int getRegType(String s){
        if (isNumeric(s)){
            return CONST;
        } else if (s.charAt(0)=='_'){
            return TEMP;
        }

        return  VAR;
    }
    

    /*
        Write text to asm file 
    */
    private void writeMipsCode(String mipsCode) throws IOException{
        this.fw.write("\t"+mipsCode+"\n");
    }

    /* 
        <==== Generate functions to make mips code  ====>
    */
    public void generate(CopyQuadruple instruction) throws IOException{
        /* 
            Get argument one 
        */
        Symbol arg1 = instruction.getFirstArgument();
        int arg1RegType = getRegType(arg1.getName().s);
        /* 
            Get result type
        */
        Symbol res = instruction.getResult();
        int resRegType = getRegType(res.getName().s);
        String resReg ="";
        /* 
            Mips code to write too
        */
        String mipsCode = "";

        /* 
            Handle result reg
        */
        if (resRegType == TEMP){
            resReg = regAlloc.allocateTempReg(res.getName().s);
        } else if (resRegType == VAR){
            resReg = regAlloc.getPramReg(res.getName().s);
            System.out.println(resReg);
        }
        /* 
            Handle arg1 reg
        */
        if (arg1RegType == CONST){
            mipsCode = "li "+resReg+", "+arg1.getName().s;
        } else if (arg1RegType == TEMP){
            mipsCode = "move "+resReg+", "+regAlloc.allocateTempReg(arg1.getName().s);
        } else if (arg1RegType == VAR){
            /* 
                if(arg1.getOffset() == -1)
				{
					temp = "move " + resultReg + ", " + arg1.getRegister() + "\n";
				}
				else //Class variable
				{
					temp = "lw " + resultReg + ", " + arg1.getOffset() + "($a0)\n";
				}
            */
            
        }

        /* 
            Write the mips code
        */
        writeMipsCode("# CopyQuadruple");
        writeMipsCode(mipsCode);

        /* 
            If a variable result then write result reg back into memory
        */
        if (resRegType==VAR){
            /*
            
				if(result.getOffset() == -1)
				{
					temp = "move " + result.getRegister() + ", " + resultReg + "\n";
				}
				else //Class variable
				{
					temp = "sw " + resultReg + ", " + result.getOffset() + "($a0)\n";
				}
				bw.write(temp, 0, temp.length());
            */
        }

    
    }

    public void generate(ParameterQuadruple instruction) throws IOException{
        /* 
            Check for parameter overflow
        */
        if (paramNum==MAX_PARAM_NUM){
            System.out.println("Error: More then 4 parameters for a function");
            System.exit(-1);
        }
        /* 
            Get arg1 
        */
        Symbol arg1 = instruction.getFirstArgument();
        int arg1RegType = getRegType(arg1.getName().s);
        /* 
            Mips code
        */
        String mipsCode = "";
        String paramReg = "$a"+paramNum;
        if (arg1RegType == CONST){
            mipsCode= "li "+paramReg+", "+arg1.getName().s;
        } else if (arg1RegType == TEMP){
            mipsCode="move "+paramReg+", "+regAlloc.allocateTempReg(arg1.getName().s);
        } else if (arg1RegType == VAR){
            /* 
                if(arg1.getOffset() == -1)
				{
					temp = "move " + resultReg + ", " + arg1.getRegister() + "\n";
				}
				else //Class variable
				{
					temp = "lw " + resultReg + ", " + arg1.getOffset() + "($a0)\n";
				}
            */
        }

        /* 
            Write mips code
        */
        writeMipsCode("# ParameterQuadruple");
        writeMipsCode(mipsCode);

        /* 
            Inc the param number
        */
        paramNum++;
    }


    /*
        Saves all the registers in the prelouge
    */
    private void prelouge(){

    }
    

    public void generate(CallQuadruple instruction) throws IOException{
        /* 
            Set function param back to zero
        */  
        paramNum=0;
        /* 
            Get arg1
        */
        MethodSymbol arg1 =(MethodSymbol) instruction.getFirstArgument();
        String methodName = arg1.getName().s;
        /* 
            Get result
        */
        Symbol res = instruction.getResult();
        String resReg = "";
        if (res!=null) {
            int resRegType = getRegType(res.getName().s);
            if (resRegType==TEMP){
                resReg = regAlloc.allocateTempReg(res.getName().s);
            } else if (resRegType==VAR){
                /* 
                    @TODO
                */
            }
        }
        /* 
            Check if it's one of the linked function
        */
        writeMipsCode("# CallQuadruple");

        if (methodName.equals("System.out.println")){
            String mipsCode="jal _system_out_println";
            writeMipsCode(mipsCode);
        } else if (methodName.equals("System.exit")){
            String mipsCode="jal _system_exit";
            writeMipsCode(mipsCode);
        } else {
            /* 
                Prelouge
            */
            

            /* 
                Set the variable params
            */
            ArrayList<VariableSymbol> parameters = arg1.getParameters();
            int i=0;
            for (VariableSymbol param: parameters){
                regAlloc.allocatePramReg(param.getName().s, "$a"+(i+1));
                i++;
            }
            /* 
                Call function
            */
            
            writeMipsCode("jal "+methodName);

            /* 
               Epilouge
            */
        }
        
        /* 
            Check if store the result back in a temp
            or a variable
        */
        if (res!=null){
            writeMipsCode("move "+resReg+", "+"$v0");
        }
        
    }


    public void generate(AssignmentQuadruple instruction) throws IOException{
        /* 
            Get arg1
        */  
        Symbol arg1 = instruction.getFirstArgument();
        int arg1RegType = getRegType(arg1.getName().s);
        /* 
            Get arg2
        */
        Symbol arg2 = instruction.getSecondArgument();
        int arg2RegType = getRegType(arg2.getName().s);
        /* 
            Get result
        */
        Symbol res = instruction.getResult();
        String resReg = "";
        int resRegType = getRegType(res.getName().s);
        /* 
            Get op
        */
        int op = instruction.getOperator();
        /* 
            Mips Code
        */
        String mipsCode = "";
        int numTempRegs = 0;

        /* 
            Handle res reg
        */
        if (resRegType==TEMP){
            resReg = regAlloc.allocateTempReg(res.getName().s);
        } else if (resRegType == VAR){
            resReg = regAlloc.allocateOffsetReg(numTempRegs);
            numTempRegs++;
        }
        

        /* 
            Handle arg1 
        */
        if (arg1RegType == CONST){
            mipsCode = "li "+resReg+", "+arg1.getName().s;
        } else if (arg1RegType == TEMP){
            mipsCode = "move "+resReg+", "+regAlloc.allocateTempReg(arg1.getName().s);
        } else if (arg1RegType == VAR){
            mipsCode = "move "+resReg+", "+regAlloc.getPramReg(arg1.getName().s); 
        }

        /* 
            Write Mips code
        */
        writeMipsCode("# AssignmentQuadruple");
        writeMipsCode(mipsCode);
        mipsCode = "";

        /* 
            Handle arg2 
        */
        String opStr = "";
        String arg2Str = "";
        if (arg2RegType == CONST){
            arg2Str=arg2.getName().s;
            if      (op==AssignmentQuadruple.ADD) opStr="addi";
            else if (op==AssignmentQuadruple.SUB) opStr="subi";
            else if (op==AssignmentQuadruple.MUL){
                String tempReg  = regAlloc.allocateOffsetReg(numTempRegs);
                numTempRegs++;
                writeMipsCode("li "+tempReg+", "+arg2Str);
                opStr="mul";
                writeMipsCode("mul "+resReg+", "+resReg+", "+tempReg);   
            }
            else if (op==AssignmentQuadruple.LT ) opStr="slti";
            else if (op==AssignmentQuadruple.AND) opStr="andi";

            /* 
                Mul was differnt format needed two instructions
                the rest follow the same format
            */
            if (op!=AssignmentQuadruple.MUL) writeMipsCode(opStr+" "+resReg+", "+arg2Str);
        } else if (arg2RegType == TEMP){
            String arg2Reg = regAlloc.allocateTempReg(arg2.getName().s);
            if      (op==AssignmentQuadruple.ADD) opStr="add";
            else if (op==AssignmentQuadruple.SUB) opStr="sub";
            else if (op==AssignmentQuadruple.MUL) opStr="mul";
            else if (op==AssignmentQuadruple.LT ) opStr="slt";
            else if (op==AssignmentQuadruple.AND) opStr="and";

            writeMipsCode(opStr+" "+resReg+", "+resReg+", "+arg2Reg);
        } else if (arg2RegType == VAR){
            /* 
                @TODO
            */
            String arg2Reg = regAlloc.getPramReg(arg2.getName().s);
            if      (op==AssignmentQuadruple.ADD) opStr="add";
            else if (op==AssignmentQuadruple.SUB) opStr="sub";
            else if (op==AssignmentQuadruple.MUL) opStr="mul";
            else if (op==AssignmentQuadruple.LT ) opStr="slt";
            else if (op==AssignmentQuadruple.AND) opStr="and";

            writeMipsCode(opStr+" "+resReg+", "+resReg+", "+arg2Reg);
        }

        /* 
            Need to do store result reg back into var memory location
        */
        if (resRegType==VAR){

        }

    }


    public void generate(ReturnQuadruple instruction) throws IOException{
        VariableSymbol arg1 = (VariableSymbol) instruction.getFirstArgument();
    
        String arg1Reg = regAlloc.getPramReg(arg1.getName().s);
        arg1Reg = arg1Reg==null?"$a0":arg1Reg;
        writeMipsCode("move $v0, "+arg1Reg);
        writeMipsCode("jr $ra");
        
        
    }
    
    public void generate(NewObjectQuadruple instruction) throws IOException{}
    public void generate(ArrayAssignmentQuadruple instruction){}
    public void generate(ArrayLengthQuadruple instruction){}
    public void generate(IfQuadruple instruction){}
    public void generate(NewArrayQuadruple instruction){}
    public void generate(ArrayLookupQuadruple instruction){}
    public void generate(GotoQuadruple instruction){}
    public void generate(UnaryAssignmentQuadruple instruction){}
}
