package com.mygdx.game.com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PacMan;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Entities.EntityManager;

/*
*
 * Created by AbZ on 06/06/2017.
 *
*/

public class G_LevelScreen implements Screen {

    private final PacMan game;
    private final EntityManager entityManager;
    private Hud hud;
    private Viewport viewport;


    public G_LevelScreen(final PacMan game) {
        this.game = game;
        entityManager = new EntityManager(1000, game, game.camera);
        viewport = new FitViewport(PacMan.G_WIDTH, PacMan.G_HEIGHT, new OrthographicCamera());
        hud = new Hud(game);
    }

    @Override
    public void render(float delta) {

        handleInput();
        update();
        draw();
    }

    public void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            System.exit(0);
        }
    }

    public void update() {

        game.camera.update();
        entityManager.update();
        hud.update(Gdx.graphics.getDeltaTime()); //updates the hud timer
    }

    public void draw() {

        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.setProjectionMatrix(game.camera.combined);

        game.batch.begin();
        entityManager.render();
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); //game batch renders only what the camera can see
        hud.stage.draw();
    }

    @Override
    public void dispose() {
        hud.dispose();
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
