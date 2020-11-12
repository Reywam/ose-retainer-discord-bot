package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MagicUserEquipmentStrategy implements EquipmentStrategy {
    @Override
    public JsonNode getEquipment(JsonNode equipment) {
        JsonNode dagger = EquipmentDataWorker.getDagger(equipment);
        // TODO Get more common or special items
        return new ObjectMapper().createObjectNode().set("Weapons", dagger);
    }
}
