package com.liwux.tank.decorator;

import com.liwux.tank.GameObject;

import java.awt.*;

public class RectDecorator extends GODecorator{
    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(super.gameObject.x,super.gameObject.y,super.gameObject.getWidth(),super.gameObject.getHeight());
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return super.gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return super.gameObject.getHeight();
    }
}
