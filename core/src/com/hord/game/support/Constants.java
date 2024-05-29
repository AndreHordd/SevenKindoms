package com.hord.game.support;

public class Constants {
    public static final int GAME_BLOCK_SIZE = 48;
    public static final int GAME_BLOCS_WIGHT = 26;
    public static final int GAME_BLOCS_HEIGHT = 14;
    public static final int GAME_WIGHT = GAME_BLOCS_WIGHT * GAME_BLOCK_SIZE;
    public static final int GAME_HEIGHT = GAME_BLOCS_HEIGHT * GAME_BLOCK_SIZE;
    public static final float PPM = 32f;


    public static final int PLAYER_WIGHT = 64;
    public static final int PLAYER_HEIGHT = 40;
    public static final float SCALE = 2.f;
    public static final int PLAYER_WIGHT_SCALE = (int) (PLAYER_WIGHT * SCALE);
    public static final int PLAYER_HEIGHT_SCALE = (int) (PLAYER_HEIGHT * SCALE);
    public static final int PLAYER_WIGHT_HITBOX = (int) (16 * SCALE);
    public static final int PLAYER_HEIGHT_HITBOX = (int) (23 * SCALE);


    public static final String RunSword = "SpriteSheets/RunSword.atlas";
    public static final String IdleSword = "SpriteSheets/IdleSword.atlas";
    public static final String Attack = "SpriteSheets/Attack.atlas";

    public static final String DiamondTexture = "SpriteSheets/Diamond.atlas";
    public static final String DiamondEffectTexture = "SpriteSheets/DiamondEffect.atlas";

    public static final String FlagTexture = "SpriteSheets/Flag.atlas";

    public static final String BoxTexture = "SpriteSheets/Box.atlas";
    public static final String GameOverTexture = "SpriteSheets/GameOver.atlas";



    public static final int LEFT = -5;
    public static final int RIGHT = 5;

    public static final int DIAMOND_WIGHT = 24;
    public static final int DIAMOND_HEIGHT = 24;

    public static final int FLAG_WIGHT = 34*2;
    public static final int FLAG_HEIGHT = 93*2;

    public static final int BOX_WIGHT = 84;
    public static final int BOX_HEIGHT = 66;

    public static final int GAME_OVER = 360;
}


