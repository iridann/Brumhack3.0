package com.brumhack.asteroids;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by janospotecki on 25/10/15.
 */
public class AstroidAbstract {

    private final List<AObserver> aAstroidList = new ArrayList<>();

    public void register(AObserver newAstroid){
        this.aAstroidList.add(newAstroid);
    }

    public void unregister(AObserver removeAstroid){
        this.aAstroidList.remove(removeAstroid);
    }

    protected void moveAsteroids(){
        for(AObserver a : aAstroidList) a.move();
    }
}
