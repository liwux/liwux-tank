package com.liwux.tank;

import java.awt.*;

public class Explode {


    public static int bulletWidth = ResourceMgr.explodes[0].getWidth();
    public static int bulletHeight = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    TankFrame tankFrame =null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length){
            step=0;
        }
    }
}
