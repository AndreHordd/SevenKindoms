package com.hord.game.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.hord.game.support.Constants.*;

public class PlayerRender {
    private TextureAtlas AtlasRunning = new TextureAtlas(Gdx.files.internal(RunSword));
    private TextureAtlas AtlasRunningLeft = new TextureAtlas(Gdx.files.internal(RunSword));
    private TextureAtlas AtlasIdle = new TextureAtlas(Gdx.files.internal(IdleSword));
    private TextureAtlas AtlasIdleLeft = new TextureAtlas(Gdx.files.internal(IdleSword));
    private TextureAtlas AtlasAttack = new TextureAtlas(Gdx.files.internal(Attack));
    private TextureAtlas AtlasAttackLeft = new TextureAtlas(Gdx.files.internal(Attack));
    private Animation idleAnimation;
    private Animation runningAnimation;
    private Animation attackAnimation;
    private Animation idleAnimationLeft;
    private Animation runningAnimationLeft;
    private Animation attackAnimationLeft;

    private SpriteBatch batch = new SpriteBatch();

    private boolean moving = false;
    private boolean attacking = false;
    private boolean rightDirection = true;
    float stateTime = 0;

    private World world;
    private Body player;
    private OrthographicCamera camera;

    /**
     * Конструктор класу PlayerRender
     * @param player - об'єкт
     * @param world - світ
     * @param camera - камера
     */
    public PlayerRender(Body player, World world, OrthographicCamera camera){
        this.player = player;
        this.world = world;
        this.camera = camera;
        setTexture();
    }

    /**
     * Метод для відображення об'єкта
     */
    public void render(){
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawTexture();
        batch.end();
    }

    /**
     * Метод для встановлення текстури
     */
    private void setTexture(){
        TextureRegion[] idleFrames = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            idleFrames[i] = AtlasIdle.findRegion("IdleSword-0" + (i + 1));
        }

        TextureRegion[] idleFramesLeft = new TextureRegion[4];
        for (int i = 0; i < 4; i++) {
            idleFramesLeft[i] = AtlasIdleLeft.findRegion("IdleSword-0" + (i + 1));
            idleFramesLeft[i].flip(true,false);
        }

        TextureRegion[] runningFrames = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            runningFrames[i] = AtlasRunning.findRegion("RunSword-0" + (i + 1));
        }
        TextureRegion[] runningFramesLeft = new TextureRegion[5];
        for (int i = 0; i < 5; i++) {
            runningFramesLeft[i] = AtlasRunningLeft.findRegion("RunSword-0" + (i + 1));
            runningFramesLeft[i].flip(true,false);
        }

        TextureRegion[] attackFrames = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            attackFrames[i] = AtlasAttack.findRegion("Attack-0" + (i + 1));
        }
        TextureRegion[] attackFramesLeft = new TextureRegion[2];
        for (int i = 0; i < 2; i++) {
            attackFramesLeft[i] = AtlasAttackLeft.findRegion("Attack-0" + (i + 1));
            attackFramesLeft[i].flip(true,false);
        }

        idleAnimation = new Animation<TextureRegion>(1/10f, idleFrames);
        idleAnimationLeft = new Animation<TextureRegion>(1/10f, idleFramesLeft);

        runningAnimation = new Animation<TextureRegion>(1/10f, runningFrames);
        runningAnimationLeft = new Animation<TextureRegion>(1/10f, runningFramesLeft);

        attackAnimation = new Animation<TextureRegion>(1/7f, attackFrames);
        attackAnimationLeft = new Animation<TextureRegion>(1/7f, attackFramesLeft);

    }

    /**
     * Метод для відображення текстури
     */


    private void drawTexture(){

        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion idleAnimationTexture = (TextureRegion) idleAnimation.getKeyFrame(stateTime, true);
        TextureRegion idleAnimationTextureLeft = (TextureRegion) idleAnimationLeft.getKeyFrame(stateTime, true);

        TextureRegion runningAnimationTexture = (TextureRegion) runningAnimation.getKeyFrame(stateTime, true);
        TextureRegion runningAnimationTextureLeft = (TextureRegion) runningAnimationLeft.getKeyFrame(stateTime, true);

        TextureRegion attackAnimationTexture = (TextureRegion) attackAnimation.getKeyFrame(stateTime, true);
        TextureRegion attackAnimationTextureLeft = (TextureRegion) attackAnimationLeft.getKeyFrame(stateTime, true);

        if (isAttacking()){
            if (isRightDirection()){
                batch.draw(attackAnimationTexture,player.getPosition().x-(PLAYER_WIGHT_SCALE)/2/PPM,player.getPosition().y-(PLAYER_HEIGHT_SCALE)/2/PPM,PLAYER_WIGHT_SCALE/PPM,PLAYER_HEIGHT_SCALE/PPM);
            }else{
                batch.draw(attackAnimationTextureLeft,player.getPosition().x-(PLAYER_WIGHT_SCALE)/2/PPM,player.getPosition().y-(PLAYER_HEIGHT_SCALE)/2/PPM,PLAYER_WIGHT_SCALE/PPM,PLAYER_HEIGHT_SCALE/PPM);
            }
        }else if (isMoving()){
            if (isRightDirection()){
                batch.draw(runningAnimationTexture,player.getPosition().x-(PLAYER_WIGHT_SCALE)/2/PPM,player.getPosition().y-(PLAYER_HEIGHT_SCALE)/2/PPM,PLAYER_WIGHT_SCALE/PPM,PLAYER_HEIGHT_SCALE/PPM);
            }else {
                batch.draw(runningAnimationTextureLeft,player.getPosition().x-(PLAYER_WIGHT_SCALE)/2/PPM,player.getPosition().y-(PLAYER_HEIGHT_SCALE)/2/PPM,PLAYER_WIGHT_SCALE/PPM,PLAYER_HEIGHT_SCALE/PPM);
            }
        } else{
            if (isRightDirection()){
                batch.draw(idleAnimationTexture,player.getPosition().x-(PLAYER_WIGHT_SCALE)/2/PPM,player.getPosition().y-(PLAYER_HEIGHT_SCALE)/2/PPM,PLAYER_WIGHT_SCALE/PPM,PLAYER_HEIGHT_SCALE/PPM);
            }else {
                batch.draw(idleAnimationTextureLeft,player.getPosition().x-(PLAYER_WIGHT_SCALE)/2/PPM,player.getPosition().y-(PLAYER_HEIGHT_SCALE)/2/PPM,PLAYER_WIGHT_SCALE/PPM,PLAYER_HEIGHT_SCALE/PPM);
            }
        }
    }

    /**
     * Метод для видалення об'єкта
     */
    public void dispose(){
        batch.dispose();
    }

    /**
     * Метод для визначення чи гравець рухається
     * @return - чи рухається
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * Метод для визначення чи гравець атакує
     * @return - чи атакує
     */
    public boolean isAttacking() {
        return attacking;
    }

    /**
     * Метод для встановлення руху гравця
     * @param moving - чи рухається
     */
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    /**
     * Метод для встановлення атаки гравця
     * @param attacking - чи атакує
     */
    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    /**
     * Метод для визначення чи гравець рухається вправо
     * @return - чи рухається вправо
     */
    public boolean isRightDirection() {
        return rightDirection;
    }

    /**
     * Метод для встановлення руху гравця вправо
     * @param rightDirection - чи рухається вправо
     */
    public void setRightDirection(boolean rightDirection) {
        this.rightDirection = rightDirection;
    }
}
