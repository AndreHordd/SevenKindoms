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

public class DiamondRender {
    private World world;
    private Body diamond;
    private OrthographicCamera camera;
    private SpriteBatch batch = new SpriteBatch();
    private float stateTime = 0;
    private TextureAtlas AtlasDiamond = new TextureAtlas(Gdx.files.internal(DiamondTexture));
    private TextureAtlas AtlasDiamondEffect = new TextureAtlas(Gdx.files.internal(DiamondEffectTexture));
    private Animation diamondAnimation;
    private Animation diamondEffectAnimation;
    private boolean diamondEffect = false;
    private boolean diamondLive = true;

    /**
     * Конструктор класу DiamondRender
     * @param diamond - об'єкт
     * @param world - світ
     * @param camera - камера
     */
    public DiamondRender(Body diamond,World world,OrthographicCamera camera){
        this.diamond = diamond;
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
        TextureRegion[] diamondFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            diamondFrames[i] = AtlasDiamond.findRegion("0" + (i + 1));
        }
        TextureRegion[] diamondEffectFrames = new TextureRegion[3];
        for (int i = 0; i < 3; i++) {
            diamondEffectFrames[i] = AtlasDiamondEffect.findRegion("0" + (i + 1));
        }

        diamondAnimation = new Animation<TextureRegion>(1/7f, diamondFrames);
        diamondEffectAnimation = new Animation<TextureRegion>(1/5f, diamondEffectFrames);
        diamondEffectAnimation.setPlayMode(Animation.PlayMode.NORMAL);

    }

    private boolean effectAnimationStarted = false;
    /**
     * Метод для відображення текстури
     */
    private void drawTexture() {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion diamondAnimationTexture = (TextureRegion) diamondAnimation.getKeyFrame(stateTime, true);


        if (diamondEffect){
            diamondLive=false;
            if (!effectAnimationStarted) {
                effectAnimationStarted = true;
                stateTime = 0;
            }

            TextureRegion diamondEffectAnimationTexture = (TextureRegion) diamondEffectAnimation.getKeyFrame(stateTime, false);
            batch.draw(diamondEffectAnimationTexture, diamond.getPosition().x - (DIAMOND_WIGHT) / PPM, diamond.getPosition().y - (DIAMOND_HEIGHT) / PPM, DIAMOND_WIGHT * 2 / PPM, DIAMOND_HEIGHT * 2 / PPM);

            if (diamondEffectAnimation.isAnimationFinished(stateTime)) {
                diamondEffect=false;
            }
        }
        if (diamondLive)
            batch.draw(diamondAnimationTexture,diamond.getPosition().x-(DIAMOND_WIGHT)/PPM,diamond.getPosition().y-(DIAMOND_HEIGHT)/PPM,DIAMOND_WIGHT*2/PPM,DIAMOND_HEIGHT*2/PPM);

    }

    /**
     * Метод для видалення об'єкта
     */
    public void dispose(){
        batch.dispose();
    }

    /**
     * Метод для відображення об'єкта
     */
    public boolean isDiamondEffect() {
        return diamondEffect;
    }

    /**
     * Метод для встановлення ефекту
     * @param diamondEffect - ефект
     */
    public void setDiamondEffect(boolean diamondEffect) {
        this.diamondEffect = diamondEffect;
    }
}
