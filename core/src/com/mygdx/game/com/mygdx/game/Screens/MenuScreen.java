package com.mygdx.game.com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PacMan;
import com.mygdx.game.Entities.EntityManager;

/**
 * Created by AbZ on 20/06/2017.
 */

public class MenuScreen implements Screen {

    private final PacMan game;
    private Stage stage;
    private Viewport viewport;
    private Texture bg;
    private Label welcomeLabel;
    private Label redirectLabel;

    public MenuScreen(final PacMan game) {

        this.game = game;
        bg = new Texture("bg.jpg");
        viewport = new FitViewport(PacMan.G_WIDTH, PacMan.G_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch); // analogical to a box
        Table table = new Table();
        table.bottom();               //puts it at the bottom of the stage
        table.setFillParent(true); //makes it the size of the stage
        welcomeLabel = new Label("WELCOME TO PAC-MAN!!!", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        redirectLabel = new Label("Click Anywhere To Begin...", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        // Top Row
        table.add(welcomeLabel).expandX().padBottom(10);

        // Next Row
        table.row();
        table.add(redirectLabel).expandX().padBottom(120);

        stage.addActor(table);
    }

    public void handleInput() {

        if (Gdx.input.isTouched()) {
            EntityManager.sleep(1000);
            game.setScreen(new G_LevelScreen(game));
            dispose();
        } if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            System.exit(0);
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        game.camera.update();
        game.batch.setProjectionMatrix(game.camera.combined);
        game.batch.begin();
        game.batch.draw(bg, 0, 0, PacMan.G_WIDTH * 2, PacMan.G_HEIGHT);
        game.batch.end();
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        bg.dispose();
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
