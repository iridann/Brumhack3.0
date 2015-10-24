package com.brumhack.asteroids;


import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;


/**
 * @author Iridann.
 */

public class SimpleGame extends BasicGame {
    private Image ship = null;
    private Image asteroid1 = null;
    private Image asteroid2 = null;
    private Image asteroid3 = null;
    private Image asteroid4 = null;
    private Shape circle = null;

    public SimpleGame(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
    ship = new Image("res/photos.007.png");
        asteroid1 = new Image("res/photos.003.png");
        asteroid2 = new Image("res/photos.004.png");
        asteroid3 = new Image("res/photos.005.png");
        asteroid4 = new Image("res/photos.006.png");
        circle = new Circle(50,50,300);
    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    g.texture(circle,ship,true);
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
    circle.setCenterX(container.getInput().getMouseX());
        circle.setCenterY(container.getInput().getMouseY());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer agc = new AppGameContainer(new SimpleGame("Images"));
        agc.setDisplayMode(800,640,false);
        agc.start();
    }


}
