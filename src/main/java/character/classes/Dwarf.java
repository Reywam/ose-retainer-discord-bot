package character.classes;

import character.abilities.Abilities;
import character.SavingThrows;
import lombok.Data;

@Data
public class Dwarf implements CharacterClass {
    public Dwarf() {
        savingThrows = new SavingThrows()
                .setDeathOrPoison(8)
                .setWands(9)
                .setParalysisOrPetrification(10)
                .setBreathAttacks(13)
                .setSpellsOrRodsOrStaves(12);
        hitDice = 8;
        abilities = new Abilities()
                .setInfravision(true)
                .setArchitectureKnowledge(2)
                .setListening(2);
    }

    private int hitDice;
    private SavingThrows savingThrows;
    private Abilities abilities;
}
