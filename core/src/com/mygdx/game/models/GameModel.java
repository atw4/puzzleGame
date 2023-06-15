package com.mygdx.game.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.views.AbstractView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class GameModel {
    private HumanModel human;
    private ArrayList<EnemyModel> enemies;



    private int gameIndex;

    private int numOfEnemies = 21;

    public GameModel(){
    }

    public void initTest() {
        initModels();
    }

    public void initFromTileMap() {
        TiledMap map = new TmxMapLoader().load("maps/test_map.tmx");
        MapProperties mapProp = map.getProperties();
        int mapWidth = mapProp.get("width", Integer.class);
        int tileWidth = mapProp.get("tilewidth", Integer.class);
        int mapHeight = mapProp.get("height", Integer.class);
        int tileHeight = mapProp.get("tileheight", Integer.class);

        int totalWidth = mapWidth*tileWidth;
        int totalHeight = mapHeight*tileHeight;

        float scalePosX = (float) Constants.GAME_WIDTH/ (float) totalWidth;
        float scalePosY = (float) Constants.GAME_HEIGHT/ (float) totalHeight;

        //Init human
        MapLayer humanLayer = map.getLayers().get("human");
        MapObject humanObject = (RectangleMapObject) humanLayer.getObjects().get(0);
        MapProperties humanProp = humanObject.getProperties();
        int positionX = (int) ((Float)humanProp.get("x")).floatValue();
        int positionY = (int) ((Float)humanProp.get("y")).floatValue();
        human = this.initHuman((int) (positionX * scalePosX), (int)(positionY * scalePosY));


        //Init enemies
        enemies = new ArrayList<EnemyModel>();
        MapLayer enemyLayer = map.getLayers().get("enemies");
        MapObjects enemyObjects = enemyLayer.getObjects();
        for(int i = 0; i < enemyObjects.getCount(); i++){
            MapObject enemyObject = enemyObjects.get(i);
            MapProperties enemyProp = enemyObject.getProperties();

            int enemyPositionX = (int) ((Float)enemyProp.get("x")).floatValue();
            int enemyPositionY = (int) ((Float)enemyProp.get("y")).floatValue();

            EnemyModel enemy = this.initEnemy((int) (enemyPositionX * scalePosX), (int)(enemyPositionY * scalePosY));
            enemies.add(enemy);
        }

    }


    private void initModels(){
        //init human
        human = this.initHuman(Constants.GAME_WIDTH/2, Constants.GAME_HEIGHT/2);

        //init enemies
        Random rand = new Random();
        float speed = 200;

        enemies = new ArrayList<EnemyModel>();
        for(int i = 0; i < numOfEnemies; i++){
            int positionX = rand.nextInt(0, Constants.GAME_WIDTH);
            int positionY = rand.nextInt(0, Constants.GAME_HEIGHT);

            EnemyModel enemy = initEnemy(positionX, positionY);
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

        return new EnemyModel(enemyRectangle, enemyVelocity);
    }

    public void update(float delta){

        human.step(delta);

        if(!human.isDead()){
            for(int i = 0; i < enemies.size(); i++){
                EnemyModel enemy = enemies.get(i);

                enemy.step(delta);
            }
        }

        human.setMoveLeft(Gdx.input.isKeyPressed(Input.Keys.LEFT));
        human.setMoveRight(Gdx.input.isKeyPressed(Input.Keys.RIGHT));
        human.setMoveUp(Gdx.input.isKeyPressed(Input.Keys.UP));
        human.setMoveDown(Gdx.input.isKeyPressed(Input.Keys.DOWN));


        if(!human.isDead()){
            boolean isCollision = checkCollision(human, enemies);
            if(isCollision){
                human.setIsDead(true);
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
