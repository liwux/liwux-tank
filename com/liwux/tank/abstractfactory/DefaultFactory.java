package com.liwux.tank.abstractfactory;

import com.liwux.tank.Dir;
import com.liwux.tank.Group;
import com.liwux.tank.TankFrame;

public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return null;
    }

    @Override
    public BaseBullet createBullet(int x, int y, TankFrame tankFrame) {
        return null;
    }
}
