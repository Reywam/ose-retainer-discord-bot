package character.classes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import generator.DiceRoller;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class EquipmentDataWorker {

    private static final DiceRoller roller = new DiceRoller();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ArrayNode getNWeaponsOfType(int countOfWeapons, String type, JsonNode equipment) {
        JsonNode weapons = equipment.get("weapons");
        List<JsonNode> typeWeapons = getItemsOfType(type, weapons);
        ArrayNode listOfItems = mapper.createArrayNode();
        for(int i = 0; i < countOfWeapons; i++) {
            listOfItems.add(typeWeapons.get(roller.dN(typeWeapons.size() - 1)));
        }
        return listOfItems;
    }

    public static JsonNode getArmorOfSomeType(List<String> types, JsonNode equipment) {
        JsonNode armor = equipment.get("armor");

        int armorTypeNumber = roller.dN(types.size() - 1);
        List<JsonNode> armors = new ArrayList<>();
        types.forEach(type -> {
            armors.addAll(getItemsOfType(type, armor));
        });

        return armors.get(armorTypeNumber);
    }

    public static List<JsonNode> getItemsOfType(String type, JsonNode items) {
        List<JsonNode> weaponsOfType = new ArrayList<>();
        items.forEach(weapon -> {
            JsonNode properties = weapon.get("properties");
            properties.forEach(property -> {
                if(property.asText().equals(type)) {
                    weaponsOfType.add(weapon);
                }
            });
        });
        return weaponsOfType;
    }
}
