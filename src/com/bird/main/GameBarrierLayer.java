package com.bird.main;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * barrier layer class
 */
public class GameBarrierLayer {
    private GameTime gameTime;
    private Random random = new Random();
    private List<Barrier> barriers;

    public GameBarrierLayer() {
        barriers = new ArrayList<>();
        gameTime = new GameTime();
    }

    //draw the barriers and check whether the barriers collide with the position of the bird
    public void draw(Graphics g, Bird bird) {
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);

            if (barrier.isVisible()) {
                barrier.draw(g);
            } else {
                Barrier remove = barriers.remove(i);
                Barrierpool.returnBarrierIntoPool(remove);
                i--;
            }
        }
        collideBird(bird);
        logic(g);
    }

    public void logic(Graphics g) {
        if (barriers.size() == 0) {
            ran();
            gameTime.begin();
            insert(600, 0, numberTop, 0);
            insert(600, 500 - numberDown, numberDown, 2);
        } else {
            long differ = gameTime.differ();
            g.setColor(Color.white);
            g.setFont(new Font("Courier", Font.PLAIN, 20));
            g.drawString("Stopwatch: " + differ + "sec", 30, 50);

            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) {
                ran();
                if (number < 50){
                    insert(600,100,300,4);
                }else if (number>450){
                   insert(600,125,200,6);
                }else {
                    insert(600, 0, numberTop, 0);
                    insert(600, 500 - numberDown, numberDown, 2);
                }

            }
        }
    }

    /**
     * get the barrier from the pool and set the position for the barrier
     */
    public void insert(int x, int y, int num, int type) {
        Barrier top = Barrierpool.getBarrierFromPool();
        top.setX(x);
        top.setY(y);
        top.setHeight(num);
        top.setType(type);
        top.setVisible(true);
        barriers.add(top);
    }

    //height for the top-down barrier
    private int numberTop;
    //height for the bottom barrier
    private int numberDown;
    private int number;

    //random number between 100 - 500
    public void ran() {
        numberTop = random.nextInt(400) + 100;
        numberDown = random.nextInt(400) + 100;
        number = random.nextInt(500);
        //if gap between the two barriers are too small, re-random two numbers
        if (numberTop + numberDown > 410) {
            ran();
        }
    }

    /**
     * check weather the barriers collide with the bird
     */
    public boolean collideBird(Bird bird) {
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            //check if the two rectangle objects interact
            if (barrier.getRect().intersects(bird.getRect())) {
                bird.life = false;
            }
        }
        return false;
    }

    /**
     * restart to clear the pool
     */
    public void restart() {
        barriers.clear();
    }
}
