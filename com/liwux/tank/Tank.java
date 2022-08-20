package com.liwux.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int speed = 10;

    private boolean moving = false;

    public Tank(int x, int y,Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paint(Graphics g){
        g.fillRect(x,y,50,50);
        moving();
    }

    private void moving(){
        if (!moving) return;
        if (x>800) x=0;
        if (y>600) y=0;
        if (x<0) x=800;
        if (y<0) y=600;
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
    }

}
