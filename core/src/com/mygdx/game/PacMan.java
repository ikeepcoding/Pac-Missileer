package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.com.mygdx.game.Screens.MenuScreen;

public class PacMan extends Game {



	public static final int G_WIDTH = 600, G_HEIGHT = 600;
	public static final String TITLE = "Pac-Man";
	public  SpriteBatch batch;
	public OrthographicCamera camera;
	public BitmapFont font;


	@Override
	public void create() {

		batch = new SpriteBatch();
		camera = new OrthographicCamera(); //initialises new orthographic gamera
		camera.setToOrtho(false, 800, 480); //sets the properties
		font = new BitmapFont();
		this.setScreen(new MenuScreen(this));
	}

	@Override
	public void render() {
		super.render();   //delegates the render to the rest of the screens

	}

	@Override
	public void dispose() {
		font.dispose();
		batch.dispose();
		font.dispose();
	}
}
