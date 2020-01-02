package constants;

import input.Battlefield;

/**
 * Constants used by the Pyromancer Players.
 */
public final class PyromancerConstants {

    private static PyromancerConstants instance = null;

    private PyromancerConstants() { }
    public static PyromancerConstants getInstance() {
        if (instance == null) {
            instance = new PyromancerConstants();
        }
        return instance;
    }

    public static final int BASE_HP = 500;
    public static final int LEVEL_HP = 50;

    //FIREBLAST
    public static final int FIREBLAST_BASE_DAMAGE = 350;
    public static final int FIREBLAST_LEVEL_DAMAGE = 50;
    public static final float FIREBLAST_MOD_R = 0.8f;
    public static final float FIREBLAST_MOD_K = 1.2f;
    public static final float FIREBLAST_MOD_P = 0.9f;
    public static final float FIREBLAST_MOD_W = 1.05f;

    //IGNITE
    public static final int IGNITE_BASE_DAMAGE = 150;
    public static final int IGNITE_LEVEL_DAMAGE = 20;
    public static final int IGNITE_SMALL_BASE_DAMAGE = 50;
    public static final int IGNITE_SMALL_LEVEL_DAMAGE = 30;
    public static final float IGNITE_MOD_R = 0.8f;
    public static final float IGNITE_MOD_K = 1.2f;
    public static final float IGNITE_MOD_P = 0.9f;
    public static final float IGNITE_MOD_W = 1.05f;

    //LAND
    public static final char LAND_TYPE = 'V';
    public static final float LAND_TYPE_BONUS = 1.25f;

    //STRATEGY
    public static final float LOW_LIMIT_FRAC = 1/4f;
    public static final float UP_LIMIT_FRAC = 1/3f;
    public static final float ATTACK_HP_FRAC = 1/4f;
    public static final float ATTACK_COEF = 0.7f;
    public static final float DEFENSE_HP_FRAC = 1/3f;
    public static final float DEFENSE_COEF = 0.3f;;

}
