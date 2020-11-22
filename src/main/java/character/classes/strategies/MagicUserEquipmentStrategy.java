package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MagicUserEquipmentStrategy implements EquipmentStrategy {
    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        JsonNode dagger = worker.getDagger();
        // TODO Get more common or special items
        ArrayNode classItems = worker.getClassItems("magic-user");
        ArrayNode commonItems = worker.getBasicItems();
        ObjectNode items = new ObjectMapper().createObjectNode();
        items.set("Weapons", dagger);
        items.set("Special", classItems);
        items.set("Common", commonItems);
        return items;
    }
}
