package com.liwux.tank;

import com.liwux.tank.abstractfactory.BaseTank;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Tank extends BaseTank {
    int x,y;
    Dir dir = Dir.DOWN;
    private static final int speed = 5;

    final TankFrame tf;

    private boolean moving = true;

    private boolean live = true;

    private Random random = new Random();

    Group group = Group.BAD;

    public static int tankWidth = ResourceMgr.badTankD.getWidth();
    public static int tankHeight = ResourceMgr.badTankD.getHeight();

    FireStrategy fs = new RandomFire();

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

        if (group ==Group.BAD) {
            String badFS = (String) PropertyMgr.get("badFS");
            try {
                fs = (FireStrategy) Class.forName(badFS).getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }else {
            fs = new RandomFire();
        }
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
        fs.fire(this);
    }

    public void die() {
        this.live = false;
    }
}
