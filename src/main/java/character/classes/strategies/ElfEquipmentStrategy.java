package character.classes.strategies;

import character.classes.EquipmentDataWorker;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sun.xml.internal.txw2.output.DataWriter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@NoArgsConstructor
public class ElfEquipmentStrategy implements EquipmentStrategy {
    @Override
    public JsonNode getEquipment(JsonNode equipment) {
        // TODO Actually like a fighter strategy
        ArrayNode weapons = EquipmentDataWorker.getSimpleWeapons(equipment);
        JsonNode armor = EquipmentDataWorker.getArmorOfSomeType(Arrays.asList("heavy", "medium", "light"), equipment);
        return EquipmentDataWorker.aggregateEquipment(weapons, armor);
    }
}
