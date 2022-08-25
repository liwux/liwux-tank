package com.liwux.tank;

public class Main {
    public static void main(String[] arg) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        //背景音乐
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
       }
}
