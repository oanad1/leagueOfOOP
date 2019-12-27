package input;

import angels.*;
import players.*;

public class AngelsFactory {

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
