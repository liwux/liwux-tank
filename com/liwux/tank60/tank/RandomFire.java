package com.liwux.tank60.tank;

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
        new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
