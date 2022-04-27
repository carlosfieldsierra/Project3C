package codegen;
import ir.*;
import visitor.symbol.MethodSymbol;
import visitor.symbol.Symbol;
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
    int paramNum = 0;

    
    
    public MipsGenerator(String filePath,HashMap<String,ArrayList<Quadruple>> methodsIR){
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
            Loop through methods Ir
        */
        for (String methodNameKey: methodsIR.keySet()){
            // Methods Ir
            ArrayList<Quadruple> irList = methodsIR.get(methodNameKey);
        
            /* 
                Write method label
            */
            try {
                fw.write(methodNameKey+":\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(methodNameKey);
            /* 
                Generate mips on method per method basis
            */
            for (Quadruple quadruple: irList){
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


    
    public void generate(CopyQuadruple instruction) throws IOException{
        Symbol arg1 = instruction.getFirstArgument();
        System.out.println(arg1.getType());
        Symbol res = instruction.getResult();
        /* 
            If a constant
        */
        if (arg1 instanceof visitor.symbol.Constant){
            /* 
                Make temp reg
            */  
            String tempReg = regAlloc.allocateReg(res.getName().s);
            this.fw.write("\taddi "+tempReg+", $zero, "+arg1.getName()+"\n");
        }

    }

    public void generate(ParameterQuadruple instruction) throws IOException{
        if (paramNum>3){
            System.exit(-1);
        }
        
        Symbol arg1 = instruction.getFirstArgument();
        System.out.println(arg1.getType());
        if (arg1.getType() instanceof IdentifierType ){
            return;
        }

        if (arg1 instanceof visitor.symbol.VariableSymbol){

            this.fw.write("\tadd $a"+paramNum+",$zero,"+regAlloc.allocateReg(arg1.getName().s)+"\n");
            paramNum+=1;
        }
        
    }
    
    public void generate(CallQuadruple instruction) throws IOException{
        // Set back to zero
        paramNum=0;

        /* 
            Get arg1 and result
        */  
        MethodSymbol arg1 =(MethodSymbol) instruction.getFirstArgument();
        Symbol res = instruction.getResult();
        /* 
            Get result reg
        */
        String resReg="";
        if (res!=null){
            resReg = regAlloc.allocateReg(res.getName().s);
        }
        /* 
            Get method name
        */
        String methodName = arg1.getName().s;
        /*
            Check if it's system exit, or println
        */
        if (methodName.equals("System.out.println")){
            this.fw.write("\tjal _system_out_println\n");
        } else if (methodName.equals("System.exit")){
            this.fw.write("\tjal _system_exit\n");
        } else {
            /* 
                Function Prelouge
            */
            // this.fw.write("\t# Function Prelouge\n");
            // this.fw.write("\taddiu $sp, $sp, -32\n");
            // this.fw.write("\tsw    $fp, 0($sp)\n");
            // this.fw.write("\tsw    $ra, 4($sp)\n");
            // this.fw.write("\tsw    $a0, 8($sp)\n");
            // this.fw.write("\tsw    $a1, 16($sp)\n");
            // this.fw.write("\tsw    $a2, 20($sp)\n");
            // this.fw.write("\tsw    $a3, 28($sp)\n");
            // this.fw.write("\taddiu $fp, $sp, 32\n");
            this.fw.write("\tjal "+curClass+"_"+methodName+"\n");
            if (res!=null){
                this.fw.write("\tadd "+resReg+", $zero, "+"$v0\n");
            }
        }

       
        

        // System.out.println(
        //     "jal _system_out_println"
        // );
    }

    public void generate(AssignmentQuadruple instruction) throws IOException{
        Symbol arg1 = instruction.getFirstArgument();
        Symbol arg2 = instruction.getSecondArgument();
        Symbol res = instruction.getResult();
        int op = instruction.getOperator();
        /* 
            Arg1
        */
        String arg1Reg = "";
        if (arg1 instanceof visitor.symbol.VariableSymbol){
            arg1Reg = regAlloc.allocateReg(arg1.getName().s);
        }
        /* 
            Result
        */
        String resReg = "";
        if (res instanceof visitor.symbol.VariableSymbol){
            resReg = regAlloc.allocateReg(res.getName().s);
        }
        /* 
            Arg2
        */
        String arg2Reg = "";
        if (arg2 instanceof visitor.symbol.VariableSymbol){
            arg2Reg = regAlloc.allocateReg(arg2.getName().s);
        }
        /* 
            Op
        */
        if (op==instruction.ADD){
            fw.write("\tadd "+resReg+", "+arg1Reg+", "+arg2Reg+"\n");
        }
        else if (op==instruction.SUB){
            fw.write("\tsub "+resReg+", "+arg1Reg+", "+arg2Reg+"\n");
        }
        else if (op==instruction.MUL){
            fw.write("\tmul "+resReg+", "+arg1Reg+", "+arg2Reg+"\n");
        }
        else if (op==instruction.LT ){
            fw.write("\tslt "+resReg+", "+arg1Reg+", "+arg2Reg+"\n");
        }        
        else if (op==instruction.AND){
            fw.write("\tand "+resReg+", "+arg1Reg+", "+arg2Reg+"\n");
        }
    
       
    }

    public void generate(ReturnQuadruple instruction){
        Symbol arg1 = instruction.getFirstArgument();
        System.out.println(arg1.getClass());
        if (arg1 instanceof visitor.symbol.VariableSymbol){
            
        }
    }

    public void generate(NewObjectQuadruple instruction) throws IOException{
       /* 
            Get arg1
       */
       Symbol arg1 = instruction.getFirstArgument();
       String className = arg1.getName().s;
       /* 
            Get result
       */
       Symbol res = instruction.getResult();
   

       curClass = className;
    }

    public void generate(ArrayAssignmentQuadruple instruction){}
    public void generate(ArrayLengthQuadruple instruction){}
    public void generate(IfQuadruple instruction){}
    public void generate(NewArrayQuadruple instruction){}
    public void generate(ArrayLookupQuadruple instruction){}
    public void generate(GotoQuadruple instruction){}
    public void generate(UnaryAssignmentQuadruple instruction){}




    

}
