package com.mygdx.game.models;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        this.initModels();
    }


    private void initModels(){
        //init human
        Rectangle humanRect = new Rectangle();
        humanRect.x = Constants.GAME_WIDTH/2;
        humanRect.y = Constants.GAME_HEIGHT/2;
        humanRect.width = 50;
        humanRect.height = 50;
        human = new HumanModel(humanRect);



        //init enemies
        Random rand = new Random();

        float minSpeed = 200;
        float maxSpeed = 250;
        enemies = new ArrayList<EnemyModel>();
        for(int i = 0; i < numOfEnemies; i++){
            Rectangle enemyRectangle = new Rectangle();
            enemyRectangle.x = rand.nextInt(0, Constants.GAME_WIDTH);
            enemyRectangle.y = rand.nextInt(0, Constants.GAME_HEIGHT);
            enemyRectangle.width = 50;
            enemyRectangle.height = 50;

            Vector2 enemyVelocity = new Vector2();
            enemyVelocity.x = (rand.nextInt(0, 1)*2 - 1) * rand.nextFloat(minSpeed, maxSpeed);
            enemyVelocity.y = (rand.nextInt(0, 1)*2 - 1) * rand.nextFloat(minSpeed, maxSpeed);

            EnemyModel enemy = new EnemyModel(enemyRectangle, enemyVelocity);
            enemies.add(enemy);
        }
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
