package com.bird.main;

import static com.bird.util.Constant.*;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * game background class
 */
public class GameBackGround {
    //background image
    private BufferedImage bkimg;

    //initialization
    public GameBackGround() {
        bkimg = GameUtil.loadBufferedImage(Constant.BK_IMG_OATH);
    }

    //draw the game background
    public void draw(Graphics g){
        //set color for the background
        g.setColor(BK_COLOR);
        g.fillRect(0, 0, FRAM_WIDTH, FRAM_HEIGNT);
        g.setColor(Color.black);

        //get the height and width of the background image
        int height = bkimg.getHeight();
        int weight = bkimg.getWidth();
        //for loop to fill out the gui box
        int count = Constant.FRAM_WIDTH / weight + 1;
        for (int i = 0; i < count; i++) {
            g.drawImage(bkimg, weight * i, Constant.FRAM_HEIGNT - height, null);
        }
    }
}
