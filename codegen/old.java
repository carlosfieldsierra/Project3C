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
    int paramNum = 0;
    /* 
        Reg Types
    */
    private final int TEMP = 0;
    private final int CONST = 1;
    private final int VAR = 2;


    

    
    
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
        <==== Generate functions to make mips code  ====>
    */
    public void generate(CopyQuadruple instruction) throws IOException{}
    public void generate(ParameterQuadruple instruction) throws IOException{}
    public void generate(CallQuadruple instruction) throws IOException{}
    public void generate(AssignmentQuadruple instruction) throws IOException{}
    public void generate(ReturnQuadruple instruction){}
    public void generate(NewObjectQuadruple instruction) throws IOException{}
    public void generate(ArrayAssignmentQuadruple instruction){}
    public void generate(ArrayLengthQuadruple instruction){}
    public void generate(IfQuadruple instruction){}
    public void generate(NewArrayQuadruple instruction){}
    public void generate(ArrayLookupQuadruple instruction){}
    public void generate(GotoQuadruple instruction){}
    public void generate(UnaryAssignmentQuadruple instruction){}
}
