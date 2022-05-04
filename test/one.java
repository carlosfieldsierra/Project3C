class Test {
    public static void main(String[] args) {
        
        /*
            3+6=9
        */
        System.out.println(new Dad().start(1,1,1)+new Dad().done(2,2,2));
        
    }
}


class Dad{

    public int start(int a,int b,int c){
        int sum;
        sum = a+b+c;
        sum = sum*2;
        System.out.println(sum);
        return sum;
    }

    public int done(int a,int b, int c){
        return  a+b+c;
    }

  
}
