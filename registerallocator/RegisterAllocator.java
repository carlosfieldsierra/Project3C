package registerallocator;
import java.util.*;


public class RegisterAllocator {
    public HashMap<String,String> varToReg;
    private final int MAX_REGS = 18;
    private int idNum;


    public RegisterAllocator(){
        varToReg = new HashMap<>();
        idNum = 0;
    }

    public void checkForRegOverFlow(){
        if (idNum==MAX_REGS){
            System.out.println("\nError: Ran out of registers\n");
            System.exit(-1);
        }
    }

    public void checkForRegOverFlow(int num){
        if (num==MAX_REGS){
            System.out.println("\nError: Ran out of registers\n");
            System.exit(-1);
        }
    }
    private String makeReg(int num){
        String reg;
        if (num<10){
            reg="$t"+num;
        } else {
            reg="$s"+(num-10);
        }
        return reg;

    }

    private String makeReg(){
        String reg;
        if (idNum<10){
            reg="$t"+idNum;
        } else {
            reg="$s"+(idNum-10);
        }
        idNum+=1;
        return reg;

    }

    /* 
        Allocate Non Map Reg 
        ()
    */
    public String allocateNonMapReg(){
        /*  
            Check for reg overflow
        */
        checkForRegOverFlow();
        /*
            Assign reg
        */
        String reg = makeReg();
        
        return reg;
    }
    

    /* 
        Allocate temp reg
        (String name)
    */
    public String allocateTempReg(String name){
        /*
            Check for reg overflow
        */
        checkForRegOverFlow();

        if (!varToReg.containsKey(name)){
            varToReg.put(name, makeReg());
        }
        
        return varToReg.get(name);
    }

    /* 
        Allocate regs with offset
    */
    public String allocateOffsetReg(int offset){
        offset+=idNum;
        /* 
            Check for overflow
        */  
        checkForRegOverFlow(offset);

        return makeReg(offset);
    }

   
}
