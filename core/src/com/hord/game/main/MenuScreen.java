package com.hord.game.main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {
    private Stage stage;
    private Game game;
    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private Texture startButtonTexture;
    private Texture texture1, texture2, texture3; // Текстури для анімації
    private boolean isPressed = false;

    public MenuScreen(final Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        spriteBatch = new SpriteBatch();

        backgroundTexture = new Texture(Gdx.files.internal("image.png"));
        startButtonTexture = new Texture(Gdx.files.internal("start1.png"));
        texture1 = new Texture(Gdx.files.internal("start1.png"));
        texture2 = new Texture(Gdx.files.internal("start2.png"));
        texture3 = new Texture(Gdx.files.internal("start3.png"));

        final Image startButtonImage = new Image(startButtonTexture);
        startButtonImage.setSize(startButtonImage.getWidth() * 2, startButtonImage.getHeight() * 2);
        startButtonImage.setPosition(Gdx.graphics.getWidth() / 2 - startButtonImage.getWidth() / 2, Gdx.graphics.getHeight() / 2 - startButtonImage.getHeight() / 2);

        startButtonImage.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                // Розпочати анімацію натискання
                startButtonImage.addAction(Actions.sequence(
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                startButtonImage.setDrawable(new TextureRegionDrawable(texture1));
                            }
                        }),
                        Actions.delay(0.05f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                startButtonImage.setDrawable(new TextureRegionDrawable(texture2));
                            }
                        }),
                        Actions.delay(0.05f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                startButtonImage.setDrawable(new TextureRegionDrawable(texture3));
                            }
                        })
                ));
                return true; // Важливо повернути true, щоб подія продовжила розповсюдження до touchUp
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // Запустити гру після відпускання кнопки
                if (isPressed) {
                    return;
                }
                game.setScreen(new GameScreen(new OrthographicCamera()));
                isPressed = true;
            }
        });


        stage.addActor(startButtonImage);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        backgroundTexture.dispose();
        startButtonTexture.dispose();
        texture1.dispose();
        texture2.dispose();
        texture3.dispose();
        spriteBatch.dispose();
    }

    // Реалізуйте інші методи інтерфейсу Screen, як потрібно
    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
