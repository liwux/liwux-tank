package com.liwux.tank;

import java.awt.*;

public class Explode extends GameObject{


    public static int bulletWidth = ResourceMgr.explodes[0].getWidth();
    public static int bulletHeight = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    GameModel gameModel =null;

    private int step = 0;

    public Explode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;

        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step >= ResourceMgr.explodes.length)
            gameModel.remove(this);

    }
}
