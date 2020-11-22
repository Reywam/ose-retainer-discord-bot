package character.classes;

import character.CharacterData;
import character.EquipmentDataParser;
import character.classes.strategies.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import generator.DiceRoller;
import lombok.Data;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Data
public class EquipmentDataWorker {

    private final DiceRoller roller = new DiceRoller();
    private final ObjectMapper mapper = new ObjectMapper();
    private final int COUNT_OF_ADDITIONAL_COMMON_ITEMS = 4;
    private final EquipmentDataParser parser = new EquipmentDataParser();
    private JsonNode equipment;

    private final Map<Class, EquipmentStrategy> strategies = new HashMap<Class, EquipmentStrategy>() {
        {
            put(Fighter.class, new FighterEquipmentStrategy());
            put(Cleric.class, new ClericEquipmentStrategy());
            put(MagicUser.class, new MagicUserEquipmentStrategy());
            put(Thief.class, new ThiefEquipmentStrategy());
            put(Halfling.class, new HalflingEquipmentStrategy());
            put(Dwarf.class, new DwarfEquipmentStrategy());
            put(Elf.class, new ElfEquipmentStrategy());
        }
    };

    private Map<String, String> equipmentToStringMap(JsonNode equipment) {
        JsonNode weapons = equipment.get("Weapons");
        JsonNode armor = equipment.get("Armor");
        JsonNode common = equipment.get("Common");
        JsonNode special = equipment.get("Special");

        HashMap<String, String> data = new HashMap<>();
        data.put("Weapons", join(weapons));
        data.put("Armor", join(armor));
        data.put("Common", join(common));
        data.put("Special", join(special));

        return data;
    }

    private String join(JsonNode equipment) {
        if(isNull(equipment)) return "Has no item";
        StringJoiner joiner = new StringJoiner(" ");
        if(equipment.isArray()) {
            equipment.forEach(node -> joiner.add(node.get("name").asText()));
        }
        else {
            joiner.add(equipment.get("name").asText());
        }

        return joiner.toString();
    }

    public Map<String, String> generateEquipment(CharacterData data) throws IOException {
        EquipmentStrategy strategy = strategies.get(data.getCharacterClass().getClass());
        JsonNode characterEquipment = strategy.getEquipment(this);
        return equipmentToStringMap(characterEquipment);
    }

    public EquipmentDataWorker() throws IOException {
        equipment = parser.parse();
    }


    public ArrayNode getClassItems(String className) {
        return (ArrayNode) equipment.get(className.toLowerCase());
    }

    public ArrayNode getBasicItems() {
        ArrayNode common = getCommonItems();
        ArrayNode additional = getAdditionalCommonItems();
        return common.addAll(additional);
    }

    private ArrayNode getAdditionalCommonItems() {
        ArrayNode additionalCommonItems = (ArrayNode) equipment.get("additional_common");

        int rollCount = additionalCommonItems.size() - 1;
        ArrayNode additionalCharacterItems = mapper.createArrayNode();

        for(int i = 0; i < COUNT_OF_ADDITIONAL_COMMON_ITEMS; i++) {
            int itemId = roller.dN(rollCount);
            additionalCharacterItems.add(additionalCommonItems.get(itemId));
        }

        return additionalCharacterItems;
    }

    private ArrayNode getCommonItems() {
        return (ArrayNode) equipment.get("common");
    }

    public ArrayNode getDagger() {
        List<JsonNode> meleeWeapons = getItemsOfType("melee", equipment.get("weapons"));
        List<JsonNode> dagger = meleeWeapons.stream().filter(item -> item.get("name").asText().equals("Dagger")).collect(Collectors.toList());
        return mapper.createArrayNode().add(dagger.get(0));
    }

    public JsonNode getSomeArmor() {
        return getArmorOfSomeType(Arrays.asList("light", "medium", "heavy"));
    }

    public ArrayNode getSimpleWeapons() {
        return getMeleeWeapon().addAll(getMissileWeapon());
    }

    public ArrayNode getMeleeWeapon() {
        return getNWeaponsOfType(1, "melee");
    }

    public ArrayNode getMissileWeapon() {
        return getNWeaponsOfType(1, "missile");
    }

    public JsonNode aggregateEquipment(ArrayNode weapons, JsonNode armor, JsonNode common, JsonNode classItems) {
        ObjectNode equipment = mapper.createObjectNode();

        equipment.set("Weapons", weapons);
        equipment.set("Armor", armor);
        equipment.set("Common", common);
        equipment.set("Special", classItems);

        return equipment;
    }

    public ArrayNode getNWeaponsOfType(int countOfWeapons, String type) {
        JsonNode weapons = equipment.get("weapons");
        List<JsonNode> typeWeapons = getItemsOfType(type, weapons);
        ArrayNode listOfItems = mapper.createArrayNode();
        for(int i = 0; i < countOfWeapons; i++) {
            listOfItems.add(typeWeapons.get(roller.dN(typeWeapons.size() - 1)));
        }
        return listOfItems;
    }

    public JsonNode getLightArmor() {
        return getArmorOfSomeType(Arrays.asList("light"));
    }

    public JsonNode getArmorOfSomeType(List<String> types) {
        JsonNode armor = equipment.get("armor");
        List<JsonNode> armors = new ArrayList<>();
        if(types.size() > 1) {
            int armorTypeNumber = roller.dN(types.size() - 1);
            types.forEach(type -> {
                armors.addAll(getItemsOfType(type, armor));
            });

            return armors.get(armorTypeNumber);
        }
        else {
            armors.addAll(getItemsOfType(types.get(0), armor));
            return armors.get(0);
        }
    }

    public List<JsonNode> getItemsOfType(String type, JsonNode items) {
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
