package com.mygdx.game.helpers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class HelperSprite {
    public static Sprite getSprite(Texture texture, Rectangle rectangle) {
        Sprite sprite = new Sprite(texture);
        sprite.setPosition(rectangle.x, rectangle.y);
        sprite.setScale(rectangle.width/texture.getWidth(), rectangle.height/texture.getHeight());
        sprite.setOrigin(0,0);

        return sprite;
    }

    public static SpriteAnimation<TextureRegion> getAnimation(Texture texture,
                                                  Rectangle rectangle,
                                                  int numOfCols,
                                                  int numOfRows,
                                                  float frameDuration) {

        int tileWidth = texture.getWidth()/numOfCols;
        int tileHeight = texture.getHeight()/numOfRows;

        TextureRegion[][] tmp = TextureRegion.split(texture, tileWidth, tileHeight);
        TextureRegion[] walkFrames = new TextureRegion[numOfCols * numOfRows];

        int index = 0;
        for (int i = 0; i < numOfRows; i++) {
            for (int j = 0; j < numOfCols; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        SpriteAnimation<TextureRegion> animation = new SpriteAnimation<TextureRegion>(frameDuration, walkFrames);
        animation.setScale(rectangle.width/tileWidth, rectangle.height/tileHeight);
        animation.setOrigin(0,0);
        return animation;
    }



}
