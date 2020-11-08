package character.abilities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Data
@Accessors(chain = true)
public class Abilities {
    private int openDoors = 1;
    private int listening = 1;
    private int searchSecretDoors = 1;
    private int searchTraps = 1;

    private boolean infravision = false;
    private boolean immuneToGhoulParalyze = false;
    private int architectureKnowledge = 0;
    private int hideInShadows = 0;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String templateString = "%s: %d in 6";
        return
                builder
                    .append(String.format(templateString, "Open doors", openDoors)).append('\n')
                    .append(String.format(templateString, "Head noises", listening)).append('\n')
                    .append(String.format(templateString, "Search secret doors", searchSecretDoors)).append('\n')
                    .append(String.format(templateString, "Search traps", searchTraps)).append('\n')
                .toString();
    }
}
