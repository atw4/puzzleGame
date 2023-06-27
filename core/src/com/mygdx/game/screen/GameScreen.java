package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.ScreenType;
import com.mygdx.game.models.GameModel;
import com.mygdx.game.views.GameView;

public class GameScreen extends AbstractScreen implements Screen {
	private GameModel gameModel;
	private boolean isGameOver;

	private GameView gameView;

	public GameScreen(GameManager gameManager){
		super(gameManager, ScreenType.GAME);
		isGameOver = false;

		TiledMap map = new TmxMapLoader().load("maps/test_map_2.tmx");

		gameModel = new GameModel();
		gameModel.initFromTileMap(map);

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
