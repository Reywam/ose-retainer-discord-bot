package character.abilities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ThiefAbilities extends Abilities {
    private int climbOnSheerSurfaces;
    private int findOrRemoveTraps;
    private int hideInShadows;
    private int moveSilently;
    private int openLocks;
    private int pickPockets;
}
