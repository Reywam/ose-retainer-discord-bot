package generator.definer;

import character.Characteristics;
import character.Class;
import generator.DiceRoller;

public class HitPointsDefiner {

    private static DiceRoller roller = new DiceRoller();

    public static int defineHP(Class characterClass, Characteristics characteristics) {
        int basicHP = 1;
        switch (characterClass) {
            case CLERIC:
                basicHP = roller.d6();
            case DWARF:
                basicHP =  roller.d8();
            case ELF:
                basicHP =  roller.d6();
            case WARRIOR:
                basicHP =  roller.d8();
            case HALFLING:
                basicHP =  roller.d6();
            case MAGE:
                basicHP =  roller.d4();
            case THIEF:
                basicHP =  roller.d4();
        }
        return basicHP += characteristics.getConstitutionModifier();
    }
}
