package character;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class SavingThrows {
    private int deathOrPoison;
    private int wands;
    private int paralysisOrPetrification;
    private int breathAttacks;
    private int spellsOrRodsOrStaves;
}
