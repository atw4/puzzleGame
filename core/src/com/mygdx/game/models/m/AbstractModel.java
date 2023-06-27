package com.mygdx.game.models.m;


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

    public void setX(float x){
        rectangle.setX(x);
    }
    public float getX(){
        return rectangle.getX();
    }

    public void setY(float y){
        rectangle.setY(y);
    }
    public float getY(){
        return rectangle.getY();
    }

    public void setWidth(float width){
        rectangle.setWidth(width);
    }
    public float getWidth(){
        return rectangle.getWidth();
    }

    public void setHeight(float height){
        rectangle.setHeight(height);
    }
    public float getHeight(){
        return rectangle.getHeight();
    }

}
