package com.bird.main;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Cloud Class
 */
public class Cloud {
    //image for cloud
    private BufferedImage img;
    //moving speed for cloud
    private  int speed;
    //position for the cloud object
    private  int x, y;

    public Cloud(){}

    public Cloud(BufferedImage img, int speed, int x, int y) {
        this.img = img;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        x -= speed;
        g.drawImage(img, x, y,null);
    }
    /**
     * check whether the cloud is out of frame
     */
    public boolean isOutFrame(){
        if (x < -100) {
            return true;
        }
        return false;
    }

}
