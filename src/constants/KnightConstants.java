package constants;

import input.Battlefield;

/**
 * Constants used by the Knight Players.
 */
public final class KnightConstants{

    private static KnightConstants instance = null;

    private KnightConstants() { }
    public static KnightConstants getInstance() {
        if (instance == null) {
            instance = new KnightConstants();
        }
        return instance;
    }

    public static final int BASE_HP = 900;
    public static final int LEVEL_HP = 80;

    //EXECUTE
    public static final int EXECUTE_BASE_DAMAGE = 200;
    public static final int EXECUTE_LEVEL_DAMAGE = 30;
    public static final float EXECUTE_INSTANT_PERCENT = 0.2f;
    public static final float EXECUTE_INSTANT_LEVEL_PERCENT = 0.01f;
    public static final float EXECUTE_INSTANT_LEVEL_MAX_PERCENT = 0.4f;
    public static final float EXECUTE_MOD_R = 1.15f;
    public static final float EXECUTE_MOD_K = 0;
    public static final float EXECUTE_MOD_P = 1.1f;
    public static final float EXECUTE_MOD_W = 0.8f;

    //SLAM
    public static final int SLAM_BASE_DAMAGE = 100;
    public static final int SLAM_LEVEL_DAMAGE = 40;
    public static final float SLAM_MOD_R = 0.8f;
    public static final float SLAM_MOD_K = 1.2f;
    public static final float SLAM_MOD_P = 0.9f;
    public static final float SLAM_MOD_W = 1.05f;

    //LAND
    public static final char LAND_TYPE = 'L';
    public static final float LAND_TYPE_BONUS = 1.15f;

    //STRATEGY
    public static final float LOW_LIMIT_FRAC = 1/3f;
    public static final float UP_LIMIT_FRAC = 1/2f;
    public static final float ATTACK_HP_FRAC = 1/5f;
    public static final float ATTACK_COEF = 0.5f;
    public static final float DEFENSE_HP_FRAC = 1/4f;
    public static final float DEFENSE_COEF = 0.2f;

}
