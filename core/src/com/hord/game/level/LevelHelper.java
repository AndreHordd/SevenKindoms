package com.hord.game.level;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.List;

import static com.hord.game.support.Constants.*;

public class LevelHelper {
    private TiledMap tiledMap;
    private World world;
    private String name;


    /**
     * Конструктор класу LevelHelper
     * @param world - світ
     */
    public LevelHelper(World world){
        this.world = world;
    }

    /**
     * Метод для конфігурації карти
     */
    public OrthogonalTiledMapRenderer setupMap(String name){
        this.name = name;
        tiledMap = new TmxMapLoader().load("maps/Level/level1.tmx");
        parseMapObjects(tiledMap.getLayers().get(name).getObjects());
        return new OrthogonalTiledMapRenderer(tiledMap);
    }


    /**
     * Метод для парсингу об'єктів карти
     */
    public void parseMapObjects(MapObjects mapObjects){
        for (MapObject mapObject: mapObjects){
            if (mapObject instanceof PolygonMapObject){
                createStaticBody((PolygonMapObject) mapObject);
            }
        }
    }

    /**
     * Метод для створення статичного тіла
     */
    private void createStaticBody(PolygonMapObject polygonMapObject){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        Body body = world.createBody(bodyDef);
        Shape shape = createPolygonShape(polygonMapObject);
        body.createFixture(shape,1000).setUserData(name);
        shape.dispose();

    }

    /**
     * Метод для створення форми тіла
     */
    private Shape createPolygonShape(PolygonMapObject polygonMapObject) {
        float[] vertices = polygonMapObject.getPolygon().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length / 2];

        for(int i = 0; i < vertices.length/2; i++){
            Vector2 current = new Vector2(vertices[i*2]/PPM,vertices[i*2 + 1]/PPM);
            worldVertices[i] = current;
        }
        PolygonShape shape = new PolygonShape();
        shape.set(worldVertices);
        return shape;

    }

}
