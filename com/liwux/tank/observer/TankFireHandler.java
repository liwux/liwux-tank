package com.liwux.tank.observer;

import com.liwux.tank.Tank;

public class TankFireHandler implements TankFireObserver{
    @Override
    public void actionOnFire(TankFireEvent e) {
        Tank tank = e.getSource();
        tank.fire();
    }
}
