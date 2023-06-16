package com.mygdx.game.helpers;

import static com.badlogic.gdx.graphics.g2d.Batch.X1;
import static com.badlogic.gdx.graphics.g2d.Batch.X2;
import static com.badlogic.gdx.graphics.g2d.Batch.X3;
import static com.badlogic.gdx.graphics.g2d.Batch.X4;
import static com.badlogic.gdx.graphics.g2d.Batch.Y1;
import static com.badlogic.gdx.graphics.g2d.Batch.Y2;
import static com.badlogic.gdx.graphics.g2d.Batch.Y3;
import static com.badlogic.gdx.graphics.g2d.Batch.Y4;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class SpriteAnimation<T> extends Animation {

    float scaleX = 1;
    float scaleY = 1;

    float x = 0;

    float y = 0;

    float originX = 0;
    float originY = 0;

    final float[] vertices = new float[8];
    boolean dirty = true;

    SpriteAnimation(float frameDuration, TextureRegion[] walkFrames){
        super(frameDuration, walkFrames);
    }

    public void setScale(float scaleX, float scaleY){
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void setOrigin (float originX, float originY) {
        this.originX = originX;

        this.originY = originY;
    }


    public void draw (float stateTime, Batch batch) {
        TextureRegion region = (TextureRegion) this.getKeyFrame(stateTime, true);
        batch.draw(region, x, y, originX, originY, region.getRegionWidth(), region.getRegionHeight(), scaleX, scaleY, 0);
    }

}