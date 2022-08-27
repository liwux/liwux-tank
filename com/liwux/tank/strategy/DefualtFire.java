package com.liwux.tank.strategy;

import com.liwux.tank.Bullet;
import com.liwux.tank.Tank;

public class DefualtFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.tankWidth/2 - Bullet.bulletWidth/2;
        int bY = tank.y + Tank.tankHeight/2 - Bullet.bulletHeight/2;
        new Bullet(bX,bY,tank.dir,tank.group);
        //new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
