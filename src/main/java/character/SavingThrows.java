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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String templateString = "%s: %d";
        return
                builder.append(String.format(templateString, "D", deathOrPoison)).append('\n')
                        .append(String.format(templateString, "W", wands)).append('\n')
                        .append(String.format(templateString, "P", paralysisOrPetrification)).append('\n')
                        .append(String.format(templateString, "B", breathAttacks)).append('\n')
                        .append(String.format(templateString, "S", spellsOrRodsOrStaves)).append('\n')
                        .toString();
    }
}
