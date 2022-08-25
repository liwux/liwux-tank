package com.liwux.tank60.tank.abstractfactory;

import com.liwux.tank60.tank.Dir;
import com.liwux.tank60.tank.Group;
import com.liwux.tank60.tank.TankFrame;

public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
    public abstract BaseBullet createBullet(int x, int y, TankFrame tankFrame);

}
