package com.mygdx.game.views.v;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.helpers.HelperSprite;
import com.mygdx.game.models.m.CameraModel;
import com.mygdx.game.views.GameView;


public class BackgroundView extends AbstractView{

    private Texture texture;
    private Rectangle rectangle;
    private Sprite sprite;

    private CameraModel cameraModel;

    public BackgroundView(CameraModel cameraModel){
        this.cameraModel = cameraModel;

        texture = new Texture("rainbow.jpg");
        rectangle = new Rectangle();
        rectangle.width = Constants.GAME_WIDTH;
        rectangle.height = Constants.GAME_HEIGHT;

        sprite = HelperSprite.getSprite(texture, rectangle);
        sprite.setOrigin(-sprite.getWidth(), -sprite.getHeight());
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

    @Override
    public void update() {
        sprite.setX( cameraModel.getRectangle().x );
        sprite.setY( cameraModel.getRectangle().y );
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
