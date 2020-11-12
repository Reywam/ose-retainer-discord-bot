package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import generator.DiceRoller;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class FighterEquipmentStrategy implements EquipmentStrategy {

    private final DiceRoller roller = new DiceRoller();
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public ObjectNode getEquipment(JsonNode equipment) {
        ObjectNode characterEquipment = mapper.createObjectNode();

        ArrayNode meleeWeapons = EquipmentDataWorker.getNWeaponsOfType(2, "melee", equipment);
        ArrayNode missileWeapons = EquipmentDataWorker.getNWeaponsOfType(1, "missile", equipment);
        ArrayNode weapons = mapper.createArrayNode().addAll(meleeWeapons).addAll(missileWeapons);
        JsonNode armor = EquipmentDataWorker.getArmorOfSomeType(Arrays.asList("medium", "heavy"), equipment);

        characterEquipment.set("weapons", weapons);
        characterEquipment.set("armor", armor);

        // TODO Get shield if have not two-handed weapon
        return characterEquipment;
    }
}
