package generator.definer;

import character.classes.*;
import generator.DiceRoller;

public class ClassDefiner {
    private static final int CHARACTER_CLASSES_COUNT = 7;
    private static DiceRoller roller = new DiceRoller();

    public static CharacterClass define() {
        int random = roller.dN(CHARACTER_CLASSES_COUNT);
        switch (random) {
            case 1:
                return new Fighter();
            case 2:
                return new MagicUser();
            case 3:
                return new Cleric();
            case 4:
                return new Thief();
            case 5:
                return new Dwarf();
            case 6:
                return new Elf();
            case 7:
                return new Halfling();
            default: return new Fighter();
        }
    }
}
