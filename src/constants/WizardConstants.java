package constants;


/**
 * Constants used by the Wizard Players.
 */
public final class WizardConstants {

    private WizardConstants() { }

    public static final int BASE_HP = 400;
    public static final int LEVEL_HP = 30;

    //DRAIN
    public static final Float DRAIN_BASE_PROCENT = 0.2f;
    public static final Float DRAIN_LEVEL_PROCENT = 0.05f;
    public static final Float DRAIN_OPP_MAX_PROCENT = 0.3f;
    public static final Float DRAIN_MOD_R = 0.8f;
    public static final Float DRAIN_MOD_K = 1.2f;
    public static final Float DRAIN_MOD_P = 0.9f;
    public static final Float DRAIN_MOD_W = 1.05f;

    //DEFLECT
    public static final Float DEFLECT_BASE_PROCENT = 0.35f;
    public static final Float DEFLECT_LEVEL_PROCENT = 0.02f;
    public static final Float DEFLECT_MAX_PROCENT = 0.7f;
    public static final Float DEFLECT_MOD_R = 1.2f;
    public static final Float DEFLECT_MOD_K = 1.4f;
    public static final Float DEFLECT_MOD_P = 1.3f;
    public static final Float DEFLECT_MOD_W = 0f;

    //LAND
    public static final char LAND_TYPE = 'D';
    public static final Float LAND_TYPE_BONUS = 1.1f;


    //STRATEGY
    public static final Float LOW_LIMIT_FRAC = 0.25f;
    public static final Float UP_LIMIT_FRAC = 0.5f;
    public static final Float ATTACK_HP_FRAC = 0.1f;
    public static final Float ATTACK_COEF = 0.6f;
    public static final Float DEFENSE_HP_FRAC = 0.2f;
    public static final Float DEFENSE_COEF = 0.2f;
}
