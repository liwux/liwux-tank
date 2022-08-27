package com.liwux.tank.chain;

import com.liwux.tank.GameObject;
import com.liwux.tank.Tank;
import com.liwux.tank.Wall;

public class TankWallCollider implements Collider{
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall){
            Wall wall = (Wall) o2;
            Tank tank = (Tank) o1;
            if (tank.getRectangle().intersects(wall.getRectangle())){
                tank.back();
            }
        }else if (o1 instanceof Wall && o2 instanceof Tank){
            return collider(o2,o1);
        }
        return true;
    }
}
