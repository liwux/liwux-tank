package com.liwux.tank;

import java.awt.*;

public class Bullet extends GameObject{
    int x,y;
    Dir dir;
    private static final int speed = 10;
    public static int bulletWidth = ResourceMgr.bulletD.getWidth();
    public static int bulletHeight = ResourceMgr.bulletD.getHeight();
    private boolean live = true;
    Group group = Group.BAD;

    public Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = bulletWidth;
        rectangle.height = bulletHeight;

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g){
        if (!live) {
            GameModel.getInstance().remove(this);
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
        if (x<0 || y <0 || x> TankFrame.GAME_WIDTH || y> TankFrame.GAME_HEIGHT) live = false;
    }

    public boolean collideWith(Tank tank) {
        if (this.group == tank.getGroup()) return false;
        if (rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            int eX = tank.getX()+ Tank.tankWidth/2- Explode.bulletWidth/2;
            int eY = tank.getY()+ Tank.tankHeight/2- Explode.bulletHeight/2;
            GameModel.getInstance().objects.add(new Explode(eX,eY));
            return true;
        }
        return false;
    }

     public void die() {
        this.live = false;
    }
}
