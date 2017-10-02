package com.mygdx.game.Textures;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * Created by AbZ on 06/06/2017.
 */

public class TextureManager implements Disposable {

    public static Texture PLAYER = new Texture(Gdx.files.internal("pacman.png"));
    public static Texture MISSILE = new Texture(Gdx.files.internal("missile.png"));
    public static Texture GHOST  = new Texture(Gdx.files.internal("blinky.png"));
    public static Texture GAME_LOST  = new Texture(Gdx.files.internal("gameOver.jpg"));
    public static Texture GAME_WON  = new Texture(Gdx.files.internal("gameWon.png"));

    @Override
    public void dispose() {
        PLAYER.dispose();
        MISSILE.dispose();
        GHOST.dispose();
        GAME_LOST.dispose();
        GAME_WON.dispose();
    }
}
