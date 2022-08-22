package com.liwux.tank;

public class Main {
    public static void main(String[] arg) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();

        int initTankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));

        //初始化地方坦克
        for (int i=0;i<initTankCount;i++){
            tankFrame.tankList.add(new Tank(50+i*60,200,Dir.DOWN,Group.BAD,tankFrame));
        }


        //背景音乐
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
       }
}
