package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class FighterEquipmentStrategy implements EquipmentStrategy {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public JsonNode getEquipment(JsonNode equipment) {
        ArrayNode meleeWeapons = EquipmentDataWorker.getNWeaponsOfType(2, "melee", equipment);
        ArrayNode missileWeapons = EquipmentDataWorker.getNWeaponsOfType(1, "missile", equipment);
        ArrayNode weapons = mapper.createArrayNode().addAll(meleeWeapons).addAll(missileWeapons);
        JsonNode armor = EquipmentDataWorker.getArmorOfSomeType(Arrays.asList("medium", "heavy"), equipment);

        // TODO Get shield if have not two-handed weapon
        return EquipmentDataWorker.aggregateEquipment(weapons, armor);


    }
}
