package com.mygdx.game.views.v;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.models.m.CameraModel;


public class CameraView extends AbstractView {

    OrthographicCamera camera;

    CameraModel cameraModel;

    public CameraView(CameraModel cameraModel){
        this.cameraModel = cameraModel;

        Rectangle rect = this.cameraModel.getRectangle();

        camera = new OrthographicCamera(rect.width, rect.height);
        camera.position.x = rect.x;
        camera.position.y = rect.y;
    }

    @Override
    public void draw(float delta, GameManager gameManager) {
        camera.update();

        gameManager.batch.setProjectionMatrix(camera.combined);

        if(Constants.DEBUG){
            gameManager.debugRenderer.setProjectionMatrix(camera.combined);
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void update() {
        camera.position.x = cameraModel.getRectangle().x;
        camera.position.y = cameraModel.getRectangle().y;
    }

    @Override
    public int getRenderingPriority() {
        return -100;
    }

    public Camera getCamera(){
        return camera;
    }
}
