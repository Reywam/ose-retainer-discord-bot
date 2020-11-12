package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class DwarfEquipmentStrategy implements EquipmentStrategy {
    @Override
    public JsonNode getEquipment(JsonNode equipment) {
        // TODO Something like a fighter but with restrictions
        ArrayNode weapons = EquipmentDataWorker.getSimpleWeapons(equipment);
        JsonNode armor = EquipmentDataWorker.getArmorOfSomeType(Arrays.asList("heavy", "medium"), equipment);
        return EquipmentDataWorker.aggregateEquipment(weapons, armor);
    }
}
