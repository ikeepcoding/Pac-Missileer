
package com.mygdx.game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.mygdx.game.PacMan;
import com.mygdx.game.Textures.TextureManager;


/**
 * Created by AbZ on 06/06/2017.
 */


public class Player extends Entity implements InputProcessor, Disposable {

    private final EntityManager entityManager;
    private final OrthographicCamera camera;
    private boolean movingRight;
    private boolean movingLeft;
    private boolean movingUp;
    private boolean movingDown;
    private long previousFire;


    public Player (Vector2 pos, Vector2 direction,  EntityManager entityManager, OrthographicCamera camera) {
        super(TextureManager.PLAYER, pos, direction);
        this.entityManager = entityManager;
        this.camera = camera;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void update() {

        pos.add(direction);  //updates the position, when direction is changed
        int dir = 0;

        if (Gdx.input.isTouched()) {
            Vector3 touch = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            if (touch.x < PacMan.G_WIDTH/2) {
                dir = 1;
            } else {
                dir = 2;
            }
        }

        if (movingLeft || dir == 1 ) {
            setDirection(-400, 0);
        } else if (movingRight || dir == 2) {
            setDirection(400, 0);
        } else if (movingUp ) {
            setDirection(0, 300);
        } else if (movingDown ) {
            setDirection(0, -300);
        } else {
            setDirection(0, 0);                                //On press no keys, the direction of the player remains neutral
        }
            if (System.currentTimeMillis() - previousFire >= 1000) {            //the last fired missile a second earlier
                entityManager.addEntity(new Missile(pos.cpy().add (0,0)));    //an entity of the Missile class is added to the pos of the player
                previousFire = System.currentTimeMillis();                    //previous fire set to the current time
            }
    }

    @Override
    public boolean keyDown(int keycode) {

        if (keycode== Input.Keys.LEFT) {
            movingLeft = true;
        }
        else  if (keycode== Input.Keys.RIGHT) {
            movingRight = true;
        }
        else  if (keycode== Input.Keys.UP) {
            movingUp = true;
        }
        else if (keycode== Input.Keys.DOWN) {
            movingDown = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode== Input.Keys.LEFT) {
            movingLeft = false;
        }
        else if (keycode== Input.Keys.RIGHT) {
            movingRight = false;
        }
        else if (keycode== Input.Keys.UP) {
            movingUp = false;
        }
        else if (keycode== Input.Keys.DOWN) {
            movingDown = false;
        }

        return false;
    }

    public void dispose() {
        entityManager.dispose();
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {return false;}

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {return false;}

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {return false;}

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }


}
