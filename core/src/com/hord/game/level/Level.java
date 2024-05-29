package com.hord.game.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.hord.game.items.Box;
import com.hord.game.items.Diamond;
import com.hord.game.items.Flag;
import com.hord.game.main.GameControl;
import com.hord.game.player.Player;
import com.hord.game.player.PlayerRender;
import com.hord.game.support.BodyHelper;
import com.hord.game.support.ControlListener;

import java.util.Arrays;
import java.util.List;

import static com.hord.game.support.Constants.*;
public class Level {
    private OrthographicCamera camera;
    private World world;
    private OrthogonalTiledMapRenderer orthogonalTiledMapRenderer;
    private Box2DDebugRenderer box2DDebugRenderer;
    private Player player;
    private Diamond diamond1;
    private Diamond diamond2;
    private Diamond diamond3;
    private Box box1;
    private Box box2;
    private Flag flag;
    private Background background;



    private LevelHelper levelHelper;
    private boolean diamondCollected;
    private boolean gameActive = true;
    private ControlListener controlListener;

    private Texture endGameTexture;
    private Texture restartGameTexture;
    private Texture endMenuTexture;
    private boolean showEndGameInterface = false;

    private SpriteBatch spriteBatch;
    private GameControl gameControl;


    /**
     * Конструктор класу Level
     * @param camera - камера
     */
    public Level(OrthographicCamera camera, GameControl gameControl) {
        this.camera = camera;
        this.gameControl = gameControl;

        world = new World(new Vector2(0, -9.81f), false);
        levelHelper = new LevelHelper(world);

        background = new Background();
        endGameTexture = new Texture(Gdx.files.internal("exit.png"));
        restartGameTexture = new Texture(Gdx.files.internal("repeat.png"));
        endMenuTexture = new Texture(Gdx.files.internal("EndMenu.png"));


        orthogonalTiledMapRenderer = levelHelper.setupMap("layer");
        orthogonalTiledMapRenderer = levelHelper.setupMap("ground");
        box2DDebugRenderer = new Box2DDebugRenderer(false,false,false,false,false,false);


        player = new Player(864,800,world, camera);
        diamond1 = new Diamond("diamond1",1755,1025,world, camera);
        diamond2 = new Diamond("diamond2",2290,640,world, camera);
        diamond3 = new Diamond("diamond3",2760,1120,world, camera);
        box1 = new Box("box",2120,640,world, camera);
        box2 = new Box("box",1920,640,world, camera);
        flag = new Flag(3276,860,world,camera);

        controlListener = new ControlListener(diamond1.getDiamondRender(),diamond2.getDiamondRender(),diamond3.getDiamondRender(),player.getPlayerController(), flag.getFlagRender());
        this.world.setContactListener(controlListener);

        spriteBatch = new SpriteBatch();
    }

    /**
     * Метод для відображення об'єкта
     */
    public void render(){
        background.render(); // Малюємо фон

        camera.zoom = 0.75f;
        cameraUpdate(Gdx.graphics.getDeltaTime()); // Оновлюємо камеру
        orthogonalTiledMapRenderer.setView(camera);

        box2DDebugRenderer.render(world, camera.combined.scl(PPM));

        flag.render();
        diamond1.render();
        diamond2.render();
        diamond3.render();
        box1.render();
        box2.render();

        orthogonalTiledMapRenderer.render();
        player.render();
        Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0);

        if (showEndGameInterface) {
            // Отримати ширину і висоту вікна
            float screenWidth = Gdx.graphics.getWidth();
            float screenHeight = Gdx.graphics.getHeight();

// Масштаби текстур
            float scale = 6;

// Розрахунок розмірів та координат для текстури "endGame"
            float scaledEndGameWidth = endGameTexture.getWidth() * scale;
            float scaledEndGameHeight = endGameTexture.getHeight() * scale;
            float endGameX = (screenWidth - scaledEndGameWidth) / 2 + 5;
            float endGameY = (screenHeight - scaledEndGameHeight) / 2 - 100;

// Розрахунок розмірів та координат для текстури "restartGame"
            float scaledRestartGameWidth = restartGameTexture.getWidth() * scale;
            float scaledRestartGameHeight = restartGameTexture.getHeight() * scale;
            float restartGameX = (screenWidth - scaledRestartGameWidth) / 2 + 5;
            float restartGameY = endGameY + scaledEndGameHeight + 30; // Додаємо 20 пікселів відступу між кнопками


            float endMenuX = (screenWidth - endMenuTexture.getWidth()*5) / 2;
            float endMenuY = (screenHeight - endMenuTexture.getHeight()*5) / 2;
            float scaledMenuWidth = endMenuTexture.getWidth() * 5;
            float scaledMenuHeight = endMenuTexture.getHeight() * 5;

            spriteBatch.begin();
            spriteBatch.draw(endMenuTexture, endMenuX, endMenuY, scaledMenuWidth, scaledMenuHeight);
            spriteBatch.draw(endGameTexture, endGameX, endGameY, scaledEndGameWidth, scaledEndGameHeight);
            spriteBatch.draw(restartGameTexture, restartGameX, restartGameY, scaledRestartGameWidth, scaledRestartGameHeight);
            spriteBatch.end();

            if (Gdx.input.justTouched()) {
                float touchX = Gdx.input.getX();
                float touchY = screenHeight - Gdx.input.getY(); // Зверніть увагу на інверсію Y
                if (touchX >= restartGameX && touchX <= (restartGameX + scaledEndGameWidth) &&
                        touchY >= restartGameY && touchY <= (restartGameY + scaledEndGameHeight)) {
                    gameControl.restartGame();
                }

                if (touchX >= endGameX && touchX <= (endGameX + scaledEndGameWidth) &&
                        touchY >= endGameY && touchY <= (endGameY + scaledEndGameHeight)) {
                    Gdx.app.exit();
                }
            }





        }


    }


    /**
     * Метод для оновлення об'єкта
     * @param delta - час
     */
    public void update(float delta){
        if (!gameActive) {
            //System.out.println("Flag collected, game should stop now.");
            showEndGameInterface = true;
            return; // Не оновлювати гру, якщо вона зупинена
        }

        world.step(1/60f, 6, 2);
        player.update(delta);
        if (controlListener.isDiamond1Collected()){
            world.destroyBody(diamond1.getDiamond());
            diamond1.setDiamond(null);
            controlListener.setDiamond1Collected(false);
        }
        if (controlListener.isDiamond2Collected()){
            world.destroyBody(diamond2.getDiamond());
            diamond2.setDiamond(null);
            controlListener.setDiamond2Collected(false);
        }
        if (controlListener.isDiamond3Collected()){
            world.destroyBody(diamond3.getDiamond());
            diamond3.setDiamond(null);
            controlListener.setDiamond3Collected(false);
        }

        if (controlListener.isFlagCollected()){
            player.setGameActive(false);
            gameActive = false; // Зупинити гру, коли прапор зібрано
        }

        cameraUpdate(delta);
    }

    /*public OrthogonalTiledMapRenderer setupMap(List<String> layerNames) {
        // Завантаження карти
        tiledMap = new TmxMapLoader().load("maps/Level/level1.tmx");

        // Обробка кожного шару за іменем
        for (String name : layerNames) {
            if (tiledMap.getLayers().get(name) != null) {
                levelHelper.parseMapObjects(tiledMap.getLayers().get(name).getObjects());
            } else {
                System.out.println("Layer " + name + " not found!");
            }
        }

        // Ініціалізація рендерера для карти
        orthogonalTiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        return orthogonalTiledMapRenderer;
    }
*/


    /**
     * Метод для оновлення камери
     * @param delta - час
     */
    public void cameraUpdate(float delta){
        Vector3 position = camera.position;
        position.x = player.getCoordinate().x*PPM;
        position.y = player.getCoordinate().y*PPM;
        camera.position.set(position);
        camera.update();
    }

    public void dispose(){
        box2DDebugRenderer.dispose();
        world.dispose();
        flag.dispose();
        player.dispose();
        diamond1.dispose();
        diamond2.dispose();
        diamond3.dispose();
        box1.dispose();
        box2.dispose();
        background.dispose();
        spriteBatch.dispose();
        endGameTexture.dispose();
        restartGameTexture.dispose();
    }
}
