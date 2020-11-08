package character.classes;

import character.abilities.Abilities;
import character.SavingThrows;
import lombok.Data;

@Data
public class Elf implements CharacterClass {
    public Elf() {
        savingThrows = new SavingThrows()
                .setDeathOrPoison(12)
                .setWands(13)
                .setParalysisOrPetrification(13)
                .setBreathAttacks(15)
                .setSpellsOrRodsOrStaves(15);
        hitDice = 6;
        abilities = new Abilities()
                .setInfravision(true)
                .setImmuneToGhoulParalyze(true)
                .setSearchSecretDoors(2)
                .setListening(2);
    }

    private int hitDice;
    private SavingThrows savingThrows;
    private Abilities abilities;
}
