package com.hord.game.items;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.hord.game.support.BodyHelper;

import static com.hord.game.support.Constants.*;

public class Flag {
    private int flagX;
    private int flagY;
    private World world;
    private Body flag;
    private OrthographicCamera camera;
    private FlagRender flagRender;


    /**
     * Конструктор класу Flag
     * @param x - координата по X
     * @param y - координата по Y
     * @param world - світ
     * @param camera - камера
     */
    public Flag(int x, int y, World world, OrthographicCamera camera){
        this.flagX = x;
        this.flagY = y;
        this.world = world;
        this.camera= camera;

        flag = BodyHelper.createBody("flag",false,flagX,flagY,FLAG_WIGHT,FLAG_HEIGHT,world);
        flagRender = new FlagRender(flag,world,camera);
    }

    /**
     * Метод для відображення об'єкта
     */
    public FlagRender getFlagRender() {
        return flagRender;
    }
    /**
     * Метод для відображення об'єкта
     */
    public Body getFlag() {
        return flag;
    }

    /**
     * Метод для відображення об'єкта
     */
    public void setFlag(Body flag) {
        this.flag = flag;
    }
    /**
     * Метод для відображення об'єкта
     */
    public void render(){
        flagRender.render();
    }
    /**
     * Метод для видалення об'єкта
     */
    public void dispose(){
        flagRender.dispose();
    }

}
