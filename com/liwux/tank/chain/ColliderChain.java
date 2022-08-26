package com.liwux.tank.chain;

import com.liwux.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{
    private List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
        add(new TankTankCollider());
        add(new BulletTankCollider());
    }

    public void add(Collider c){
        colliders.add(c);

    }

    @Override
    public boolean collider(GameObject o1, GameObject o2) {
        for (int i=0;i<colliders.size();i++){
            if (!colliders.get(i).collider(o1,o2)){
                return false;
            }
        }
        return true;
    }
}
