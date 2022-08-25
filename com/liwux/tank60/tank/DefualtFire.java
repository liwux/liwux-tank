package com.liwux.tank60.tank;

public class DefualtFire implements FireStrategy {

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.tankWidth/2 - Bullet.bulletWidth/2;
        int bY = tank.y + Tank.tankHeight/2 - Bullet.bulletHeight/2;
        new Bullet(bX,bY,tank.dir,tank.group,tank.gameModel);
        new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
