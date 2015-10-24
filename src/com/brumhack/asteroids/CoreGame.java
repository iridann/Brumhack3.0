package com.brumhack.asteroids;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.newdawn.slick.*;


/**
 * @author Iridann.
 */
public class CoreGame extends BasicGame {
    int j;
    int ms;

    public CoreGame(String gamename) {
        super(gamename);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        j = 0;
        ms = 0;
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        if (ms > 1000) {
            j++;
            ms = 0;
        } else {
            ms += delta;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.drawString("Hello World!", 400, 300);
        Input input = new Input(100);
        if (input.isKeyDown(Input.KEY_SPACE)) {
            g.drawString("\n\nCount is:" + Integer.toString(j), 400, 300);
        }
    }

    public static void main(String[] args) {
        try {
            AppGameContainer gameContainer;
            gameContainer = new AppGameContainer(new CoreGame("Stockstroids"));
            gameContainer.setDisplayMode(800, 600, false);
            gameContainer.setTargetFrameRate(120);
            gameContainer.setAlwaysRender(true);
            gameContainer.setShowFPS(false);
            gameContainer.start();
        } catch (SlickException ex) {
            Logger.getLogger(CoreGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
