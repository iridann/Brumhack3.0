package com.brumhack.asteroids;


import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;


/**
 * @author Iridann.
 */

public class SimpleGame extends StateBasedGame {


    public SimpleGame(String name) {
        super(name);
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new Start_Screen());
        this.addState(new Game_Screen());
        this.addState(new Pause_Screen());
        this.addState(new Game_Over());
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer agc = new AppGameContainer(new SimpleGame("StackStroid"));
        agc.setDisplayMode(800,640,false);
        agc.start();
    }
}
