package com.mygdx.game.com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PacMan;
import com.mygdx.game.Textures.TextureManager;
import com.mygdx.game.Entities.EntityManager;

/**
 * Created by AbZ on 20/06/2017.
 */

public class GameOverScreen implements  Screen {

    private final PacMan game;
    private Viewport viewport;
    private Stage stage;

    public GameOverScreen(final PacMan game) {

        this.game = game;
        viewport = new FitViewport(PacMan.G_WIDTH, PacMan.G_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((PacMan) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Table table = new Table();
        table.bottom();             //displays it at the bottom of the screen
        table.setFillParent(true); //table takes up the whole stage

        //GameOverScreen Label
        Label playAgainLabel = new Label("Click Anywhere To Restart The Game!", font);
        table.add(playAgainLabel).expandX().padBottom(10); //expands the entire length of the row

        stage.addActor(table);
    }

    public void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
                System.exit(0);
            }
        if (Gdx.input.isTouched()) {
            EntityManager.sleep(1000);
            game.setScreen(new G_LevelScreen(game));
            dispose();
        }
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        game.batch.begin();
        game.batch.draw(TextureManager.GAME_LOST, 0,0, PacMan.G_WIDTH, PacMan.G_HEIGHT);
        game.batch.end();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}


}
