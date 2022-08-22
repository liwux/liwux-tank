package com.liwux.singleton;

/**
 * 懒汉式，加锁效率会降低很多
 */
public class Mgr06 {
    private static Mgr06 INSTANCE;

    private Mgr06(){}

    public static Mgr06 getInstance(){
        if (INSTANCE == null){
            synchronized (Mgr06.class){
                if (INSTANCE == null){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    INSTANCE = new Mgr06();
                }

            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i=0;i<100;i++){
            new Thread(()->{
                System.out.println(Mgr06.getInstance().hashCode());
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
