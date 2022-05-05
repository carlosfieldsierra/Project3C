class Milestone5 {
    public static void main(String[] args) {
       System.out.println(
           new Cond().cond(100) // 25
           +
           new Cond().cond(0) // 5
           +
           new Recursive().makeVal(5, 0) // 5
       ); // = 35
    }
}

class Cond{

    public int cond(int a){
        int var;
        if (10<a){
            if (a<20){
               var = 15; 
            } else {
                var = 25;
            }
        }else {
            var=5;
        }
        return var;

    }
}
class Recursive{

    public int makeVal(int val ,int zero){
        int temp;
        if (val<zero){
            temp= zero-1;
        } else {
            System.out.println(zero);
            temp = this.makeVal(val, zero+1);
        }
        return temp;
    }
}
