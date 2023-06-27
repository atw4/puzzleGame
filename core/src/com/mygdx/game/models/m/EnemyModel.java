package com.mygdx.game.models.m;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.models.GameModel;
import com.mygdx.game.models.m.AbstractModel;


public class EnemyModel extends AbstractModel {
    private Vector2 velocity;

    private GameModel gameModel;

    public EnemyModel(Rectangle rectangle, Vector2 velocity, GameModel gameModel){
        super(rectangle);

        this.velocity = velocity;
        this.gameModel = gameModel;
    }

    @Override
    public void step(float delta){
        rectangle.x += velocity.x * delta;
        rectangle.y += velocity.y * delta;

        TiledMapModel tiledMapModel = gameModel.getTiledMapModel();


        //Check for out of bounds
        if(rectangle.x < (tiledMapModel.getX()) || rectangle.x > (tiledMapModel.getX() + tiledMapModel.getWidth())){
            velocity.x *= -1;
        }
        if(rectangle.y < (tiledMapModel.getY()) || rectangle.y > (tiledMapModel.getY() + tiledMapModel.getHeight())){
            velocity.y *= -1;
        }
    }



    public Vector2 getVelocity(){
        return velocity;
    }
}
