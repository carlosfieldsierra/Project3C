
class Milestone3 {
    public static void main(String[] args){
        System.out.println(
            new G().getOne() // 1
            +new G().getTwo() // 2
            +new G().getZero()  // 0
            +new G().addNums(1, 1, 1) // 3
            ); // = 6
    }
}

class G{
    public int addNums(int a,int b,int c){
        return a+b+c;
    }
    public int getZero(){
        return 0;
    }
    public int getOne(){
        return 1;
    }
    public int getTwo(){
        return 2;
    }
}
