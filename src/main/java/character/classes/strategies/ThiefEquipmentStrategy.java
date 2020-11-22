package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ThiefEquipmentStrategy implements EquipmentStrategy {
    @Override
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        // TODO Get one one-handed melee weapon
        ArrayNode weapons = worker.getSimpleWeapons();
        JsonNode armor = worker.getLightArmor();
        // TODO Get thief tools
        ArrayNode common = worker.getBasicItems();
        return worker.aggregateEquipment(weapons, armor, common);
    }
}
