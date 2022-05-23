package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Barrier Class
 */
public class Barrier {
    //rectangle gui object to hold a barrier object
    private Rectangle rect;

    private boolean mob = true;

    //moving speed for barrier, right to left
    private int speed = 3;
    //array for storing static images of barriers
    private static BufferedImage[] imgs;

    //status of visibility
    private boolean visible;

    static {
        final int COUNT = 3;
        //initialization
        imgs = new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            imgs[i] = GameUtil.loadBufferedImage(Constant.BARRIER_IMG_PATH[i]);
        }
    }

    //position of the barrier
    private int x, y;
    //width and height, width is identified
    private int width, height;
    //different type of Barriers
    private int type;
    public static final int TYPE_TOP_NORMAL = 0; //top to down barrier
    public static final int TYPE_BOTTOM_NORMAL = 2; //bottom to top barrier
    public static final int TYPE_HOVER_NORMAL = 4; //floating barrier
    public static final int TYPE_MOBILE = 6; //dynamic moving floating barrier

    //get the width and height from the default image resource
    public static final int BARRIRER_WIDTH = imgs[0].getWidth(); //img[0] is the image of the body of barrier
    public static final int BARRIRER_HEIGHT = imgs[0].getHeight();
    public static final int BARRIRER_HEAD_WIDTH = imgs[1].getWidth(); //img[1] is the image of the head(up) of barrier
    public static final int BARRIRER_HEAD_HEIGHT = imgs[1].getHeight();

    public Barrier() {

        rect = new Rectangle();

    }

    public Barrier(int x, int y, int height, int type) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.type = type;
        this.width = BARRIRER_WIDTH;
    }

    //draw barriers with corresponding type
    public void draw(Graphics g) {
        switch (type) {
            case TYPE_TOP_NORMAL:
                drawTopNormal(g);
                break;
            case TYPE_BOTTOM_NORMAL:
                drawBottomNomal(g);
                break;
            case TYPE_HOVER_NORMAL:
                drawHoverNormal(g);
                break;
            case TYPE_MOBILE:
                drawMobile(g);
                break;
        }

    }

    //drawing method for top-down barriers
    private void drawTopNormal(Graphics g) {
        //the number of images needed for the body part
        int count = (height - BARRIRER_HEAD_HEIGHT) / BARRIRER_HEIGHT + 1;
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y + i * BARRIRER_HEIGHT, null);
        }

        //adding the head(down) to the body
        int y = height - BARRIRER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x - (BARRIRER_HEAD_WIDTH - BARRIRER_WIDTH) / 2, y, null);
        x -= speed;
        if (x < -100) {
            visible = false;
        }
        rect(g);
    }

    //drawing method for bottom-up barriers
    private void drawBottomNomal(Graphics g) {
        //the number of images needed for the body part
        int count = height / BARRIRER_HEIGHT + 1;
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, Constant.FRAM_HEIGNT - i * BARRIRER_HEIGHT, null);
        }
        //adding the head(up) to the body
        int y = Constant.FRAM_HEIGNT - height;
        g.drawImage(imgs[1], x - (BARRIRER_HEAD_WIDTH - BARRIRER_WIDTH) / 2, y, null);
        x -= speed;
        if (x < -100) {
            visible = false;
        }
        rect(g);
    }
    //drawing method for floating barriers
    private void drawHoverNormal(Graphics g) {
        //the number of images needed for the body part
        int count = (height - BARRIRER_HEAD_HEIGHT) / BARRIRER_HEIGHT;
        //adding the head(up) to the body
        g.drawImage(imgs[1], x, y,null);
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y+ BARRIRER_HEAD_HEIGHT +i* BARRIRER_HEIGHT, null);
        }

        //adding the head(down) to the body
        int y_bottom = y + height- BARRIRER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x, y_bottom, null);
        x -= speed;
        if (x < -100) {
            visible = false;
        }
        rect(g);
    }

    //drawing method for dynamic moving floating barriers
    private void drawMobile(Graphics g) {
        //the number of images needed for the body part
        int count = (height- BARRIRER_HEAD_HEIGHT)/ BARRIRER_HEIGHT;
        //adding the head(up) to the body
        g.drawImage(imgs[1],x,y,null);
        //for loop for the body part
        for (int i = 0; i < count; i++) {
            g.drawImage(imgs[0], x, y+ BARRIRER_HEAD_HEIGHT +i* BARRIRER_HEIGHT, null);
        }

        //adding the head(down) to the body
        int y_bottom = y+height- BARRIRER_HEAD_HEIGHT;
        g.drawImage(imgs[2], x, y_bottom,null);
        x -= speed;
        if (x < -100) {
            visible = false;
        }

        rect(g);

        if (mob) {
            y += 2;
            if (y >= 250) {
                mob=false;
            }
        }else if (!mob){
            y -= 2;
            if (y <= 100) {
                mob=true;
            }
        }
    }

    /**
     * making a rectangle object for holding the image object
     *
     * @return
     */
    public void rect(Graphics g) {
        int x1 = this.x;
        int y1 = this.y;
        int w1 = imgs[0].getWidth();
//        g.setColor(Color.blue);
//        g.drawRect(x1, y1, w1, height);
        setRectangle(x1, y1, w1, height);
    }

    /**
     *
     *
     *
     * @return
     */
    public void setRectangle(int x, int y, int width, int height) {
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
    }


    //the distance between each set of barrier object(s)
    public boolean isInFrame() {
        return 600 - x > 200;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Rectangle getRect() {
        return rect;
    }
}
