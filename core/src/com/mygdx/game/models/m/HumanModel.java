package com.mygdx.game.models.m;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.constants.Direction;
import com.mygdx.game.models.m.AbstractModel;

import java.util.EnumMap;

public class HumanModel extends AbstractModel {

    private Vector2 velocity;

    private boolean isDead = false;


    private boolean isJump = false;

    private EnumMap<Direction, Boolean> moveMap;
    private EnumMap<Direction, Boolean> isCollideMap;

    public HumanModel(Rectangle rectangle){
        super(rectangle);
        velocity = new Vector2(0, 0);

        isCollideMap = new EnumMap<Direction, Boolean>(Direction.class);
        moveMap = new EnumMap<Direction, Boolean>(Direction.class);
        for(Direction dir : Direction.values()){
            isCollideMap.put(dir, false);
            moveMap.put(dir, false);
        }
    }

    public boolean isCollide(Direction dir){
        return isCollideMap.get(dir);
    }

    public void setIsCollide(Direction dir, boolean val){
        isCollideMap.put(dir, val);
    }


    @Override
    public void step(float delta) {
        boolean moveDown = this.isMove(Direction.DOWN);
        boolean moveUp = this.isMove(Direction.UP);
        boolean moveLeft = this.isMove(Direction.LEFT);
        boolean moveRight = this.isMove(Direction.RIGHT);

        /*
        if(moveDown && !moveUp && !isCollide(Direction.DOWN)){
            velocity.y = -Constants.HERO_SPEED;
        }else if(!moveDown && moveUp && !isCollide(Direction.UP)){
            velocity.y = Constants.HERO_SPEED;
        }
        */

        if(moveLeft && !moveRight && !isCollide(Direction.LEFT)){
            velocity.x = -Constants.HERO_SPEED;
        }else if(!moveLeft && moveRight && !isCollide(Direction.RIGHT)){
            velocity.x = Constants.HERO_SPEED;
        }else {
            velocity.x = 0;
        }

        if(isCollide(Direction.DOWN)){
            if(isJump){
                velocity.y = Constants.JUMP_SPEED;
            }
        }else {
            velocity.y += Constants.GRAVITY * delta;
        }

        rectangle.x += velocity.x * delta;
        rectangle.y += velocity.y * delta;
    }


    public Vector2 getVelocity(){
        return velocity;
    }

    public boolean isMove(Direction dir){
        return moveMap.get(dir);
    }
    public boolean setIsMove(Direction dir, boolean val){
        return moveMap.put(dir, val);
    }
    public boolean isDead(){
        return this.isDead;
    }
    public void setDead(boolean isDead){
        this.isDead = isDead;
    }

    public boolean isJump() {
        return isJump;
    }
    public void setJump(boolean jump) {
        isJump = jump;
    }


    public boolean isRunning(){
        return velocity.x != 0 || velocity.y != 0;
    }

}
