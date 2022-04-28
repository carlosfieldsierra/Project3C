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
        String reg = "";

        if (idNum<10){
            reg="$t"+idNum;
        } else {
            reg="$s"+(idNum-10);
        }
        
        idNum+=1;

        return reg;
    }
    
    

   
}
