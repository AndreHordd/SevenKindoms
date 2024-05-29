package com.hord.game.player;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.hord.game.support.BodyHelper;
import com.hord.game.support.ControlListener;

import static com.hord.game.support.Constants.*;

public class Player {
    public PlayerRender getPlayerRender;
    private int playerX;
    private int playerY;
    private World world;
    private OrthographicCamera camera;
    private PlayerRender playerRender;
    private PlayerController playerController;
    private Body player;

    /**
     * Конструктор класу Player
     * @param x - координата по X
     * @param y - координата по Y
     * @param world - світ
     * @param camera - камера
     */
    public Player(int x, int y, World world, OrthographicCamera camera){
        this.playerX = x;
        this.playerY = y;
        this.world = world;
        this.camera = camera;

        player = BodyHelper.createBody("player",true,playerX,playerY,PLAYER_WIGHT_HITBOX,PLAYER_HEIGHT_HITBOX,world);
        playerRender = new PlayerRender(player,world,camera);
        playerController = new PlayerController(player,playerRender,world);
        //=this.world.setContactListener(new ControlListener(playerRender,playerController));
    }

    /**
     *  Метод, що видає координати гравця
     * @return - координати
     */
    public Vector2 getCoordinate(){
        return player.getPosition();
    }

    /**
     * Метод, що видає об'єкт гравця
     * @return - об'єкт
     */
    public PlayerRender getPlayerRender() {
        return playerRender;
    }

    /**
     * Метод, що видає об'єкт гравця
     * @return - об'єкт
     */
    public PlayerController getPlayerController() {
        return playerController;
    }

    private boolean gameActive = true; // Додайте цю змінну

    // Додайте сеттер
    public void setGameActive(boolean gameActive) {
        this.gameActive = gameActive;
    }
    /**
     * Метод, що видає об'єкт гравця
     * @return - об'єкт
     */
    public void render(){
        if (gameActive)
            playerRender.render();

    }

    /**
     * Метод, що видає об'єкт гравця
     * @return - об'єкт
     */
    public void update(float delta){
        playerController.update(delta);
    }

    /**
     * Метод, що видає об'єкт гравця
     * @return - об'єкт
     */
    public void dispose(){
        playerRender.dispose();
    }
}
