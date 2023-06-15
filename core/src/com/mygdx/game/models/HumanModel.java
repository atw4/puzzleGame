package com.mygdx.game.models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class HumanModel extends AbstractModel {

    private Vector2 velocity;


    private boolean moveUp = false;
    private boolean moveDown = false;
    private boolean moveLeft = false;
    private boolean moveRight = false;

    private boolean isDead = false;

    public HumanModel(Rectangle rectangle){
        super(rectangle);
        velocity = new Vector2();
    }
    @Override
    public void step(float delta) {
        velocity.x = 0;
        velocity.y = 0;


        if(moveDown && !moveUp){
            velocity.y = -50;
        }else if(!moveDown && moveUp){
            velocity.y = 50;
        }

        if(moveLeft && !moveRight){
            velocity.x = -50;
        }else if(!moveLeft && moveRight){
            velocity.x = 50;
        }

        rectangle.x += velocity.x * delta;
        rectangle.y += velocity.y * delta;
    }


    public Vector2 getVelocity(){
        return velocity;
    }


    public boolean isMoveUp() {
        return moveUp;
    }

    public boolean isMoveDown() {
        return moveDown;
    }

    public boolean isMoveLeft() {
        return moveLeft;
    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setMoveUp(boolean moveUp) {
        this.moveUp = moveUp;
    }

    public void setMoveDown(boolean moveDown) {
        this.moveDown = moveDown;
    }

    public void setMoveLeft(boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

    public boolean isDead(){
        return this.isDead;
    }
    public void setIsDead(boolean isDead){
        this.isDead = isDead;
    }

}
