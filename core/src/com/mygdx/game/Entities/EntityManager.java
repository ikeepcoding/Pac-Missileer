
package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.PacMan;
import com.mygdx.game.Scenes.Hud;
import com.mygdx.game.Textures.TextureManager;
import com.mygdx.game.com.mygdx.game.Screens.GameOverScreen;
import com.mygdx.game.com.mygdx.game.Screens.GameWonScreen;


/**
 * Created by AbZ on 08/06/2017.
 */

public class EntityManager implements Disposable {

    private final Array<Entity> entities = new Array<Entity>();
    private final Player player;
    private final PacMan game;

    public EntityManager(int amount,  final PacMan game, OrthographicCamera camera) {

        this.game = game;
        player = new Player(new Vector2(230, 15), new Vector2(0, 0), this, camera);           // a new player is added
        for (int i = 0; i < amount; i++) {                                                    //amount= quantity of ghosts
            float x = MathUtils.random(0, PacMan.G_WIDTH - TextureManager.GHOST.getWidth());
            float y = MathUtils.random(PacMan.G_HEIGHT, PacMan.G_HEIGHT * 100);
            float speed = MathUtils.random(1, 2);                                             //each ghost shall come at random speed between the values specified
            addEntity(new Ghost(new Vector2(x, y), new Vector2(0, -speed)));                  //new Ghost entity added
        }
    }

    public void update() {

        for (com.mygdx.game.Entities.Entity entity : entities) {                 //loops through each entity in entities
            entity.update();                             //updates the entity
        }

        for (com.mygdx.game.Entities.Missile missile : getMissiles()) {          //loops through each missile in missiles
            if (missile.reachedEnd())                    //if the missile reaches greater than player height...
                entities.removeValue(missile, false);    //its value is remove from entities
        }
        player.update();                                 //player updated
        entityCollision();                               //entityCollision method updated
        gameOver();
        gameWon();
        pauseAndResume();
    }

    public void render() {

        for (com.mygdx.game.Entities.Entity entity : entities) {                //loops through each entity in entities
            entity.render(game.batch);                  //renders each entity
        }   player.render(game.batch);                  //player is rendered(sprite batched passed)
    }


    public void addEntity(Entity entity) {    //addEntity method

        entities.add(entity);                 //an entity is added to entities

    }

    private Array<Ghost> getGhosts() {                              //an array of the Ghost object- getGhosts method
        Array<Ghost> returnGhosts = new Array<Ghost>();                //array returnGhosts is initialised

        for (Entity entity : entities)                             //loops through each entity in entities
            if (entity instanceof Ghost)                           //if there is an entity instance of Ghost class...
                returnGhosts.add((Ghost) entity);                  //then it adds that to the array

        return returnGhosts;                                      //returns the array
    }

    private Array<Missile> getMissiles() {                          //an array of the Missile object- getGhosts method
        Array<Missile> returnMissiles = new Array<com.mygdx.game.Entities.Missile>();             //array returnMissiles is initialised

        for (Entity entity : entities)                             //loops through each entity in entities
            if (entity instanceof Missile)                         //if there is an entity instance of Ghost class...
                returnMissiles.add((Missile) entity);              //then it adds that to the array

        return returnMissiles;                                     //returns the array
    }


    public void entityCollision() {
        for (Missile missile : getMissiles()) {                               //loops through getMissiles() method array
            for (Ghost ghost : getGhosts()) {                                 //loops through getGhosts() array
                if (ghost.getBounds().overlaps(missile.getBounds())) {        //if ghost intersects with the missile
                    entities.removeValue(ghost, false);                       //the ghost is removed from the entities array
                    entities.removeValue(missile, false);                     //the missile is removed from the entities array
                    Hud.addScore(200);

                }
            }
        }
    }

    public void gameOver () {

        for (Ghost ghost : getGhosts()) {
            if (player.getBounds().overlaps(ghost.getBounds())) {
                sleep(200);
                game.setScreen(new GameOverScreen(game));

            }
        }
    }

    public void gameWon () {

        if (Hud.getWorldTimer() == 0) {
            sleep(1000);
            game.setScreen(new GameWonScreen(game));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.BACKSPACE)) {
            System.exit(0);
        }
    }

    public static void sleep (int time) {

        try {
            Thread.sleep(time);
        } catch (Exception e) {

        }
    }

    public static void pauseAndResume () {

        if (Gdx.input.isKeyPressed(Input.Keys.P)) {

            sleep(10000);
        }
    }

    @Override
    public void dispose() {
        player.dispose();
    }
}

