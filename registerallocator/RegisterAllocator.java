package registerallocator;
import java.util.*;


public class RegisterAllocator {
    public HashMap<String,String> varToReg;
    int tempNum;

    public RegisterAllocator(){
        varToReg = new HashMap<>();
        tempNum = 0;
    }

    
    

    public String allocateReg(String name){
        if (!varToReg.containsKey(name)){
            return makeTempReg(name);
        }
        return varToReg.get(name);
    }


    private String makeTempReg(String name){
        /* 
            Error if more than 9 temp regs
        */
        if (tempNum>9){
            System.out.println("\nError - Out of temp registers\n");
            System.exit(0);
        }
        /*  
            Make temp regs
        */
        String tempReg = "$t"+tempNum;
        tempNum++;
        /* 
            Store in map
        */
        varToReg.put(name,tempReg);

        return tempReg;
    }

}
