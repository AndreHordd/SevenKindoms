package com.hord.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hord.game.support.Constants;

public class Background {
    private SpriteBatch staticBatch;
    private Texture texture;

    public Background() {
        staticBatch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("image.png"));
    }

    public void render() {
        staticBatch.begin();
        staticBatch.draw(texture, 0, 0, Constants.GAME_WIGHT, Constants.GAME_HEIGHT);
        staticBatch.end();
    }

    public void dispose() {
        staticBatch.dispose();
        texture.dispose();
    }
}
