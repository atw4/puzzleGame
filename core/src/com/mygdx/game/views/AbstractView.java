package com.mygdx.game.views;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.models.GameModel;


public abstract class AbstractView {

    public abstract void draw(float delta, GameManager gameManager);

    public abstract void dispose();

    public abstract void update();

    public abstract int getRenderingPriority();
}
