package com.liwux.tank.chain;

import com.liwux.tank.Bullet;
import com.liwux.tank.GameObject;
import com.liwux.tank.Tank;

public class BulletTankCollider implements Collider {
    @Override
    public void collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet&& o2 instanceof Tank){
            ((Bullet) o1).collideWith((Tank) o2);
        } else if (o1 instanceof Tank && o2 instanceof Bullet){
            collider(o2,o1);
        }
        else return;

    }
}
