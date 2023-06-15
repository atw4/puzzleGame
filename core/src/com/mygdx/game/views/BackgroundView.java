package com.mygdx.game.views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;

import org.w3c.dom.Text;


public class BackgroundView extends AbstractView{
    Sprite backgroundSprite;
    public BackgroundView(){

        texture = new Texture("rainbow.jpg");
        rectangle = new Rectangle();
        rectangle.width = Constants.GAME_WIDTH;
        rectangle.height = Constants.GAME_HEIGHT;

        sprite = AbstractView.getSpriteFromTextureAndRect(texture, rectangle);
        sprite.setOrigin(0.0F, 0.0F);
    }
}
