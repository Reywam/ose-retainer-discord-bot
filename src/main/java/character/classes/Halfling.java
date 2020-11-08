package character.classes;

import character.abilities.Abilities;
import character.SavingThrows;
import lombok.Data;

@Data
public class Halfling implements CharacterClass {
    public Halfling() {
        savingThrows = new SavingThrows()
                .setDeathOrPoison(8)
                .setWands(9)
                .setParalysisOrPetrification(10)
                .setBreathAttacks(13)
                .setSpellsOrRodsOrStaves(12);
        hitDice = 6;
        abilities = new Abilities().setHideInShadows(2);
    }

    private int hitDice;
    private SavingThrows savingThrows;
    private Abilities abilities;
}
