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

    public Bullet(int x, int y, Dir dir,Group group,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

    public void collideWith(Tank tank) {
        if (this.group == tank.getGroup())return;

        //TODO: 用一个rect来记录当前位置
        Rectangle rectangle1 = new Rectangle(this.x,this.y,bulletWidth,bulletHeight);
        Rectangle rectangle2 = new Rectangle(tank.getX(),tank.getY(), Tank.tankWidth,Tank.tankHeight);
        if (rectangle1.intersects(rectangle2)){
            tank.die();
            this.die();
            tankFrame.explodeList.add(new Explode(x,y,tankFrame));
        }
    }

    private void die() {
        this.live = false;
    }
}
