package com.mygdx.game.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.ScreenType;

public class MainMenuScreen extends AbstractScreen implements Screen {

    OrthographicCamera camera;

    final private int fadeOutTime = 0;
    private DelayAction fadeOutAction;

    public MainMenuScreen(final GameManager gameManager) {
        super(gameManager, ScreenType.MAIN_MENU);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);



        this.transition();
    }

    private void transition(){
        RunnableAction run = Actions.run( new Runnable(){

            @Override
            public void run() {
                gameManager.transitionToScreen(ScreenType.GAME);
            }
        });

        fadeOutAction = Actions.delay(fadeOutTime, run);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        gameManager.batch.setProjectionMatrix(camera.combined);

        gameManager.batch.begin();
        gameManager.font.draw(gameManager.batch, "Welcome to Puzzle Game", 100, 150);
        gameManager.batch.end();

        fadeOutAction.act(delta);
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
    public void dispose() {

    }
}