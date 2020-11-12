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
        ArrayNode meleeWeapon = EquipmentDataWorker.getNWeaponsOfType(1, "melee", equipment);
        JsonNode armor = EquipmentDataWorker.getArmorOfSomeType(Arrays.asList("heavy", "medium"), equipment);
        return EquipmentDataWorker.aggregateEquipment(meleeWeapon, armor);
    }
}
