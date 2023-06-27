package com.mygdx.game.models.m;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.models.GameModel;
import com.mygdx.game.models.m.AbstractModel;

public class CameraModel extends AbstractModel {

    private GameModel gameModel;

    public CameraModel(Rectangle rectangle, GameModel gameModel) {
        super(rectangle);

        this.gameModel = gameModel;
    }

    @Override
    public void step(float delta) {
        HumanModel human = gameModel.getHuman();
        TiledMapModel tiledMapModel = gameModel.getTiledMapModel();

        rectangle.x = human.getX();
        rectangle.y = human.getY();

        rectangle.x = Math.max(rectangle.x, this.getWidth()/2 + tiledMapModel.getX());
        rectangle.y = Math.max(rectangle.y, this.getHeight()/2 + tiledMapModel.getY());
        rectangle.x = Math.min(rectangle.x, -this.getWidth()/2 + (tiledMapModel.getX() + tiledMapModel.getWidth()) );
        rectangle.y = Math.min(rectangle.y, -this.getHeight()/2 + (tiledMapModel.getY() + tiledMapModel.getHeight()) );
    }
}
