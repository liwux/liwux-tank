package com.liwux.tank.strategy;

import com.liwux.tank.Bullet;
import com.liwux.tank.Dir;
import com.liwux.tank.Tank;

import java.util.Random;

public class RandomFire implements FireStrategy {
    Random random = new Random();
    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.tankWidth/2 - Bullet.bulletWidth/2;
        int bY = tank.y + Tank.tankHeight/2 - Bullet.bulletHeight/2;
        for (Dir dir: Dir.values()){
            new Bullet(bX,bY,dir,tank.group,tank.gameModel);
        }
        //new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
