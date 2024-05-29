package com.hord.game.items;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.hord.game.support.BodyHelper;

import static com.hord.game.support.Constants.*;

public class Box {
    private int boxX;
    private int boxY;
    private World world;
    private Body box;
    private OrthographicCamera camera;
    private BoxRender boxRender;
    private String name;

    /**
     * Конструктор класса Box
     * @param name - ім'я об'єкта
     * @param x - координата по X
     * @param y - координата по Y
     * @param world - світ
     * @param camera - камера
     */
    public Box(String name, int x, int y, World world, OrthographicCamera camera){
        this.name = name;
        this.boxX = x;
        this.boxY = y;
        this.world = world;
        this.camera= camera;

        box = BodyHelper.createBody(name,true, boxX, boxY,BOX_WIGHT,BOX_HEIGHT,world);
        boxRender = new BoxRender(box,world,camera);
    }

    /**
     * Метод для відображення об'єкта
     */
    public void render(){
        boxRender.render();
    }

    /**
     * Метод для видалення об'єкта
     */
    public void dispose(){
        boxRender.dispose();
    }

}
