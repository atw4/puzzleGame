package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.constants.ScreenType;

import org.w3c.dom.Text;

public class GameOverScreen extends AbstractScreen implements Screen {
    private Stage stage;
    private Texture playUpTexture;
    private Texture playDownTexture;
    private ImageButton playAgainBtn;

    public GameOverScreen(final GameManager gameManager) {
        super(gameManager, ScreenType.MAIN_MENU);

        stage = new Stage(new StretchViewport(Constants.GAME_WIDTH, Constants.GAME_HEIGHT));
        Gdx.input.setInputProcessor(stage);



        this.createScene();
    }


    private void createScene(){
        //Create the play again button
        //Add the button
        playUpTexture = new Texture("button.png");
        Drawable playUpDrawable = new TextureRegionDrawable(new TextureRegion(playUpTexture));

        playDownTexture = new Texture("button_down.png");
        Drawable playDownDrawable = new TextureRegionDrawable(new TextureRegion(playDownTexture));

        playAgainBtn = new ImageButton(playUpDrawable, playDownDrawable);
        playAgainBtn.setPosition(stage.getWidth()/2 - playAgainBtn.getWidth()/2,
                stage.getHeight()/2 - playAgainBtn.getHeight()/2);
        playAgainBtn.setOrigin(playAgainBtn.getWidth()/2, playAgainBtn.getHeight()/2);
        stage.addActor(playAgainBtn);

        playAgainBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                gameManager.transitionToScreen(ScreenType.MAIN_MENU);
            }
        });

        //Add the button label
        Label.LabelStyle style = new Label.LabelStyle(gameManager.font, new Color(0, 0, 1, 1));
        Label playAgainLabel = new Label("Play Again", style);
        playAgainLabel.setWidth(playAgainBtn.getWidth());
        playAgainLabel.setPosition(playAgainBtn.getX(),
                playAgainBtn.getY() + playAgainBtn.getHeight()/2);
        playAgainLabel.setAlignment(Align.center);
        playAgainLabel.setTouchable(Touchable.disabled);
        stage.addActor(playAgainLabel);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        stage.act(delta);
        stage.draw();
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
        stage.dispose();
        playUpTexture.dispose();
        playUpTexture.dispose();


    }

}