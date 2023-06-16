package com.mygdx.game.views;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.models.GameModel;


public abstract class AbstractView {


    protected Texture texture;
    protected Rectangle rectangle;
    protected Sprite sprite;

    public void draw(float delta, GameManager gameManager) {
        gameManager.batch.begin();
        sprite.draw(gameManager.batch);
        gameManager.batch.end();
    }

    public void dispose(){
        texture.dispose();
    }

    public abstract void update();

    public abstract int getRenderingPriority();
}
