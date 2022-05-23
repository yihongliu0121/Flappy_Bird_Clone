package com.bird.main;

import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Foreground set up
 */
public class GameForeGround {
    //number of clouds
    private static final int CLOUND_COUNT = 2;
    //list to hold cloud object
    private List<Cloud> clouds;
    //cloud moving speed right to lefe
    private static final int CLOUNG_SPEED = 5;
    //images set
    private BufferedImage[] img;

    private Random random;

    // initialization
    public GameForeGround() {
        clouds = new ArrayList<>();
        img = new BufferedImage[CLOUND_COUNT];

        //adding cloud image
        for (int i = 0; i < CLOUND_COUNT; i++) {
            img[i] = GameUtil.loadBufferedImage("img/cloud" + i + ".png");
        }
        random = new Random();
    }

    //draw the cloud
    public void draw(Graphics g) {
        logic();
        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).draw(g);
        }
    }

    /**
     * control the number of clouds on the screen
     */
    private void logic() {
        if ((int) (500 * Math.random()) < 5) {
            Cloud cloud = new Cloud(img[random.nextInt(CLOUND_COUNT)], CLOUNG_SPEED, 600, random.nextInt(150));
            clouds.add(cloud);
        }

        for (int i = 0; i < clouds.size(); i++) {
            Cloud cloud = clouds.get(i);
            if (cloud.isOutFrame()) {
                clouds.remove(i);
                i--;
                System.out.println("removed" + cloud);
            }
        }
    }
}
