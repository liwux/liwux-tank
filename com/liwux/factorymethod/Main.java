package com.liwux.factorymethod;

public class Main {
    public static void main(String[] args) {
        Moveable moveable = new Plane();
        moveable.move();
    }
}
