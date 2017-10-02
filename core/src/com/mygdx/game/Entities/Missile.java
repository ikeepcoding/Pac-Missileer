package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.PacMan;
import com.mygdx.game.Textures.TextureManager;

/**
 * Created by AbZ on 17/06/2017.
 */

public class Missile extends Entity implements Disposable {

    public Missile(Vector2 pos) {
        super(TextureManager.MISSILE, pos, new Vector2(0 , 5));
    }

    @Override
    public void update() {
        pos.add(direction);
    }

    public  boolean reachedEnd() {
        return pos.y >= PacMan.G_HEIGHT;
    }

    public void dispose() {
        TextureManager.MISSILE.dispose();
    }
}
