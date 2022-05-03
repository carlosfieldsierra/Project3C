class Test {
    public static void main(String[] args) {
        
        System.out.println(new Dad().start(1,1,1)+new Dad().done(2,2,2));
        
    }
}


class Dad{

    public int start(int a,int b,int c){
      
        return a+b+c;
    }

    public int done(int a,int b, int c){
        return  a+b+c;
    }

  
}
