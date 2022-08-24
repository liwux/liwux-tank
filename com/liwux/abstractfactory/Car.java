package com.liwux.abstractfactory;

import com.liwux.factorymethod.Moveable;

public class Car implements Moveable {
    @Override
    public void move() {
        System.out.println("car move ........");
    }
}
