package com.liwux.tank.chain;

import com.liwux.tank.Bullet;
import com.liwux.tank.GameObject;
import com.liwux.tank.Tank;

public class TankTankCollider implements Collider{
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;
            if (tank1.getRectangle().intersects(tank2.getRectangle())){
                tank1.back();
                tank2.back();
            }
        }
        return true;
    }
}
