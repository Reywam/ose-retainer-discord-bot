package character.classes;

import character.abilities.Abilities;
import character.SavingThrows;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class Cleric implements CharacterClass {
    public Cleric() {
        savingThrows = new SavingThrows()
                .setDeathOrPoison(11)
                .setWands(12)
                .setParalysisOrPetrification(14)
                .setBreathAttacks(15)
                .setSpellsOrRodsOrStaves(16);
        hitDice = 6;
        abilities = new Abilities();
    }

    private int hitDice;
    private SavingThrows savingThrows;
    // TODO Add ability to undead turning
    private Abilities abilities;
}
