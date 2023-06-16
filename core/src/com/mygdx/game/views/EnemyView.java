package com.mygdx.game.views;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.helpers.HelperSprite;
import com.mygdx.game.models.EnemyModel;
import com.mygdx.game.models.GameModel;

public class EnemyView extends AbstractView{
    private Texture texture;
    private EnemyModel enemy;

    private Sprite sprite;

    public EnemyView(EnemyModel enemyModel) {
        enemy = enemyModel;

        texture = new Texture("Free/Main Characters/Pink Man/Fall (32x32).png");

        sprite = HelperSprite.getSprite(texture, enemy.getRectangle());
    }


    @Override
    public void update() {
        Rectangle enemyRect = enemy.getRectangle();
        sprite.setPosition(enemyRect.x, enemyRect.y);
    }

    @Override
    public int getRenderingPriority() {
        return 1;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void draw(float delta, GameManager gameManager) {
        gameManager.batch.begin();
        sprite.draw(gameManager.batch);
        gameManager.batch.end();
    }

}
