package com.mygdx.game.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.constants.Direction;
import com.mygdx.game.models.m.AbstractModel;
import com.mygdx.game.models.m.CameraModel;
import com.mygdx.game.models.m.EnemyModel;
import com.mygdx.game.models.m.HumanModel;
import com.mygdx.game.models.m.TiledMapModel;

import java.util.ArrayList;
import java.util.Random;

public class GameModel {
    private HumanModel human;
    private ArrayList<EnemyModel> enemies;
    private TiledMapModel tiledMapModel;

    private CameraModel cameraModel;



    float totalStep = 0;
    private int numOfEnemies = 21;

    private TiledMap tiledMap;


    public GameModel(){
    }


    public void initFromTileMap(TiledMap tiledMap) {
        this.tiledMap = tiledMap;

        this.totalStep = 0;

        cameraModel = new CameraModel(new Rectangle(0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT), this);

        //Init tilemap model
        tiledMapModel = new TiledMapModel(this.tiledMap);

        //Init human
        MapLayer humanLayer = tiledMap.getLayers().get("human");
        MapObject humanObject = (RectangleMapObject) humanLayer.getObjects().get(0);
        MapProperties humanProp = humanObject.getProperties();
        int positionX = (int) ((Float)humanProp.get("x")).floatValue();
        int positionY = (int) ((Float)humanProp.get("y")).floatValue();
        human = this.initHuman(positionX, positionY);


        //Init enemies
        enemies = new ArrayList<EnemyModel>();
        MapLayer enemyLayer = tiledMap.getLayers().get("enemies");
        MapObjects enemyObjects = enemyLayer.getObjects();
        for(int i = 0; i < enemyObjects.getCount(); i++){
            MapObject enemyObject = enemyObjects.get(i);
            MapProperties enemyProp = enemyObject.getProperties();

            int enemyPositionX = (int) ((Float)enemyProp.get("x")).floatValue();
            int enemyPositionY = (int) ((Float)enemyProp.get("y")).floatValue();

            EnemyModel enemy = this.initEnemy(enemyPositionX , enemyPositionY);
            enemies.add(enemy);
        }
    }


    private HumanModel initHuman(int positionX, int positionY){
        Rectangle humanRect = new Rectangle();
        humanRect.x = positionX;
        humanRect.y = positionY;
        humanRect.width = 50;
        humanRect.height = 50;

        return new HumanModel(humanRect);
    }

    private EnemyModel initEnemy(int positionX, int positionY){
        float speed = 200;

        Random rand = new Random();
        float angle = (float) Math.toRadians(rand.nextFloat(0, 360));
        Vector2 enemyVelocity = new Vector2();

        enemyVelocity.x = (float) (Math.cos(angle)*speed);
        enemyVelocity.y = (float) (Math.sin(angle)*speed);


        Rectangle enemyRectangle = new Rectangle();
        enemyRectangle.x = positionX;
        enemyRectangle.y = positionY;
        enemyRectangle.width = 50;
        enemyRectangle.height = 50;

        return new EnemyModel(enemyRectangle, enemyVelocity, this);
    }

    public void update(float delta){
        this.totalStep += delta;

        //Update the human movement
        human.setIsMove(Direction.LEFT, Gdx.input.isKeyPressed(Input.Keys.LEFT));
        human.setIsMove(Direction.RIGHT, Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        human.setIsMove(Direction.UP, Gdx.input.isKeyPressed(Input.Keys.UP));
        human.setIsMove(Direction.DOWN, Gdx.input.isKeyPressed(Input.Keys.DOWN));
        human.setJump(Gdx.input.isKeyPressed(Input.Keys.SPACE));

        human.step(delta);
        for(Direction dir : Direction.values()){
            Rectangle collisionRect = tiledMapModel.checkCollision(human, dir);

            human.setIsCollide(dir, collisionRect != null);

            if(collisionRect != null){
                switch(dir){
                    case UP:
                        human.setY(collisionRect.y - human.getHeight());
                        break;
                    case DOWN:
                        human.setY(collisionRect.y + collisionRect.getHeight());
                        break;
                    case LEFT:
                        human.setX(collisionRect.x + collisionRect.getWidth());
                        break;
                    case RIGHT:
                        human.setX(collisionRect.x - human.getWidth());
                        break;
                }


            }
        }


        cameraModel.step(delta);

        if(!human.isDead()){
            for(int i = 0; i < enemies.size(); i++){
                EnemyModel enemy = enemies.get(i);

                enemy.step(delta);
            }
        }





        if(!human.isDead()){
            boolean isCollision = checkCollision(human, enemies);
            if(isCollision){
                human.setDead(true);
            }
        }

    }

    public boolean isGameOver(){
        return human.isDead();
    }

    public HumanModel getHuman(){
        return human;
    }
    public ArrayList<EnemyModel> getEnemies(){
        return enemies;
    }

    public TiledMap getTiledMap(){
        return tiledMap;
    }

    public float getTotalStep() {
        return totalStep;
    }
    public TiledMapModel getTiledMapModel() { return tiledMapModel; }

    public CameraModel getCameraModel() {
        return cameraModel;
    }
    public boolean checkCollision(AbstractModel checkModel, ArrayList<? extends AbstractModel> otherModels){
        Rectangle checkRect = checkModel.getRectangle();

        Rectangle intersection = new Rectangle();
        boolean isCollision = false;
        for(int i = 0; i < otherModels.size() && !isCollision; i++){
            AbstractModel otherModel = otherModels.get(i);

            isCollision = Intersector.intersectRectangles(checkModel.getRectangle(), otherModel.getRectangle(), intersection);
        }

        return isCollision;
    }
}
