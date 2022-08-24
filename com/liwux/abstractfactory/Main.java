package com.liwux.abstractfactory;

import com.liwux.factorymethod.Moveable;
import com.liwux.factorymethod.Plane;

public class Main {
    public static void main(String[] args) {
        Moveable moveable = new Plane();
        moveable.move();
    }
}
