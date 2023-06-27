package com.mygdx.game.views;

import com.mygdx.game.GameManager;
import com.mygdx.game.models.m.EnemyModel;
import com.mygdx.game.models.GameModel;
import com.mygdx.game.views.v.AbstractView;
import com.mygdx.game.views.v.BackgroundView;
import com.mygdx.game.views.v.CameraView;
import com.mygdx.game.views.v.EnemyView;
import com.mygdx.game.views.v.HumanView;
import com.mygdx.game.views.v.TiledMapView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameView {
    private GameModel gameModel;




    private HumanView humanView;
    private ArrayList<EnemyView> enemyViews;


    private BackgroundView backgroundView;

    private ArrayList<AbstractView> views;
    private ArrayList<AbstractView> renderingViews;


    private TiledMapView tiledMapView;

    private CameraView cameraView;

    public  GameView(){

    }


    public void initFromModel(GameModel gameModel){
        this.gameModel = gameModel;


        //Init the views array
        views = new ArrayList<AbstractView>();

        //Init the camera view
        cameraView = new CameraView(this.gameModel.getCameraModel());
        views.add(cameraView);

        //Init the tiled map view
        tiledMapView = new TiledMapView(gameModel.getTiledMap(), gameModel.getTiledMapModel(), cameraView);
        views.add(tiledMapView);

        //Init human view
        humanView = new HumanView(gameModel.getHuman());
        views.add(humanView);

        //Init enemeny views
        enemyViews = new ArrayList<EnemyView>();

        ArrayList<EnemyModel> enemies = this.gameModel.getEnemies();
        for(int i = 0; i < enemies.size(); i++){
            EnemyModel enemy = enemies.get(i);

            EnemyView enemyView = new EnemyView(enemy);
            enemyViews.add(enemyView);
            views.add(enemyView);
        }

        //Init background view
        backgroundView = new BackgroundView(gameModel.getCameraModel());
        views.add(backgroundView);

        renderingViews = (ArrayList<AbstractView>) views.clone();
        Collections.sort(renderingViews, new Comparator<AbstractView>() {
            @Override
            public int compare(AbstractView abstractView, AbstractView t1) {
                return abstractView.getRenderingPriority() - t1.getRenderingPriority();
            }
        });
    }


    public void update(float delta){
        //Update all the views, after updating the models
        for(int i = 0; i < views.size(); i++){
            AbstractView view = views.get(i);
            view.update();
        }
    }

    public void draw(float delta, GameManager gameManager){
        //Update all the views, after updating the models
        for(int i = 0; i < renderingViews.size(); i++){
            AbstractView view = renderingViews.get(i);
            view.draw(delta, gameManager);
        }


    }


    public void dispose(){
        for(int i = 0; i < views.size(); i++){
            AbstractView view = views.get(i);
            view.dispose();
        }
    }


    public CameraView getCameraView() {
        return cameraView;
    }
}
