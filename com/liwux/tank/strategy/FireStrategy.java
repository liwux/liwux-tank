package com.liwux.tank.strategy;

import com.liwux.tank.Tank;

import java.io.Serializable;

public interface FireStrategy extends Serializable {
    void fire(Tank tank);
}
