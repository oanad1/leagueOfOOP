package constants;


/**
 * Constants used by the Pyromancer Players.
 */
public final class PyromancerConstants {

    private PyromancerConstants() { }

    public static final int BASE_HP = 500;
    public static final int LEVEL_HP = 50;

    //FIREBLAST
    public static final int FIREBLAST_BASE_DAMAGE = 350;
    public static final int FIREBLAST_LEVEL_DAMAGE = 50;
    public static final Float FIREBLAST_MOD_R = 0.8f;
    public static final Float FIREBLAST_MOD_K = 1.2f;
    public static final Float FIREBLAST_MOD_P = 0.9f;
    public static final Float FIREBLAST_MOD_W = 1.05f;

    //IGNITE
    public static final int IGNITE_BASE_DAMAGE = 150;
    public static final int IGNITE_LEVEL_DAMAGE = 20;
    public static final int IGNITE_SMALL_BASE_DAMAGE = 50;
    public static final int IGNITE_SMALL_LEVEL_DAMAGE = 30;
    public static final Float IGNITE_MOD_R = 0.8f;
    public static final Float IGNITE_MOD_K = 1.2f;
    public static final Float IGNITE_MOD_P = 0.9f;
    public static final Float IGNITE_MOD_W = 1.05f;

    //LAND
    public static final char LAND_TYPE = 'V';
    public static final Float LAND_TYPE_BONUS = 1.25f;

    //STRATEGY
    public static final Float LOW_LIMIT_FRAC = 0.25f;
    public static final Float UP_LIMIT_FRAC = 1 / 3f;
    public static final Float ATTACK_HP_FRAC = 0.25f;
    public static final Float ATTACK_COEF = 0.7f;
    public static final Float DEFENSE_HP_FRAC = 0.33f;
    public static final Float DEFENSE_COEF = 0.3f;;

}
