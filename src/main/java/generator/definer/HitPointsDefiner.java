package generator.definer;

import character.Characteristics;
import character.Class;
import generator.DiceRoller;

public class HitPointsDefiner {

    private static DiceRoller roller = new DiceRoller();

    public static int defineHP(Class characterClass, Characteristics characteristics) {
        switch (characterClass) {
            case CLERIC:
                return roller.d6();
            case DWARF:
                return roller.d8();
            case ELF:
                return roller.d6();
            case WARRIOR:
                return roller.d8();
            case HALFLING:
                return roller.d6();
            case MAGE:
                return roller.d4();
            case THIEF:
                return roller.d4();
            default: return 1;
        }
    }
}
