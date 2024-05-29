package com.hord.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import static com.hord.game.support.Constants.*;
import static com.hord.game.support.Constants.PPM;

public class FlagRender {
    private World world;
    private Body flag;
    private OrthographicCamera camera;
    private SpriteBatch batch = new SpriteBatch();
    private float stateTime = 0;
    private TextureAtlas AtlasFlag = new TextureAtlas(Gdx.files.internal(FlagTexture));
    private Animation flagAnimation;

    /**
     * Конструктор класу FlagRender
     * @param flag - об'єкт
     * @param world - світ
     * @param camera - камера
     */
    public FlagRender(Body flag, World world, OrthographicCamera camera){
        this.flag = flag;
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
    private void setTexture() {
        TextureRegion[] flagFrames = new TextureRegion[8];
        for (int i = 0; i < 8; i++) {
            flagFrames[i] = AtlasFlag.findRegion("Flag-0" + (i + 1));
        }

        flagAnimation = new Animation<TextureRegion>(1/7f, flagFrames);

    }

    /**
     * Метод для відображення текстури
     */
    private void drawTexture() {
        stateTime += Gdx.graphics.getDeltaTime();
        //TextureRegion diamondEffectAnimationTexture = (TextureRegion) diamondEffectAnimation.getKeyFrame(stateTime);;

        TextureRegion flagAnimationTexture = (TextureRegion) flagAnimation.getKeyFrame(stateTime, true);
        batch.draw(flagAnimationTexture, flag.getPosition().x-(FLAG_WIGHT)/2/PPM, flag.getPosition().y-(FLAG_HEIGHT)/2/PPM,FLAG_WIGHT/PPM,FLAG_HEIGHT/PPM);

    }

    /**
     * Метод для видалення об'єкта
     */
    public void dispose(){
        batch.dispose();
    }

}
