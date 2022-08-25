package com.liwux.tank60.tank.abstractfactory;

import com.liwux.tank60.tank.*;

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
