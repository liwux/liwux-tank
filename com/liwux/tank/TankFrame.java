package com.liwux.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    public static final int GAME_WIDTH = 800,GAME_HEIGHT=600;

    Tank myTank = new Tank(200,400,Dir.DOWN,Group.GOOD,this);

    List<Bullet> bulletList = new ArrayList<>();

    List<Tank> tankList = new ArrayList<>();

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
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹的数量："+ bulletList.size(),10,50);
        g.drawString("敌人的数量："+ tankList.size(),10,80);

        g.setColor(c);

        myTank.paint(g);
        for (int i=0;i<bulletList.size();i++){
            bulletList.get(i).paint(g);
        }

        for (int i=0;i<tankList.size();i++){
            tankList.get(i).paint(g);

        }

        for (int i=0;i<bulletList.size();i++){
            for (int j=0;j<tankList.size();j++){
                bulletList.get(i).collideWith(tankList.get(j));
            }
        }

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
