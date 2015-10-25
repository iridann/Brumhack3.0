package com.brumhack.asteroids;

/**
 * Created by janospotecki on 25/10/15.
 */
public class Astroid implements AObserver {
    private float x,y;
    private float dimension;
    private float movingX, movingY;

    public Astroid(float x, float y, float dimension) {
        this.x = x;
        this.y = y;
        this.dimension = dimension;
    }

    public void setMovingDirection(float x, float y){
        this.movingX = x;
        this.movingY = y;
    }

    public float getDimension(){
        return this.dimension;
    }

    public float getX(){
        return this.x;
    }

    public float getY(){
        return this.y;
    }

    @Override
    public void move() {
        this.x += this.movingX;
        this.y += this.movingY;
        System.out.println("Android moving");
    }

    @Override
    public Astroid getData() {
       return this;
    }
}
