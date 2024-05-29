package com.hord.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.hord.game.support.Constants.*;

public class PlayerController {
    private Body player;
    private PlayerRender playerRender;
    private World world;
    private boolean jumping = false;

    /**
     * Конструктор класу PlayerController
     * @param player - об'єкт
     * @param playerRender - відображення об'єкта
     * @param world - світ
     */
    public PlayerController(Body player, PlayerRender playerRender, World world){
        this.player = player;
        this.playerRender = playerRender;
        this.world = world;
    }

    /**
     * Метод для визначення дій гравця
     * @param delta - час
     */
    private void inputUpdate(float delta) {
        int horizontalForce = 0;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            horizontalForce += LEFT;
            playerRender.setRightDirection(false);
            playerRender.setMoving(true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            horizontalForce += RIGHT;
            playerRender.setRightDirection(true);
            playerRender.setMoving(true);
        }

        if (isJumping()){
            if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                float JUMP = 8f;
                float impulse = player.getMass() * JUMP;
                player.applyLinearImpulse(0, impulse, player.getPosition().x, player.getPosition().y, true);
                setJumping(false);
            }
        }

        player.setLinearVelocity(horizontalForce, player.getLinearVelocity().y);

        if (Gdx.input.isTouched()) {
            playerRender.setAttacking(true);
        } else {
            playerRender.setAttacking(false);
        }

        if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
            playerRender.setMoving(false);
        }
    }

    /**
     * Метод для визначення дій гравця
     * @param delta - час
     */
    public void update(float delta){
        inputUpdate(delta);
    }

    /**
     * Метод для визначення дій гравця
     * @return - чи стрибає гравець
     */
    public boolean isJumping() {
        return jumping;
    }

    /**
     * Метод для встановлення стрибка гравця
     * @param jumping - чи стрибає гравець
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

}
