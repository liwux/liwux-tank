package com.liwux.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {

    private static final int GAME_WIDTH = 800,GAME_HEIGHT=600;

    Tank myTank = new Tank(200,200,Dir.DOWN,this);
    Bullet bullet = new Bullet(300,300,Dir.DOWN);

    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);

        addKeyListener(new MykeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if(offScreenImage ==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {

        myTank.paint(g);
        bullet.paint(g);

    }

    class MykeyListener extends KeyAdapter{
        boolean lU = false;
        boolean rU = false;
        boolean uU = false;
        boolean dU = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    lU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    rU = true;
                    break;
                case KeyEvent.VK_UP:
                    uU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    dU = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
            //System.out.println("key press");
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch(key){
                case KeyEvent.VK_LEFT:
                    lU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    rU = false;
                    break;
                case KeyEvent.VK_UP:
                    uU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    dU = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
            //System.out.println("key released");
        }

        private void setMainTankDir(){
            if (!lU&&!uU&&!rU&&!dU) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (lU) myTank.setDir(Dir.LEFT);
                if (uU) myTank.setDir(Dir.UP);
                if (rU) myTank.setDir(Dir.RIGHT);
                if (dU) myTank.setDir(Dir.DOWN);
                if (lU && uU) myTank.setDir(Dir.LEFT_UP);
                if (rU && uU) myTank.setDir(Dir.RIGHT_UP);
                if (lU && dU) myTank.setDir(Dir.LEFT_DOWN);
                if (rU && dU) myTank.setDir(Dir.RIGHT_DOWN);
            }
        }
    }
}
