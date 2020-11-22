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
    public JsonNode getEquipment(JsonNode equipment) {
        // TODO Get one one-handed melee weapon
        ArrayNode weapons = EquipmentDataWorker.getSimpleWeapons(equipment);
        JsonNode armor = EquipmentDataWorker.getLightArmor(equipment);
        // TODO Get thief tools
        ArrayNode common = EquipmentDataWorker.getBasicItems(equipment);
        return EquipmentDataWorker.aggregateEquipment(weapons, armor, common);
    }
}
