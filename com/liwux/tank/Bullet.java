package com.liwux.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private Dir dir;
    private static final int speed = 10;
    private static final int WIDTH =10,HEIGHT=10;

    private boolean live = true;

    TankFrame tankFrame = null;

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
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);
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
        }

        if (x<0 || y <0 || x> TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) live = false;
    }

}
