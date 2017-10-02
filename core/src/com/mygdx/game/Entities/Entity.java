package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by AbZ on 06/06/2017.
 */

public abstract class Entity implements Disposable {

    protected Texture texture;
    protected Vector2 pos, direction;


    public Entity( Texture texture,Vector2 pos, Vector2 direction) {
        this.texture = texture;
        this.pos = pos;
        this.direction = direction;
    }

    public void render(SpriteBatch sb) {
        sb.draw(texture, pos.x, pos.y);
    }

    public void setDirection(float x, float y) {
        direction.set(x,y);
        direction.scl(Gdx.graphics.getDeltaTime());
    }

    public Rectangle getBounds() {
        return  new Rectangle(pos.x, pos.y, texture.getWidth(), texture.getHeight());  //adds bounds around the entity
    }

    public abstract void update();

    public void dispose() {
        texture.dispose();
    }
}
