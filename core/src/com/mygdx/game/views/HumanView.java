package com.mygdx.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.models.HumanModel;

public class HumanView extends AbstractView{
    private HumanModel human;

    public HumanView(HumanModel humanModel) {
        human = humanModel;

        texture = new Texture("Free/Main Characters/Ninja Frog/Fall (32x32).png");
        rectangle = human.getRectangle();

        sprite = AbstractView.getSpriteFromTextureAndRect(texture, rectangle);
    }

    @Override
    public void update() {
        Rectangle humanRect = human.getRectangle();
        sprite.setPosition(humanRect.x, humanRect.y);
    }

    @Override
    public int getRenderingPriority() {
        return 2;
    }
}
