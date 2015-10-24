package com.brumhack.asteroids;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Soviet on 24/10/15.
 */
public class Game_Screen extends BasicGameState {
    private Image ship = null;
    private Image facebookAsteroid = null;
    private Image googleAsteroid = null;
    private Image microsoftAsteroid = null;
    private Image appleAsteroid = null;
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        ship = new Image("res/ship(2).png");
        facebookAsteroid = new Image("res/facebookAsteroid.001.png")
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)){
            game.enterState(2);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        Start_Screen.backGround.drawCentered(400, 320);
    }


}
