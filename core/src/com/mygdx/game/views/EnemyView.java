package com.mygdx.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.models.EnemyModel;

public class EnemyView extends AbstractView{
    private EnemyModel enemy;

    public EnemyView(EnemyModel enemyModel) {
        enemy = enemyModel;

        texture = new Texture("Free/Main Characters/Pink Man/Fall (32x32).png");
        rectangle = enemy.getRectangle();

        sprite = AbstractView.getSpriteFromTextureAndRect(texture, rectangle);
    }

    public void update() {
        Rectangle enemyRect = enemy.getRectangle();
        sprite.setPosition(enemyRect.x, enemyRect.y);
    }
}
