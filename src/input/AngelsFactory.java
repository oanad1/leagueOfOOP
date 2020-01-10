package input;

import angels.AngelVisitor;
import angels.DamageAngel;
import angels.XPAngel;
import angels.LifeGiver;
import angels.DarkAngel;
import angels.Spawner;
import angels.Dracula;
import angels.GoodBoy;
import angels.LevelUpAngel;
import angels.TheDoomer;
import angels.SmallAngel;

/**
 * A factory class used to create instances of Angel objects.
 */
public final class AngelsFactory {

         private AngelsFactory() { }

        public static AngelVisitor getAngel(final String type) {

            switch (type) {
                case "DamageAngel":
                    return DamageAngel.getInstance();

                case "DarkAngel":
                    return DarkAngel.getInstance();

                case "Dracula":
                    return Dracula.getInstance();

                case "GoodBoy":
                    return GoodBoy.getInstance();

                case "LevelUpAngel":
                    return LevelUpAngel.getInstance();

                case "LifeGiver":
                    return LifeGiver.getInstance();

                case "SmallAngel":
                    return SmallAngel.getInstance();

                case "Spawner":
                    return Spawner.getInstance();

                case "TheDoomer":
                    return TheDoomer.getInstance();

                case "XPAngel":
                    return XPAngel.getInstance();

                default:
                    return null;
            }
        }
}
