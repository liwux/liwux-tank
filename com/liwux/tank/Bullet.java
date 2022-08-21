package com.liwux.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private final Dir dir;
    private static final int speed = 10;
    private static final int WIDTH =10,HEIGHT=10;

    private boolean live = true;

    private final TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }
    public void paint(Graphics g){
        if (!live) {
            tankFrame.bulletList.remove(this);
        }
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case LEFT_UP:
                g.drawImage(ResourceMgr.bulletLU,x,y,null);
                break;
            case LEFT_DOWN:
                g.drawImage(ResourceMgr.bulletLD,x,y,null);
                break;
            case RIGHT_UP:
                g.drawImage(ResourceMgr.bulletRU,x,y,null);
                break;
            case RIGHT_DOWN:
                g.drawImage(ResourceMgr.bulletRD,x,y,null);
                break;
        }
        move();
    }

    private void move(){
        switch (this.dir){
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT_UP:
                x -= speed;
                y -= speed;
                break;
            case LEFT_DOWN:
                x -= speed;
                y += speed;
                break;
            case RIGHT_UP:
                x += speed;
                y -= speed;
                break;
            case RIGHT_DOWN:
                x += speed;
                y += speed;
                break;
        }

        if (x<0 || y <0 || x> TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) live = false;
    }

}
