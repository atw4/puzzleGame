package com.mygdx.game.models.m;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.constants.Constants;
import com.mygdx.game.constants.Direction;
import com.mygdx.game.models.m.AbstractModel;

import java.util.ArrayList;

public class TiledMapModel extends AbstractModel {

    TiledMap map;
    int mapWidth;
    int mapHeight;
    int tileWidth;
    int tileHeight;

    private ArrayList<Rectangle>physicsShapes;
    public TiledMapModel(TiledMap map) {
        super(new Rectangle(0, 0, 0, 0));

        this.map = map;
        MapProperties mapProperties = this.map.getProperties();

        mapWidth = ((Integer)mapProperties.get("width")).intValue();
        mapHeight = ((Integer)mapProperties.get("height")).intValue();
        tileWidth = ((Integer)mapProperties.get("tilewidth")).intValue();
        tileHeight = ((Integer)mapProperties.get("tileheight")).intValue();

        this.setWidth(mapWidth * tileWidth);
        this.setHeight(mapHeight * tileHeight);
    }



    @Override
    public void step(float delta) {

    }

    public Rectangle checkCollision(AbstractModel model, Direction dir){
        Rectangle rect = new Rectangle(model.getRectangle());
        if(dir == Direction.UP){
            rect.y += rect.height;
            rect.height = 0;
        }else if(dir == Direction.DOWN){
            rect.height = 0;
        }else if(dir == Direction.LEFT){
            rect.width = 0;
        }else if(dir == Direction.RIGHT){
            rect.x += rect.width;
            rect.width = 0;
        }


        ArrayList<Rectangle> collisionRectangles = this.getCollisionRectangles(0, 0, mapWidth, mapHeight);
        for(Rectangle collisionRectangle : collisionRectangles){
            if(collisionRectangle.overlaps(rect)){
                return collisionRectangle;
            }
        }

        return null;
    }

    private ArrayList<Rectangle> getCollisionRectangles (int startX, int startY, int width, int height) {
        ArrayList<Rectangle> collisionRectangles = new ArrayList<Rectangle>();

        for (MapLayer mapLayer : map.getLayers()) {
            if (!(mapLayer instanceof TiledMapTileLayer)) {
                continue;
            }
            TiledMapTileLayer layer = (TiledMapTileLayer) mapLayer;

            for (int y = 0; y <= layer.getHeight(); y++) {
                for (int x = 0; x <= layer.getWidth(); x++) {
                    TiledMapTileLayer.Cell cell = layer.getCell(x, y);
                    if (cell == null) {
                        continue;
                    }

                    TiledMapTile tile = cell.getTile();
                    MapObjects tileObjects = tile.getObjects();
                    for (MapObject tileObject : tileObjects) {
                        if (tileObject instanceof RectangleMapObject) {
                            Rectangle tileRect = ((RectangleMapObject) tileObject).getRectangle();

                            collisionRectangles.add(new Rectangle(
                                    x * layer.getTileWidth() + tileRect.x,
                                    y * layer.getTileHeight() + tileRect.y,
                                    tileRect.width,
                                    tileRect.height));
                        }
                    }
                }
            }

        }

        return collisionRectangles;
    }
}
