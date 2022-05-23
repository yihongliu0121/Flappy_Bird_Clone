package com.bird.util;

import java.awt.*;

/**
 * @author
 * @create
 */
public class Constant {
    //window size
    public static final int FRAM_WIDTH= 600;
    public static final int FRAM_HEIGNT= 500;

    //windown title
    public static final String FRAM_TITLE= "Flappy Bird";

    //initialization positon
    public static final int FRAM_X = 200;
    public static final int FRAM_Y = 200;

    //background image
    public static final String BK_IMG_OATH = "img/bird_bk.png";

    //background color
    public static final Color BK_COLOR = new Color(0x4BC4CF);

    //bird image
    public static final String[] BIRD_IMG = {
            "img/bird_normal.png","img/bird_up.png","img/bird_down.png"
    };

    //barrier image
    public static final String[] BARRIER_IMG_PATH = {
            "img/barrier.png","img/barrier_up.png","img/barrier_down.png"
    };

}
