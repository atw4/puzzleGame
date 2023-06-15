package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.constants.ScreenType;
import com.mygdx.game.screen.GameOverScreen;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MainMenuScreen;

public class GameManager extends Game {
    public SpriteBatch batch;
    public BitmapFont font;

    public void create(){
        batch = new SpriteBatch();
        font = new BitmapFont();
        //this.transitionToScreen(ScreenType.MAIN_MENU);
        //this.transitionToScreen(ScreenType.GAME);
        this.transitionToScreen(ScreenType.GAME_OVER);
    }

    public void render() {
        super.render();
    }

    public void dispose(){
        batch.dispose();
        font.dispose();
    }

    public void transitionToScreen(ScreenType screenType){
        Screen screen = this.getScreen();
        if(screen != null) {
            screen.dispose();
        }

        switch(screenType){
            case MAIN_MENU:
                this.setScreen(new MainMenuScreen(this));
                break;
            case GAME:
                this.setScreen(new GameScreen(this));
                break;
            case GAME_OVER:
                this.setScreen(new GameOverScreen(this));
                break;
        }
    }
}
