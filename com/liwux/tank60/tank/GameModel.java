package com.liwux.tank60.tank;

import com.liwux.tank60.tank.abstractfactory.BaseExplode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameModel {
    Tank myTank = new Tank(200,400, Dir.DOWN, Group.GOOD,this);

    List<Bullet> bulletList = new ArrayList<>();

    List<Tank> tankList = new ArrayList<>();
    List<Explode> explodeList = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.parseInt((String) Objects.requireNonNull(PropertyMgr.get("initTankCount")));

        //初始化地方坦克
        for (int i=0;i<initTankCount;i++){
            tankList.add(new Tank(50+i*60,200, Dir.DOWN, Group.BAD,this));
        }
    }

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量："+ bulletList.size(),10,50);
        g.drawString("敌人的数量："+ tankList.size(),10,80);
        g.drawString("爆炸的数量："+ explodeList.size(),10,100);

        g.setColor(c);

        myTank.paint(g);
        for (int i=0;i<bulletList.size();i++){
            bulletList.get(i).paint(g);
        }

        for (int i=0;i<tankList.size();i++){
            tankList.get(i).paint(g);
        }
        for (int i=0;i<explodeList.size();i++){
            explodeList.get(i).paint(g);
        }

        for (int i=0;i<bulletList.size();i++){
            for (int j=0;j<tankList.size();j++){
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }
    }

    public Tank getMyTank(){
        return myTank;
    }
}
