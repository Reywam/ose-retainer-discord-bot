package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class ElfEquipmentStrategy implements EquipmentStrategy {
    @Override
    public JsonNode getEquipment(JsonNode equipment) {
        // TODO Actually like a fighter strategy
        ArrayNode meleeWeapons = EquipmentDataWorker.getNWeaponsOfType(1, "melee", equipment);
        ArrayNode missileWeapons = EquipmentDataWorker.getNWeaponsOfType(1, "missile", equipment);
        ArrayNode weapons = meleeWeapons.addAll(missileWeapons);
        JsonNode armor = EquipmentDataWorker.getArmorOfSomeType(Arrays.asList("heavy", "medium", "light"), equipment);
        return EquipmentDataWorker.aggregateEquipment(weapons, armor);
    }
}
