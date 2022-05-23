package com.bird.main;

import static com.bird.util.Constant.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * Game Frame Class
 */
public class GameFrame extends Frame {
    //game background object
    private GameBackGround gameBackGround;

    //Bird object
    private Bird bird;

    //GameBarrierLayer object
    private GameBarrierLayer gameBarrierLayer;

    //GameForeGround object
    private GameForeGround gameForeGround;


    //buffer image
    private BufferedImage buffimg = new BufferedImage(FRAM_WIDTH,FRAM_HEIGNT,BufferedImage.TYPE_4BYTE_ABGR);


    //initialization for the game frame
    public GameFrame(){
        //is the gui visible
        setVisible(true);
        //size
        setSize(FRAM_WIDTH, FRAM_HEIGNT);
        //tittle
        setTitle(FRAM_TITLE);
        //position for the gui
        setLocation(FRAM_X,FRAM_Y);
        //the size of the gui is unchangeable
        setResizable(false);


        //closing event for the window
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//exit
            }
        });

        //start the game
        initGame();

        new run().start();

        //event listener
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }
        });



    }

    //instance of the objects to start to game
    public void initGame() {
        gameBackGround = new GameBackGround();
        bird = new Bird();
        gameForeGround = new GameForeGround();
        gameBarrierLayer = new GameBarrierLayer();
    }

    //thread to run the program
    class run extends Thread{
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * drawing
     */
    @Override
    public void update(Graphics g) {
        if(bird.life){
            Graphics graphics = buffimg.getGraphics();
            gameBackGround.draw(graphics);
            bird.draw(graphics);
            gameForeGround.draw(graphics);
            gameBarrierLayer.draw(graphics, bird);
            g.drawImage(buffimg,0,0,null);
        }else {
            String over = "Game Over";
            g.setColor(Color.red);
            g.setFont(new Font("Courier",Font.PLAIN,40));
            g.drawString(over,200,250);

            String reset = "Space to Reset Game";
            g.drawString(reset,70,350);
        }
    }


    //button listener
    public void add(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(1);
                break;
            case KeyEvent.VK_SPACE:
                if (bird.life == false) {
                    restart();
                }
                break;
        }
    }

    public void minu(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                bird.fly(5);
                break;
        }
    }

    /**
     * restart the game
     */
    public void restart(){
        gameBarrierLayer.restart();
        bird.restartDraw();
    }


}
