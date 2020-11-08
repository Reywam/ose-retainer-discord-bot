package character.classes;

import character.abilities.Abilities;
import character.SavingThrows;
import lombok.Data;

@Data
public class Fighter implements CharacterClass {
    public Fighter() {
        savingThrows = new SavingThrows()
                .setDeathOrPoison(12)
                .setWands(13)
                .setParalysisOrPetrification(14)
                .setBreathAttacks(15)
                .setSpellsOrRodsOrStaves(16);
        hitDice = 8;
        abilities = new Abilities();
    }

    private int hitDice;
    private SavingThrows savingThrows;
    private Abilities abilities;
}
