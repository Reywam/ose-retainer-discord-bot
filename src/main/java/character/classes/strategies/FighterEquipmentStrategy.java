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
    public JsonNode getEquipment(EquipmentDataWorker worker) {
        ArrayNode meleeWeapons = worker.getNWeaponsOfType(2, "melee");
        ArrayNode missileWeapons = worker.getMissileWeapon();
        ArrayNode weapons = meleeWeapons.addAll(missileWeapons);
        JsonNode armor = worker.getArmorOfSomeType(Arrays.asList("medium", "heavy"));

        ArrayNode common = worker.getBasicItems();

        // TODO Get shield if have not two-handed weapon
        JsonNode classItems = worker.getClassItems("fighter");
        return worker.aggregateEquipment(weapons, armor, common, classItems);


    }
}
