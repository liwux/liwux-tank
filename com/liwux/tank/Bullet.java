package com.liwux.tank;

import java.awt.*;

public class Bullet {
    private int x,y;
    private final Dir dir;
    private static final int speed = 10;
    public static int bulletWidth = ResourceMgr.bulletD.getWidth();
    public static int bulletHeight = ResourceMgr.bulletD.getHeight();
    private boolean live = true;
    private Group group = Group.BAD;

    private final TankFrame tankFrame;

    Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = bulletWidth;
        rectangle.height = bulletHeight;
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
        }
        rectangle.x = this.x;
        rectangle.y = this.y;
        if (x<0 || y <0 || x> TankFrame.GAME_WIDTH || y>TankFrame.GAME_HEIGHT) live = false;
    }

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup())return;

         if (rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            int eX = tank.getX()+Tank.tankWidth/2-Explode.bulletWidth/2;
            int eY = tank.getY()+Tank.tankHeight/2-Explode.bulletHeight/2;

            tankFrame.explodeList.add(new Explode(eX,eY,tankFrame));
        }
    }

    private void die() {
        this.live = false;
    }
}
