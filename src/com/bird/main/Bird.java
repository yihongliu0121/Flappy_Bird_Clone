package com.bird.main;

import static com.bird.util.Constant.*;
import com.bird.util.GameUtil;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Bird Class
 */
public class Bird {
    //rectangle object to hold the image of the bird
    private Rectangle rect;

    //life status of the bird
    public boolean life = true;

    //images for bird
    private BufferedImage[] images;
    public static final int BIRD_IMG_COUNT = 3;
    //flying state of the bird
    private int state;
    public static final int STATE_NORMARL = 0; //normal state
    public static final int STATE_UP = 1; //flying up
    public static final int STATE_DOWN = 2; //flying down

    //position for the bird obejct
    private int x = 200, y = 200;


    //direction for up and down
    private boolean up = false, down = false;

    //speed for going up
    private int speed = 8;

    //speed for going down, free fall
    private int acceleration = 10;



    //initialization for the bird object, images, width and height for the bird
    public Bird() {
        images = new BufferedImage[BIRD_IMG_COUNT];
        for (int i = 0; i < BIRD_IMG_COUNT; i++) {
            images[i] = GameUtil.loadBufferedImage(BIRD_IMG[i]);
        }

        int w = images[0].getWidth();
        int h = images[0].getHeight();
        rect = new Rectangle(w,h);

    }

    //drawing the bird object into the GUI
    public void draw(Graphics g) {

        flyLogic();

        g.drawImage(images[state], x, y, null);
        //g.drawRect(x,y,(int)rect.getWidth(), rect.height);
        rect.x=this.x;
        rect.y=this.y;
    }



    //speed for flying up and down
    public void flyLogic(){
        if (up){
            y-=speed;
            if (y<20){
                y=20;
            }
        }
        if (!up){
            y+= acceleration;
            if (y>475){
                y=475;
                this.life = false;
            }
        }

    }

    //setting for keyboard
    public void fly(int fly){
        switch (fly){
            case 1:
                state=1;
                up=true;
                break;
            case 5:
                state=2;
                up=false;
                break;
        }
    }

    public Rectangle getRect() {
        return rect;
    }

/**
 * restart to draw the position
 */
    public void  restartDraw() {
        life=true;
        x=200;
        y=200;
    }

}
