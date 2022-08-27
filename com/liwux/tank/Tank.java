package com.liwux.tank;

import com.liwux.tank.observer.TankFireEvent;
import com.liwux.tank.observer.TankFireHandler;
import com.liwux.tank.observer.TankFireObserver;
import com.liwux.tank.strategy.FireStrategy;
import com.liwux.tank.strategy.RandomFire;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Tank extends GameObject{
    int oldX,oldY;
    public Dir dir = Dir.DOWN;
    private static final int speed = 5;

    private boolean moving = true;

    private boolean live = true;

    private Random random = new Random();

    public Group group = Group.BAD;

    public static int tankWidth = ResourceMgr.badTankD.getWidth();
    public static int tankHeight = ResourceMgr.badTankD.getHeight();

    FireStrategy fs = new RandomFire();

    public Rectangle getRectangle() {
        return rectangle;
    }

    Rectangle rectangle = new Rectangle();

    public Tank(int x, int y,Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width= tankWidth;
        rectangle.height=tankHeight;
        GameModel.getInstance().add(this);

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

    @Override
    public void paint(Graphics g){
        if (!live) {
            GameModel.getInstance().remove(this);
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

    @Override
    public int getWidth() {
        return tankWidth;
    }

    @Override
    public int getHeight() {
        return tankHeight;
    }

    private void move(){
        this.oldX = x;
        this.oldY = y;
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

    public void back(){
        this.x = oldX;
        this.y = oldY;

    }

    private void boundsChecks() {
        if (this.x <5 ) this.x = 5;
        if (this.y < 28) this.y = 28;
        if (this.x > TankFrame.GAME_WIDTH-Tank.tankWidth-2) x= TankFrame.GAME_WIDTH-Tank.tankWidth-2;
        if (this.y > TankFrame.GAME_HEIGHT-Tank.tankHeight-2) y= TankFrame.GAME_HEIGHT-Tank.tankHeight-2;

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

    public void stop() {
        moving = false;
    }

    private List<TankFireObserver> fireObserverList = Arrays.asList(new TankFireHandler());
    public void handleFireKey(){
        TankFireEvent event = new TankFireEvent(this);
        for (TankFireObserver o:fireObserverList){
            o.actionOnFire(event);
        }
    }
}
