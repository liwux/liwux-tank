package com.liwux.tank.chain;

import com.liwux.tank.Bullet;
import com.liwux.tank.GameObject;
import com.liwux.tank.Wall;

public class BulletWallCollider implements Collider{
    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet) o1;
            Wall w = (Wall) o2;
            if (b.rectangle.intersects(w.getRectangle())){
                b.die();
            }
        }else if (o1 instanceof Wall && o2 instanceof Bullet){
            return collider(o2,o1);

        }
        return true;
    }
}
