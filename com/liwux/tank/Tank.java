package com.liwux.tank;

import java.awt.*;

public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int speed = 10;

    private final TankFrame tf;

    private boolean moving = false;

    private boolean live = true;

    public static int tankWidth = ResourceMgr.tankD.getWidth();
    public static int tankHeight = ResourceMgr.tankD.getHeight();

    public Tank(int x, int y,Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
        if (!live) {
            tf.tankList.remove(this);
        };
        switch (dir){
            case LEFT:
                g.drawImage(ResourceMgr.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD,x,y,null);
                break;
            case LEFT_UP:
                g.drawImage(ResourceMgr.tankLU,x,y,null);
                break;
            case LEFT_DOWN:
                g.drawImage(ResourceMgr.tankLD,x,y,null);
                break;
            case RIGHT_UP:
                g.drawImage(ResourceMgr.tankRU,x,y,null);
                break;
            case RIGHT_DOWN:
                g.drawImage(ResourceMgr.tankRD,x,y,null);
                break;

        }
        move();
    }

    private void move(){
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

    public void fire(){
        int bX = this.x + tankWidth/2 - Bullet.bulletWidth/2;
        int bY = this.y + tankHeight/2 - Bullet.bulletHeight/2;
        tf.bulletList.add(new Bullet(bX,bY,this.dir,this.tf));
    }

    public void die() {
        this.live = false;
    }
}
