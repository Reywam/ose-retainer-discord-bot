package generator.definer;

import character.Class;
import generator.DiceRoller;

public class ClassDefiner {
    private static final int CHARACTER_CLASSES_COUNT = 7;
    private static DiceRoller roller = new DiceRoller();

    public static Class define() {
        int random = roller.dN(CHARACTER_CLASSES_COUNT);
        switch (random) {
            case 1:
                return Class.WARRIOR;
            case 2:
                return Class.MAGE;
            case 3:
                return Class.CLERIC;
            case 4:
                return Class.THIEF;
            case 5:
                return Class.DWARF;
            case 6:
                return Class.ELF;
            case 7:
                return Class.HALFLING;
            default: return  Class.WARRIOR;
        }
    }
}
