package character.classes;

import character.abilities.Abilities;
import character.SavingThrows;
import character.abilities.ThiefAbilities;
import lombok.Data;

@Data
public class Thief implements CharacterClass {
    public Thief() {
        savingThrows = new SavingThrows()
                .setDeathOrPoison(13)
                .setWands(14)
                .setParalysisOrPetrification(13)
                .setBreathAttacks(16)
                .setSpellsOrRodsOrStaves(15);
        hitDice = 4;
        abilities = new ThiefAbilities()
                .setClimbOnSheerSurfaces(87)
                .setMoveSilently(20)
                .setOpenLocks(15)
                .setPickPockets(20)
                .setFindOrRemoveTraps(10)
                .setListening(2)
                .setHideInShadows(10);
    }

    private int hitDice;
    private SavingThrows savingThrows;
    private Abilities abilities;
}
