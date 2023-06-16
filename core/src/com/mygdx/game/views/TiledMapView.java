package com.mygdx.game.views;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;

public class TiledMapView extends AbstractView{

    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    ;

    TiledMapView(TiledMap map){
        this.map = map;

        MapProperties prop = map.getProperties();

        int mapWidth = prop.get("width", Integer.class);
        int mapHeight = prop.get("height", Integer.class);
        int tilePixelWidth = prop.get("tilewidth", Integer.class);
        int tilePixelHeight = prop.get("tileheight", Integer.class);

        int mapPixelWidth = mapWidth * tilePixelWidth;
        int mapPixelHeight = mapHeight * tilePixelHeight;

        float scale = Math.max( (float)Constants.GAME_WIDTH / (float)mapPixelWidth, (float)Constants.GAME_HEIGHT / (float)mapPixelHeight);
        renderer = new OrthogonalTiledMapRenderer(map, scale);
    }

    @Override
    public void draw(float delta, GameManager gameManager) {
        renderer.setView((OrthographicCamera) gameManager.getCamera());
        renderer.render();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void update() {

    }

    @Override
    public int getRenderingPriority() {
        return 0;
    }
}
