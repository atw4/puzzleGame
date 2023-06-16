package com.mygdx.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.helpers.HelperSprite;
import com.mygdx.game.models.GameModel;

import org.w3c.dom.Text;


public class BackgroundView extends AbstractView{
    public BackgroundView(){
        texture = new Texture("rainbow.jpg");
        rectangle = new Rectangle();
        rectangle.width = Constants.GAME_WIDTH;
        rectangle.height = Constants.GAME_HEIGHT;

        sprite = HelperSprite.getSprite(texture, rectangle);
        sprite.setOrigin(0.0F, 0.0F);
    }

    @Override
    public void update() {

    }

    @Override
    public int getRenderingPriority() {
        return 0;
    }


}
