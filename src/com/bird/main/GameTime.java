package com.bird.main;

/**
 * stopwatch for the game
 */
public class GameTime {
    private long beginTime;

    private long endTime;

    private long differ;

    public GameTime(){}

    public void  begin(){
        beginTime = System.currentTimeMillis();
    }

    public long differ(){
        endTime = System.currentTimeMillis();
        return differ = (endTime - beginTime) / 1000;
    }

}
