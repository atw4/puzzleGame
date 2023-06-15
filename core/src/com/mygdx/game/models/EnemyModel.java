package com.mygdx.game.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.constants.Constants;


public class EnemyModel extends AbstractModel {
    private Vector2 velocity;

    public EnemyModel(Rectangle rectangle, Vector2 velocity){
        super(rectangle);

        this.velocity = velocity;
    }

    @Override
    public void step(float delta){
        rectangle.x += velocity.x * delta;
        rectangle.y += velocity.y * delta;

        //Check for out of bounds
        if(rectangle.x < 0 || rectangle.x > Constants.GAME_WIDTH){
            velocity.x *= -1;
        }
        if(rectangle.y < 0 || rectangle.y > Constants.GAME_HEIGHT){
            velocity.y *= -1;
        }
    }



    public Vector2 getVelocity(){
        return velocity;
    }
}
