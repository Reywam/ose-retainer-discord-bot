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
}
