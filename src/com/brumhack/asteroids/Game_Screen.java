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
    int lastSpawn;
    int firstYear;
    int firstMonth;
    int lastAstMove = 300;
    ArrayList<Company> companies = new ArrayList<>();
    AstroidAbstract aAbstract = new AstroidAbstract();
    // Counter variable declaration
    int ms;
    int bulletCd;
    // Graphics declaration
    private Image facebookAsteroid = null;
    private Image googleAsteroid = null;
    private Image microsoftAsteroid = null;
    private Image appleAsteroid = null;
    private Shape ship = null;
    // Ship coordinates
    private float shipx;
    private float shipy;
    // Ship movement variables
    private float accel;
    private float speed;
    private static float maxSpeed = 10;
    private float xspeed;
    private float yspeed;
    private float xrot;
    private float yrot;
    private float rotation;
    // Bullet variables
    private static float bulletSpeed = (float)(maxSpeed * 1.4);
    // Object array declarations
    private ArrayList<Bullet> bullets;
    // Input declaration
    private Input input;
    @Override
    public int getID() {
        return 1;
    }

    float[] points = {0,25,10,15,20,25,10,0};

    private int moveAstroid() {
        aAbstract.moveAsteroids();
        return 300;
    }

    private int spawn(){
        int x = 0;
        int y = 0;
        int moveX = 1;
        int moveY = 3;
        for(Company c : companies){
            Astroid a;
            if(c.hasDate(firstYear, firstMonth)) {
                a = new Astroid(x, y, c.getValue(firstYear, firstMonth));
                a.setMovingDirection(moveX, moveY);
                aAbstract.register(a);
            }
            firstMonth++;
            if(firstMonth > 12){
                firstMonth = 1;
                firstYear++;
            }
            x += 50;
            y += 50;
            moveX += 0;
            moveY += 1;
        }
        return 5000;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        int[] arr = Parser.readCompanies(companies);
        firstMonth = arr[0];
        firstYear = arr[1];


        //for(Company c : companies){
        //System.out.println(c.getName());
        //}

        //if(companies.get(1).hasDate(2012, 03)) {
        //    System.out.println(companies.get(2).getName());
        //    System.out.println(companies.get(2).getValue(2012, 02));
        //}


        // Initialises counter variables at 0
        ms = 0;
        bulletCd = 0;
        // Load in graphics
        facebookAsteroid = new Image("res/facebookAsteroid.001.png");
        ship = new Polygon(points);
        ship.closed();
        // Init ship coords
        shipx = container.getWidth() / 2;
        shipy = container.getHeight() / 2;
        ship.setCenterY(shipy);
        ship.setCenterX(shipx);
        // Init accel, speed & rotation
        accel = 0;
        speed = 0;
        rotation = 0;
        // Init object arrays
        bullets = new ArrayList<Bullet>();


    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        lastSpawn -= delta;
        if(lastSpawn <= 0) lastSpawn = spawn();
        lastAstMove -= delta;
        if(lastAstMove <= 0) lastAstMove = moveAstroid();

        bulletCd -= delta;
        if (ms > 10) {
            movement();
            if (input.isKeyDown(Input.KEY_SPACE)) {
                shoot(delta);
            }
            for (Bullet b : bullets) {
                b.update(delta);
            }
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
        Start_Screen.backGround.drawCentered(400, 300);
        for (Bullet b : bullets) {
            g.draw(new Circle(b.getX(),b.getY(), 5));
        }
        ArrayList<Astroid> arr = aAbstract.getAstroidPositions();
        for (Astroid a : arr) {
            g.draw(new Circle(200 , 200,a.getDimension()));
        }
        ship.setCenterY(shipy);
        ship.setCenterX(shipx);
        g.drawString("Angle: " + Float.toString(rotation) + "\nSpeed: " + Float.toString(speed) + "\nAccel: " + Float.toString(accel) + "\nx speed: " + xspeed + "\ny speed: " + yspeed, 10, 10);
        g.rotate(shipx, shipy, rotation);
        g.draw(ship);
        g.fill(ship);

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

    public void shoot(int delta) {
        if (bulletCd <= 0) {
            bullets.add(new Bullet(shipx, shipy, bulletSpeed, rotation));
            bulletCd = 250;
        }
    }

}
