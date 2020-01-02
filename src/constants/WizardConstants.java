package constants;

import input.Battlefield;

/**
 * Constants used by the Wizard Players.
 */
public final class WizardConstants {

    private static WizardConstants instance = null;

    private WizardConstants() { }
    public static WizardConstants getInstance() {
        if (instance == null) {
            instance = new WizardConstants();
        }
        return instance;
    }

    public static final int BASE_HP = 400;
    public static final int LEVEL_HP = 30;

    //DRAIN
    public static final float DRAIN_BASE_PROCENT = 0.2f;
    public static final float DRAIN_LEVEL_PROCENT = 0.05f;
    public static final float DRAIN_OPP_MAX_PROCENT = 0.3f;
    public static final float DRAIN_MOD_R = 0.8f;
    public static final float DRAIN_MOD_K = 1.2f;
    public static final float DRAIN_MOD_P = 0.9f;
    public static final float DRAIN_MOD_W = 1.05f;

    //DEFLECT
    public static final float DEFLECT_BASE_PROCENT = 0.35f;
    public static final float DEFLECT_LEVEL_PROCENT = 0.02f;
    public static final float DEFLECT_MAX_PROCENT = 0.7f;
    public static final float DEFLECT_MOD_R = 1.2f;
    public static final float DEFLECT_MOD_K = 1.4f;
    public static final float DEFLECT_MOD_P = 1.3f;
    public static final float DEFLECT_MOD_W = 0;

    //LAND
    public static final char LAND_TYPE = 'D';
    public static final float LAND_TYPE_BONUS = 1.1f;


    //STRATEGY
    public static final float LOW_LIMIT_FRAC = 1/4f;
    public static final float UP_LIMIT_FRAC = 1/2f;
    public static final float ATTACK_HP_FRAC = 1/10f;
    public static final float ATTACK_COEF = 0.6f;
    public static final float DEFENSE_HP_FRAC = 1/5f;
    public static final float DEFENSE_COEF = 0.2f;
}
