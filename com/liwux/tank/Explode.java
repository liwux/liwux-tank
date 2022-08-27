package com.liwux.tank;

import java.awt.*;

public class Explode extends GameObject{


    public static int bulletWidth = ResourceMgr.explodes[0].getWidth();
    public static int bulletHeight = ResourceMgr.explodes[0].getHeight();
    private int x,y;

    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length)
        {
            GameModel.getInstance().remove(this);
            step = 0;

        }
    }
}
