package com.hord.game.support;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;

import static com.hord.game.support.Constants.*;

public class BodyHelper {
    /**
     * Метод для створення об'єкту
     * @param name - назва об'єкту
     * @param bodyType - тип об'єкту
     * @param x - координата по X
     * @param y - координата по Y
     * @param width - ширина
     * @param height - висота
     * @param world - світ
     * @return - об'єкт
     */
    public static Body createBody(String name,boolean bodyType, float x, float y, float width, float height, World world) {
        Body body;
        BodyDef bodyDef = new BodyDef();
        if (bodyType)
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        else
            bodyDef.type = BodyDef.BodyType.StaticBody;

        bodyDef.position.set(x/PPM, y/PPM);
        bodyDef.fixedRotation = true;
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2/PPM, height / 2/PPM);

        body.createFixture(shape,1).setUserData(name);
        shape.dispose();
        return body;
    }

    /**
     * Метод для видалення об'єкту
     * @param body - об'єкт
     */
    public static void removeFixtureFromBody(Body body) {
        Array<Fixture> fixtures = body.getFixtureList();
        for (Fixture fixture : fixtures) {
            body.destroyFixture(fixture);
        }
    }

}
