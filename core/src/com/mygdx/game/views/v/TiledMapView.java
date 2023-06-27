package com.mygdx.game.views.v;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.GameManager;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.models.m.TiledMapModel;


public class TiledMapView extends AbstractView{

    private TiledMap map;

    private TiledMapModel tiledMapModel;
    private OrthogonalTiledMapRenderer renderer;

    private CameraView cameraView;

    public TiledMapView(TiledMap map, TiledMapModel tiledMapModel, CameraView cameraView){
        this.map = map;
        this.tiledMapModel = tiledMapModel;
        this.cameraView = cameraView;

        renderer = new OrthogonalTiledMapRenderer(map);
    }

    @Override
    public void draw(float delta, GameManager gameManager) {
        renderer.setView((OrthographicCamera) cameraView.getCamera());
        Rectangle viewBounds = renderer.getViewBounds();

        int viewBoundsExtension = 100;
        viewBounds.x -= viewBoundsExtension;
        viewBounds.y -= viewBoundsExtension;
        viewBounds.width += viewBoundsExtension * 2;
        viewBounds.height += viewBoundsExtension * 2;

        renderer.render();


        if(Constants.DEBUG){

            gameManager.debugRenderer.begin(ShapeRenderer.ShapeType.Line);
            gameManager.debugRenderer.setColor(Color.YELLOW);

            for(MapLayer mapLayer : map.getLayers()){
                if(!(mapLayer instanceof TiledMapTileLayer)){
                    continue;
                }
                TiledMapTileLayer layer = (TiledMapTileLayer) mapLayer;

                for (int y = 0; y <= layer.getHeight(); y++) {
                    for (int x = 0; x <= layer.getWidth(); x++) {
                        TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                        if(cell == null){
                            continue;
                        }

                        TiledMapTile tile = cell.getTile();
                        MapObjects tileObjects = tile.getObjects();
                        for(MapObject tileObject : tileObjects){
                            if(tileObject instanceof RectangleMapObject){
                                Rectangle tileRect = ((RectangleMapObject) tileObject).getRectangle();
                                gameManager.debugRenderer.rect(x * layer.getTileWidth() + tileRect.x,
                                        y * layer.getTileHeight() + tileRect.y,
                                        tileRect.width,
                                        tileRect.height);
                            }
                        }
                    }
                }
            }
            gameManager.debugRenderer.end();
        }
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
