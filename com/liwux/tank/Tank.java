package com.liwux.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int speed = 5;

    private final TankFrame tf;

    private boolean moving = true;

    private boolean live = true;

    private Random random = new Random();

    private Group group = Group.BAD;

    public static int tankWidth = ResourceMgr.badTankD.getWidth();
    public static int tankHeight = ResourceMgr.badTankD.getHeight();

    Rectangle rectangle = new Rectangle();

    public Tank(int x, int y,Dir dir,Group group,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width= tankWidth;
        rectangle.height=tankHeight;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL:ResourceMgr.badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR:ResourceMgr.badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU:ResourceMgr.badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD:ResourceMgr.badTankD,x,y,null);
                break;
        }
        move();
    }

    private void move(){
        if (!moving) return;
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
        //new Thread(()->new Audio("audio/tank_move.wav").play()).start();


        if (this.group==Group.BAD&&random.nextInt(100)>95)
            this.fire();
        randomMove();
        boundsChecks();

        rectangle.x=this.x;
        rectangle.y=this.y;
    }

    private void boundsChecks() {
        if (this.x <5 ) this.x = 5;
        if (this.y < 28) this.y = 28;
        if (this.x > this.tf.getWidth()-Tank.tankWidth-2) x= tf.getWidth()-Tank.tankWidth-2;
        if (this.y > this.tf.getHeight()-Tank.tankHeight-2) y= tf.getHeight()-Tank.tankHeight-2;

    }

    private void randomMove() {
        if (this.group==Group.BAD && random.nextInt(100)>95)
            this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire(){
        int bX = this.x + tankWidth/2 - Bullet.bulletWidth/2;
        int bY = this.y + tankHeight/2 - Bullet.bulletHeight/2;
        tf.bulletList.add(new Bullet(bX,bY,this.dir,this.group,this.tf));
        new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.live = false;
    }
}
