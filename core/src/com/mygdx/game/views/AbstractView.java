package com.mygdx.game.views;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public abstract class AbstractView {


    protected Texture texture;
    protected Rectangle rectangle;
    protected Sprite sprite;


    public static Sprite getSpriteFromTextureAndRect(Texture texture, Rectangle rectangle){
        Sprite sprite = new Sprite(texture);
        sprite.setPosition(rectangle.x, rectangle.y);
        sprite.setScale(rectangle.width/texture.getWidth(), rectangle.height/texture.getHeight());
        sprite.setOrigin(rectangle.width/2, rectangle.height/2);

        return sprite;
    }

    public void draw(GameManager gameManager) {
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
