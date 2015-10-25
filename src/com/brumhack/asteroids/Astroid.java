package com.brumhack.asteroids;

/**
 * Created by janospotecki on 25/10/15.
 */
public class Astroid implements AObserver {
    private int x,y;
    private int dimension;
    private int movingX, movingY;

    public Astroid(int x, int y, int dimension) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
    }

    public void setMovingDirection(int x, int y){
        this.movingX = x;
        this.movingY = y;
    }

    public int getDimension(){
        return this.dimension;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public void move() {
        this.x += this.movingX;
        this.y += this.movingX;
        System.out.println("Android moving");
    }

    @Override
    public Astroid getData() {
       return this;
    }
}
