package com.mygdx.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PacMan;

/**
 * Created by AbZ on 19/06/2017.
 */

public class Hud implements Disposable {


    public Stage stage;
    private final  PacMan game;
    private Viewport viewport; //ensure the hud stays fixed and rendered seperate from game world
    private static Integer worldTimer;
    private static Integer score;
    private float timeCount;

    //Widgets
    private static Label scoreLabel;
    private Label countDownLabel;
    private Label timeLabel;
    private Label gPlayLabel;
    private Label worldLabel;
    private Label pacmanLabel;


    public Hud (final  PacMan game) {
        this.game = game;
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(PacMan.G_WIDTH, PacMan.G_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, game.batch); // analogical to a box

        Table table = new Table();
        table.top();               //puts it at the top of the stage
        table.setFillParent(true); //makes it the size of the stage

        //headingLabels
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("PAC-MAN", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        pacmanLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //ouputLabels
        countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        gPlayLabel = new Label("GAMEPLAY", new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        // Top Row
        table.add(pacmanLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);

        // Next Row
        table.row();
        table.add(scoreLabel).expandX();
        table.add(gPlayLabel).expandX();
        table.add(countDownLabel).expandX();

        stage.addActor(table); //adds table to the stage
    }
    public void update (float dt) {
        timeCount += dt;
        if (timeCount >= 1) { //1 second
            worldTimer--;
            countDownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value) {
        score += value; //increment score value
        scoreLabel.setText(String.format("%06d", score)); //updates the label
    }

    public static Integer getWorldTimer() {
        return worldTimer;
    }

    @Override
        public void dispose() {
            stage.dispose();
        }
    }


