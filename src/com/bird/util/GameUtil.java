package com.bird.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * utility class for the game
 */
public class GameUtil {
    /**
     receive the image path to read to image
     */
    public static BufferedImage loadBufferedImage(String imgPath){
        try {
            return ImageIO.read(new FileInputStream(imgPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
