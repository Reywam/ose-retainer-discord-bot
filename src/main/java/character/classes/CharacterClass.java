package character.classes;

import character.SavingThrows;
import character.abilities.Abilities;

public interface CharacterClass {
    int getHitDice();
    SavingThrows getSavingThrows();
    Abilities getAbilities();
}
