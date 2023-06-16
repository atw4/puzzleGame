package com.mygdx.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.helpers.HelperSprite;
import com.mygdx.game.helpers.SpriteAnimation;
import com.mygdx.game.models.GameModel;
import com.mygdx.game.models.HumanModel;

public class HumanView extends AbstractView{

    private Texture texture;
    private Sprite sprite;

    private HumanModel human;

    private Texture runningTexture;
    SpriteAnimation<TextureRegion> runningAnimation; // Must declare frame type (TextureRegion)
    float runningTime = 0;

    boolean isRunning = false;

    public HumanView(HumanModel humanModel) {
        human = humanModel;

        //Init the running animation
        runningTexture = new Texture("Free/Main Characters/Ninja Frog/Run (32x32).png");
        runningAnimation = HelperSprite.getAnimation(runningTexture
                                ,human.getRectangle()
                                ,12
                                ,1
                                ,0.025f);

        //Init the default animation
        texture = new Texture("Free/Main Characters/Ninja Frog/Fall (32x32).png");

        sprite = HelperSprite.getSprite(texture, human.getRectangle());
    }

    @Override
    public void draw(float delta, GameManager gameManager) {
        if(isRunning){
            runningTime += delta;

            gameManager.batch.begin();
            runningAnimation.draw(runningTime, gameManager.batch);
            gameManager.batch.end();
        }else {
            gameManager.batch.begin();
            sprite.draw(gameManager.batch);
            gameManager.batch.end();
        }
    }

    @Override
    public void update() {
        Rectangle humanRect = human.getRectangle();
        sprite.setPosition(humanRect.x, humanRect.y);
        runningAnimation.setPosition(humanRect.x, humanRect.y);

        if(isRunning != human.isRunning()){
            isRunning = human.isRunning();
            runningTime = 0;

        }
    }

    @Override
    public int getRenderingPriority() {
        return 2;
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
