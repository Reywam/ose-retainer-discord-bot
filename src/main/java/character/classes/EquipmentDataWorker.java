package character.classes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import generator.DiceRoller;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Data
public class EquipmentDataWorker {

    private static final DiceRoller roller = new DiceRoller();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static ArrayNode getDagger(JsonNode equipment) {
        List<JsonNode> meleeWeapons = getItemsOfType("melee", equipment.get("weapons"));
        List<JsonNode> dagger = meleeWeapons.stream().filter(item -> item.get("name").asText().equals("Dagger")).collect(Collectors.toList());
        return mapper.createArrayNode().add(dagger.get(0));
    }

    public static JsonNode getSomeArmor(JsonNode equipment) {
        return getArmorOfSomeType(Arrays.asList("light", "medium", "heavy"), equipment);
    }

    public static ArrayNode getSimpleWeapons(JsonNode equipment) {
        return getMeleeWeapon(equipment).addAll(getMissileWeapon(equipment));
    }

    public static ArrayNode getMeleeWeapon(JsonNode equipment) {
        return getNWeaponsOfType(1, "melee", equipment);
    }

    public static ArrayNode getMissileWeapon(JsonNode equipment) {
        return getNWeaponsOfType(1, "missile", equipment);
    }

    public static JsonNode aggregateEquipment(ArrayNode weapons, JsonNode armor/*, JsonNode common*/) {
        ObjectNode equipment = mapper.createObjectNode();

        equipment.set("Weapons", weapons);
        equipment.set("Armor", armor);
        /*equipment.set("Common", common);*/

        return equipment;
    }

    public static ArrayNode getNWeaponsOfType(int countOfWeapons, String type, JsonNode equipment) {
        JsonNode weapons = equipment.get("weapons");
        List<JsonNode> typeWeapons = getItemsOfType(type, weapons);
        ArrayNode listOfItems = mapper.createArrayNode();
        for(int i = 0; i < countOfWeapons; i++) {
            listOfItems.add(typeWeapons.get(roller.dN(typeWeapons.size() - 1)));
        }
        return listOfItems;
    }

    public static JsonNode getLightArmor(JsonNode equipment) {
        return getArmorOfSomeType(Arrays.asList("light"), equipment);
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
