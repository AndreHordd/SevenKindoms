package com.hord.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hord.game.level.Level;
import static com.hord.game.support.Constants.*;

public class GameScreen implements Screen, GameControl {
    private OrthographicCamera camera;
    private Level level;

    /**
     * Конструктор класса GameScreen
     * @param camera - камера
     */
    public GameScreen(OrthographicCamera camera){
        this.camera = camera;
        camera.setToOrtho(false,camera.viewportWidth/PPM,camera.viewportHeight/PPM);
        ScreenUtils.clear(1, 0, 0, 1, true);

    }

    @Override
    public void restartGame() {
        // Приклад перезавантаження рівня або створення нового екземпляру Level
        level = new Level(camera, this);
    }

    /**
     * Метод для показу об'єкта
     */
    @Override
    public void show() {
        level = new Level(camera, this);

    }

    /**
     * Загальний метод для відображення об'єкта
     */
    @Override
    public void render(float delta) {
        update(delta);
        level.render();

    }

    /**
     * Метод для оновлення об'єкта
     * @param delta - час
     */
    public void update(float delta){
        level.update(delta);
    }

    /**
     * Метод для встановлення розміру камери
     */
    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        level.dispose();
    }
}
