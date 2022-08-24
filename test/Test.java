package test;

public class Test {
    public static void main(String[] args) {
        Aobing a = new Aobing();
        a.start();
        for (;;){
            synchronized (a){
                if (a.isFlag()) System.out.println("xxxx");
            }
        }
    }
}

class Aobing extends Thread{
    private  boolean flag = false;
    public boolean isFlag(){
        return flag;

    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        flag= true;
        System.out.println("flag"+flag);
    }
}
