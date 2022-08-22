package com.liwux.singleton;

/**
 * 懒汉式，加锁效率会降低很多
 */
public class Mgr04 {
    private static Mgr04 INSTANCE;

    private Mgr04(){}

    public static synchronized Mgr04 getInstance(){
        if (INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Mgr04.getInstance().hashCode());
            }).start();

//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Mgr03.getInstance().hashCode());
//                }
//            }).start();
        }
    }
}
