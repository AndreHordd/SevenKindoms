package com.hord.game.support;

import com.badlogic.gdx.physics.box2d.*;
import com.hord.game.items.DiamondRender;
import com.hord.game.items.FlagRender;
import com.hord.game.player.PlayerController;

public class ControlListener implements ContactListener {
    private PlayerController playerController;
    private DiamondRender diamondRender1;
    private DiamondRender diamondRender2;
    private DiamondRender diamondRender3;
    private boolean diamond1Collected = false;
    private boolean diamond2Collected = false;
    private boolean diamond3Collected = false;
    private boolean flagCollected = false;

    /**
     * Конструктор класу ControlListener
     *
     * @param diamondRender1   - відображення алмазу 1
     * @param diamondRender2   - відображення алмазу 2
     * @param diamondRender3   - відображення алмазу 3
     * @param playerController - контролер гравця
     * @param flagRender
     */
    public ControlListener(DiamondRender diamondRender1, DiamondRender diamondRender2, DiamondRender diamondRender3, PlayerController playerController, FlagRender flagRender){

        this.diamondRender1 = diamondRender1;
        this.diamondRender2 = diamondRender2;
        this.diamondRender3 = diamondRender3;
        this.flagCollected = false;
        this.playerController = playerController;
    }

    /**
     * Метод для визначення зіткнень
     * @param contact - контакт
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixtureA = contact.getFixtureA();
        Fixture fixtureB = contact.getFixtureB();

        System.out.println("Contact: " + fixtureA.getUserData() + " and " + fixtureB.getUserData());

        if (fixtureA == null || fixtureB == null) return;
        if (fixtureA.getUserData() == null || fixtureB.getUserData() == null) return;

        if (fixtureA.getUserData() == "layer" || fixtureB.getUserData() == "layer"){
            playerController.setJumping(false);
        };

        if (fixtureA.getUserData() == "ground" || fixtureB.getUserData() == "ground"){
            playerController.setJumping(true);
        };

        if (fixtureA.getUserData() == "box" || fixtureB.getUserData() == "box"){
            playerController.setJumping(true);
        };

        if (fixtureA.getUserData() == "diamond1" || fixtureB.getUserData() == "diamond1"){
            playerController.setJumping(true);
            diamondRender1.setDiamondEffect(true);
            setDiamond1Collected(true);
        };
        if (fixtureA.getUserData() == "diamond2" || fixtureB.getUserData() == "diamond2"){
            playerController.setJumping(true);
            diamondRender2.setDiamondEffect(true);
            setDiamond2Collected(true);
        };
        if (fixtureA.getUserData() == "diamond3" || fixtureB.getUserData() == "diamond3"){
            playerController.setJumping(true);
            diamondRender3.setDiamondEffect(true);
            setDiamond3Collected(true);
        };

        if (fixtureA.getUserData() == "flag" || fixtureB.getUserData() == "flag"){
            playerController.setJumping(true);
            System.out.println("Contact: " + fixtureA.getUserData() + " with " + fixtureB.getUserData());

            setFlagCollected(true);
        };
        //System.out.println("A collision happened!");
    }

    /**
     * Метод, що визначає чи зібрано алмаз 1
     * @return - чи зібрано
     */
    public boolean isDiamond1Collected() {
        return diamond1Collected;
    }

    /**
     * Метод, що дозволяє встановити чи зібрано алмаз 1
     * @param diamond1Collected - чи зібрано
     */
    public void setDiamond1Collected(boolean diamond1Collected) {
        this.diamond1Collected = diamond1Collected;
    }

    /**
     * Метод, що визначає чи зібрано алмаз 2
     * @return - чи зібрано
     */
    public boolean isDiamond2Collected() {
        return diamond2Collected;
    }

    /**
     * Метод, що дозволяє встановити чи зібрано алмаз 2
     * @param diamond2Collected - чи зібрано
     */
    public void setDiamond2Collected(boolean diamond2Collected) {
        this.diamond2Collected = diamond2Collected;
    }

    /**
     * Метод, що визначає чи зібрано алмаз 3
     * @return - чи зібрано
     */
    public boolean isDiamond3Collected() {
        return diamond3Collected;
    }

    /**
     * Метод, що дозволяє встановити чи зібрано алмаз 3
     * @param diamond3Collected - чи зібрано
     */
    public void setDiamond3Collected(boolean diamond3Collected) {
        this.diamond3Collected = diamond3Collected;
    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    /**
     * Метод, що визначає чи зібрано прапор
     * @return - чи зібрано
     */
    public boolean isFlagCollected() {
        return flagCollected;
    }

    /**
     * Метод, що дозволяє встановити чи зібрано прапор
     * @param flagCollected - чи зібрано
     */
    public void setFlagCollected(boolean flagCollected) {
        this.flagCollected = flagCollected;
    }
}
