
class Milestone4 {
    public static void main(String[] args){
        System.out.println(
            new Test().printThenAdd(0,1,2)
            + 
            new Stuff().addAll(10,10,10)
        );
    }
}

class Test{

    public int printThenAdd(int a,int b,int c){
        int sum;
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        sum =a;
        sum = sum+b;
        sum = sum+c;
        return sum;
    }
}

class Stuff{

    public int addAll(int i,int j,int k){
        int x;
        int y;
        int z;
        x=i;
        y=j;
        z=k;
        x=x+y+z;
        return x;
    }
}