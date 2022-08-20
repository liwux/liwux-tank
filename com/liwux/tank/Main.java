package com.liwux.tank;

public class Main {
    public static void main(String[] arg) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
       }
}
