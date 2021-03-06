package constants;

/**
 * Constants used by the Rogue Players.
 */
public final class RogueConstants {

    private RogueConstants() { }

    public static final int BASE_HP = 600;
    public static final int LEVEL_HP = 40;

    //BACKSTAB
    public static final int BACKSTAB_BASE_DAMAGE = 200;
    public static final int BACKSTAB_LEVEL_DAMAGE = 20;
    public static final int BACKSTAB_NR_HITS = 3;
    public static final Float BACKSTAB_CRITICAL = 1.5f;
    public static final Float BACKSTAB_MOD_R = 1.2f;
    public static final Float BACKSTAB_MOD_K = 0.9f;
    public static final Float BACKSTAB_MOD_P = 1.25f;
    public static final Float BACKSTAB_MOD_W = 1.25f;

    //PARALYSIS
    public static final int PARALYSIS_BASE_DAMAGE = 40;
    public static final int PARALYSIS_LEVEL_DAMAGE = 10;
    public static final int PARALYSIS_OVERTIME = 3;
    public static final int PARALYSIS_OVERTIME_WOODS = 6;
    public static final Float PARALYSIS_MOD_R = 0.9f;
    public static final Float PARALYSIS_MOD_K = 0.8f;
    public static final Float PARALYSIS_MOD_P = 1.2f;
    public static final Float PARALYSIS_MOD_W = 1.25f;

    //LAND
    public static final char LAND_TYPE = 'W';
    public static final Float LAND_TYPE_BONUS = 1.15f;

    //STRATEGY
    public static final Float LOW_LIMIT_FRAC = 1 / 7f;
    public static final Float UP_LIMIT_FRAC = 0.2f;
    public static final Float ATTACK_HP_FRAC = 1 / 7f;
    public static final Float ATTACK_COEF = 0.4f;
    public static final Float DEFENSE_HP_FRAC = 0.5f;
    public static final Float DEFENSE_COEF = 0.1f;

}
