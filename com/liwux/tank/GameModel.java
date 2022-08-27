package com.liwux.tank;

import com.liwux.tank.chain.BulletTankCollider;
import com.liwux.tank.chain.Collider;
import com.liwux.tank.chain.ColliderChain;
import com.liwux.tank.chain.TankTankCollider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameModel {
    private static final GameModel INSTANCE = new GameModel();

    static {
        INSTANCE.init();
    }

    Tank myTank;


    public static GameModel getInstance(){
        return INSTANCE;
    }

    private void init(){
        myTank = new Tank(200,400, Dir.DOWN, Group.GOOD);
        int initTankCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        //初始化地方坦克
        for (int i=0;i<initTankCount;i++){
            new Tank(50+i*60,200, Dir.DOWN, Group.BAD);
        }
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
    }


    ColliderChain chain = new ColliderChain();

    List<GameObject> objects = new ArrayList<>();

    private GameModel(){

    }

    public void add(GameObject gameObject){
        this.objects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        this.objects.remove(gameObject);
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.white);
//        g.drawString("子弹的数量："+ bulletList.size(),10,50);
//        g.drawString("敌人的数量："+ tankList.size(),10,80);
//        g.drawString("爆炸的数量："+ explodeList.size(),10,100);

        g.setColor(c);

        myTank.paint(g);
        for (int i=0;i<objects.size();i++){
            objects.get(i).paint(g);
        }

        //碰撞逻辑
        for (int i=0;i<objects.size();i++){
            for (int j=i+1;j<objects.size();j++){
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collider(o1,o2);
            }
        }

//        for (int i=0;i<bulletList.size();i++){
//            for (int j=0;j<tankList.size();j++){
//                bulletList.get(i).collideWith(tankList.get(j));
//            }
//        }
    }

    public Tank getMyTank(){
        return myTank;
    }
}
