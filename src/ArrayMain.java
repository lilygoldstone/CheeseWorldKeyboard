public class ArrayMain {
    public Pipe[] MorePipes;



    public static void main(String[] args) {
        ArrayMain myApp = new ArrayMain();
    }
        public ArrayMain(){

            MorePipes=new Pipe[10];


            System.out.println("hello there!");
            for(int x=0;x<10; x++){
                MorePipes[x]=new Pipe (x+2,(int)(Math.random()*700));
            }
            for(int x=0;x<10; x++){
                System.out.println("here is my forest");
                System.out.println("tree:"+ x +"age:"+ MorePipes[x].age+ "height:" +MorePipes[x].height);
            }

        }


}
