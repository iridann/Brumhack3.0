package com.brumhack.asteroids;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by Soviet on 24/10/15.
 */
public class Game_Screen extends BasicGameState {
    private Image ship = null;
    private Image facebookAsteroid = null;
    private Image googleAsteroid = null;
    private Image microsoftAsteroid = null;
    private Image appleAsteroid = null;
    private Shape Ship = null;
    private Shape poly = null;
    private int ms;
    private float accel;
    private float speed;
    private float shipx;
    private float shipy;
    private static int maxSpeed = 15;
    private float xspeed;
    private float yspeed;
    private float xrot;
    private float yrot;
    private float rotation;
    private Input input;
    @Override
    public int getID() {
        return 1;
    }

    float[] points = {0,25,10,15,20,25,10,0};

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        ship = new Image("res/ship(2).png");
        Ship = new Circle(300,300,40);
        facebookAsteroid = new Image("res/facebookAsteroid.001.png");
        poly = new Polygon(points);
        poly.closed();
        shipx = container.getWidth() / 2;
        shipy=container.getHeight() / 2;
        poly.setCenterY(shipy);
        poly.setCenterX(shipx);
        accel = 0;
        speed = 0;
        rotation = 0;
        xrot = (float)(ship.getWidth() * 0.5);
        yrot = (float)(ship.getHeight() * 0.5);


    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if (ms > 10) {
            movement();
            ms = 0;
        } else {
            ms += delta;
        }

        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            game.enterState(2);
        }


    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        Start_Screen.backGround.drawCentered(400, 320);
//        g.texture(Ship,ship,true);
//        g.draw(Ship);
        poly.setCenterY(shipy);
        poly.setCenterX(shipx);
        g.rotate(shipx,shipy,rotation);
        g.draw(poly);
        g.fill(poly);

    }

    public void movement() {
        input = new Input(100);
        if (input.isKeyDown(Input.KEY_W)) {
            accel++;
            if (speed < maxSpeed) {
                speed = speed + accel;
            } else {
                speed = maxSpeed;
            }
        } else if (input.isKeyDown(Input.KEY_S)) {
            accel = 0;
            speed = 0;
            xspeed = 0;
            yspeed = 0;
        }

        if (input.isKeyDown(Input.KEY_A)) {
            rotation = rotation - (float) (45.0 / 6.0);
            if (rotation < 0) {
                rotation = 360 - (0 - rotation);
            }
        } else if (input.isKeyDown(Input.KEY_D)) {
            rotation = rotation + (float)(45.0 / 6.0);
            if (rotation > 360) {
                rotation = 0 + (rotation - 360);
            }
        }
        if (speed > 0) {
            yspeed = (float) (Math.cos(Math.toRadians(rotation)) * speed);
            xspeed = (float) (Math.sin(Math.toRadians(rotation)) * speed);
        }
        if (shipx > 800) {
            shipx = 0 + Math.abs(shipx - (shipx + xspeed));
        } else if (shipx < 0) {
            shipx = 800 - Math.abs(shipx - (shipx + xspeed));
        } else {
            shipx = shipx + xspeed;
        }

        if (shipy > 600) {
            shipy = 0 + Math.abs(shipy - (shipy - yspeed));
        } else if (shipy < 0) {
            shipy = 600 - Math.abs(shipy - (shipy - yspeed));
        } else {
            shipy = shipy - yspeed;
        }
    }


}
