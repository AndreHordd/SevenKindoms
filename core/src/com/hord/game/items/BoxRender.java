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

public class BoxRender {
    private World world;
    private Body box;
    private OrthographicCamera camera;
    private SpriteBatch batch = new SpriteBatch();
    private Animation flagAnimation;

    private TextureAtlas AtlasFlag = new TextureAtlas(Gdx.files.internal(BoxTexture));

    /**
     * Конструктор класу BoxRender
     * @param box - об'єкт
     * @param world - світ
     * @param camera - камера
     */
    public BoxRender(Body box, World world, OrthographicCamera camera){
        this.box = box;
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
        TextureRegion flagFrames =  AtlasFlag.findRegion("box-01");
        flagAnimation = new Animation<TextureRegion>(1/7f, flagFrames);
    }

    /**
     * Метод для відображення текстури
     */
    private void drawTexture() {
        TextureRegion flagAnimationTexture = (TextureRegion) flagAnimation.getKeyFrame(1, true);

        batch.draw(flagAnimationTexture, box.getPosition().x-(BOX_WIGHT)/2*1.2f/PPM, box.getPosition().y-(BOX_HEIGHT)/2*1.25f/PPM,BOX_WIGHT*1.25f/PPM,BOX_HEIGHT*1.25f/PPM);
    }

    /**
     * Метод для видалення об'єкта
     */
    public void dispose(){
        batch.dispose();
    }

}
