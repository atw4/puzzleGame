package com.mygdx.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.helpers.HelperSprite;
import com.mygdx.game.helpers.SpriteAnimation;
import com.mygdx.game.models.GameModel;

import org.w3c.dom.Text;


public class BackgroundView extends AbstractView{

    private Texture texture;
    private Rectangle rectangle;
    private Sprite sprite;

    public BackgroundView(){
        texture = new Texture("rainbow.jpg");
        rectangle = new Rectangle();
        rectangle.width = Constants.GAME_WIDTH;
        rectangle.height = Constants.GAME_HEIGHT;

        sprite = HelperSprite.getSprite(texture, rectangle);
        sprite.setOrigin(0.0F, 0.0F);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void update() {
    }

    @Override
    public int getRenderingPriority() {
        return -1;
    }


    @Override
    public void draw(float delta, GameManager gameManager) {
        gameManager.batch.begin();
        sprite.draw(gameManager.batch);
        gameManager.batch.end();
    }


}
