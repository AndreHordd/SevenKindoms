package com.hord.game.items;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.hord.game.support.BodyHelper;

import static com.hord.game.support.Constants.*;

public class Diamond {
    private int diamondX;
    private int diamondY;
    private World world;

    /**
     * Метод для відображення об'єкта
     */
    public Body getDiamond() {
        return diamond;
    }

    /**
     * Метод для відображення об'єкта
     */
    public void setDiamond(Body diamond) {
        this.diamond = diamond;
    }

    private Body diamond;
    private OrthographicCamera camera;
    private DiamondRender diamondRender;
    private String name;


    /**
     * Конструктор класса Diamond
     * @param name - ім'я об'єкта
     * @param x - координата по X
     * @param y - координата по Y
     * @param world - світ
     * @param camera - камера
     */
    public Diamond(String name, int x, int y, World world, OrthographicCamera camera){
        this.name = name;
        this.diamondX = x;
        this.diamondY = y;
        this.world = world;
        this.camera= camera;

        diamond = BodyHelper.createBody(name,false,diamondX,diamondY,DIAMOND_WIGHT,DIAMOND_HEIGHT,world);
        diamondRender = new DiamondRender(diamond,world,camera);
    }
    /**
     * Метод для відображення об'єкта
     */
    public DiamondRender getDiamondRender() {
        return diamondRender;
    }
    /**
     * Метод для відображення об'єкта
     */
    public void render(){
        diamondRender.render();
    }
    /**
     * Метод для видалення об'єкта
     */
    public void dispose(){
        diamondRender.dispose();
    }

}
