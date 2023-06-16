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
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
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
import com.mygdx.game.views.GameView;
import com.mygdx.game.views.HumanView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GameScreen extends AbstractScreen implements Screen {
	Camera camera;

	private GameModel gameModel;
	private boolean isGameOver;

	private GameView gameView;

	public GameScreen(GameManager gameManager){
		super(gameManager, ScreenType.GAME);

		camera = new OrthographicCamera(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
		camera.position.x = Constants.GAME_WIDTH/2;
		camera.position.y = Constants.GAME_HEIGHT/2;

		isGameOver = false;

		gameModel = new GameModel();
		gameModel.initFromTileMap();

		gameView = new GameView();
		gameView.initFromModel(gameModel);


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

		gameView.update(delta);

		//Clear screen, camera
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		gameManager.batch.setProjectionMatrix(camera.combined);

		//Render all the views
		gameView.draw(delta, gameManager);
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
		gameView.dispose();

	}
}
