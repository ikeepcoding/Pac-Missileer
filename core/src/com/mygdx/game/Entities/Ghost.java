
package com.mygdx.game.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.Textures.TextureManager;


/**
 * Created by AbZ on 08/06/2017.
 */


public class Ghost extends Entity implements Disposable {

    public Ghost(Vector2 pos, Vector2 direction) {
            super(TextureManager.GHOST, pos, direction);
    }

    @Override
    public void update() {
        pos.add(direction);

        }

    @Override
    public void dispose() {
        TextureManager.GHOST.dispose();
    }
}


