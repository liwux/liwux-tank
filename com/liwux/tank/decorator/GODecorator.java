package com.liwux.tank.decorator;

import com.liwux.tank.GameObject;

import java.awt.*;

public abstract class GODecorator extends GameObject {

    //聚合一个GameObject
    GameObject gameObject;

    public GODecorator(GameObject gameObject){
        this.gameObject = gameObject;
    }

    @Override
    public void paint(Graphics g) {
        gameObject.paint(g);
    }
}
