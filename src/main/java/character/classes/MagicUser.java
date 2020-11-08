package character.classes;

import character.abilities.Abilities;
import character.SavingThrows;
import lombok.Data;

@Data
public class MagicUser implements CharacterClass {
    public MagicUser() {
        savingThrows = new SavingThrows()
                .setDeathOrPoison(13)
                .setWands(14)
                .setParalysisOrPetrification(13)
                .setBreathAttacks(16)
                .setSpellsOrRodsOrStaves(15);
        hitDice = 4;
        abilities = new Abilities();
    }

    private int hitDice;
    private SavingThrows savingThrows;
    private Abilities abilities;
}
