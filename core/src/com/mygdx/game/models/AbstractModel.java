package com.mygdx.game.models;


import com.badlogic.gdx.math.Rectangle;

public abstract class AbstractModel {

    protected Rectangle rectangle;

    protected AbstractModel(Rectangle rectangle){
        this.rectangle = rectangle;
    }
    abstract public void step(float delta);


    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

}
