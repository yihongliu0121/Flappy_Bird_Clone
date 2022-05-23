package com.bird.main;
import java.util.ArrayList;
import java.util.List;

/**
 * Object pool for the barrier objects, to avoid create and destory objects too frequently
 * pre-defined some objects in the pool for the use of barriers
 */
public class Barrierpool {
    //ArrayList for holding the barrier object
    private static List<Barrier> pool = new ArrayList<>();
    //the initial number of objects can be hold in the pool
    public static final int initCount = 16;
    //the max number
    public static final int maxCOunt = 20;

    static {
        //initialization
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }

    /**
     * getting barrier from the pool
     * if there is more than one barrier, send to barrier for use
     * if not, make a new one then send to use
     */
    public static Barrier getBarrierFromPool(){
        int size = pool.size();
        //if there is at least one barrier object available
        if (size > 0) {
            //remove the object from the pool and send it out for usuage
            System.out.println("remove one");
            return pool.remove(size - 1);
        }else {
            //池中没有对象了 只能new
            System.out.println("new one created");
            return new Barrier();
        }
    }
    /**
     * return the object to the pool
     */
    public static void returnBarrierIntoPool(Barrier barrier){
        if (pool.size() < maxCOunt) {
            pool.add(barrier);
            System.out.println("one barrier gets back");
        }
    }

}