package com.mygdx.game.screen;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.constants.ScreenType;
import com.mygdx.game.models.EnemyModel;
import com.mygdx.game.models.GameModel;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.views.AbstractView;
import com.mygdx.game.views.BackgroundView;
import com.mygdx.game.views.EnemyView;
import com.mygdx.game.views.HumanView;

import java.util.ArrayList;

public class GameScreen extends AbstractScreen implements Screen {
	SpriteBatch batch;
	Texture img;

	Camera camera;

	private GameModel gameModel;
	private boolean isGameOver;


	private HumanView humanView;
	private ArrayList<EnemyView> enemyViews;


	private BackgroundView backgroundView;

	public GameScreen(GameManager gameManager){
		super(gameManager, ScreenType.GAME);

		camera = new OrthographicCamera(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		camera.position.x = Constants.GAME_WIDTH/2;
		camera.position.y = Constants.GAME_HEIGHT/2;

		isGameOver = false;

		this.gameModel = new GameModel();


		//Init human view
		humanView = new HumanView(this.gameModel.getHuman());

		//Init enemeny views
		enemyViews = new ArrayList<EnemyView>();

		ArrayList<EnemyModel> enemies = this.gameModel.getEnemies();
		for(int i = 0; i < enemies.size(); i++){
			EnemyModel enemy = enemies.get(i);

			EnemyView enemyView = new EnemyView(enemy);
			enemyViews.add(enemyView);
		}

		//Init background view
		backgroundView = new BackgroundView();

	}


	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		//Update models
		gameModel.update(delta);
		if(!this.isGameOver && gameModel.isGameOver()){
			this.isGameOver = true;
			this.gameManager.transitionToScreen(ScreenType.GAME_OVER);
		}

		//Update all the views, after updating the models
		for(int i = 0; i < enemyViews.size(); i++){
			EnemyView view = enemyViews.get(i);
			view.update();
		}
		humanView.update();


		//Clear screen, camera
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		gameManager.batch.setProjectionMatrix(camera.combined);

		//Render all the views
		backgroundView.draw(gameManager);

		humanView.draw(gameManager);
		for(int i = 0; i < enemyViews.size(); i++){
			EnemyView enemyView = enemyViews.get(i);
			enemyView.draw(gameManager);
		}
	}



	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
