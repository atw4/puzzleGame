package com.mygdx.game.screen;

import com.mygdx.game.GameManager;
import com.mygdx.game.constants.ScreenType;

public abstract class AbstractScreen {

    protected GameManager gameManager;

    protected ScreenType screenType;

    AbstractScreen(GameManager gameManager, ScreenType screenType){
        this.gameManager = gameManager;
        this.screenType = screenType;
    }


    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }



    public void setScreenType(ScreenType screenType) {
        this.screenType = screenType;
    }

    public ScreenType getScreenType() {
        return screenType;
    }
}
