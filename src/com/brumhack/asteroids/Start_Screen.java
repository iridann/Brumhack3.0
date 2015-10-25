package com.brumhack.asteroids;

import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Soviet on 24/10/15.
 */
public class Start_Screen extends BasicGameState {
    public static Image backGround;
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        Start_Screen.backGround = new Image("res/starMap.001.png");
    }


    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        if(container.getInput().isKeyPressed(Input.KEY_SPACE)){
            game.enterState(1);
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        Start_Screen.backGround.drawCentered(400, 320);
        g.setColor(Color.white);
        g.drawString("To Start game,press SpaceBar",300,320);
        g.drawString("To pause during the game, press Esc", 275,340);
    }

}
