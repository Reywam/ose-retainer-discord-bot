package character.classes.strategies;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClericEquipmentStrategy implements EquipmentStrategy {
    @Override
    public ObjectNode getEquipment(JsonNode equipment) {
        // TODO Get one blunt melee weapon
        // TODO Get one blunt missile weapon
            // TODO Get ammo if missile needs ammo
        // TODO Get heavy or medium armor
        // TODO Get holy symbol
        System.out.println("Cleric equipment strategy");
        return null;
    }
}
