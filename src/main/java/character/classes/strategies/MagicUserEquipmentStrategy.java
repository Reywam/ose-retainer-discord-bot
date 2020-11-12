package character.classes.strategies;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MagicUserEquipmentStrategy implements EquipmentStrategy {
    @Override
    public ObjectNode getEquipment(JsonNode equipment) {
        // TODO Get one dagger
        // TODO Get more common or special items
        System.out.println("MagicUser equipment strategy");
        return null;
    }
}
