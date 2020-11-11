package character;

import character.classes.CharacterClass;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import generator.DiceRoller;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EquipmentDataParser {
    private final ObjectMapper mapper = new ObjectMapper();
    private final DiceRoller roller = new DiceRoller();
    private JsonNode parse() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("equipment.json");
        JsonNode equipment = mapper.readTree(is);


        return equipment;
    }

    private List<JsonNode> getItemsOfType(String type, JsonNode items) {
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

    private JsonNode defineWeapons(CharacterData data, JsonNode weapons) {
        CharacterClass characterClass = data.getCharacterClass();
        ObjectNode characterWeapons = mapper.createObjectNode();

        List<JsonNode> melee = getItemsOfType("melee", weapons);
        List<JsonNode> blunt = getItemsOfType("blunt", weapons);
        List<JsonNode> missile = getItemsOfType("missile", weapons);

        //if(characterClass.getClass().getSimpleName().equals(Fighter.class.getSimpleName())) {
            int firstWeapon = roller.dN(melee.size() - 1);
            int secondWeapon = roller.dN(melee.size() - 1);
            int thirdWeapon = roller.dN(missile.size() - 1);

            characterWeapons.set("items", mapper.createArrayNode()
                    .add(melee.get(firstWeapon))
                    .add(melee.get(secondWeapon))
                    .add(missile.get(thirdWeapon)));
        //}

        return characterWeapons;
    }

    private JsonNode defineArmor(CharacterData data, JsonNode armor) {
        ObjectNode characterWeapons = mapper.createObjectNode();

        List<JsonNode> medium = getItemsOfType("medium", armor);
        List<JsonNode> heavy = getItemsOfType("heavy", armor);

        int armorIndex = roller.dN(2);
        if(armorIndex == 1) {
            characterWeapons.set("items", medium.get(0));
        }
        else {
            characterWeapons.set("items", heavy.get(0));
        }

        return characterWeapons;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Item {
        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

    public HashMap<String, List<Item>> generateEquipment(CharacterData data) throws IOException {
        JsonNode equipment = parse();
        JsonNode weapons = equipment.get("weapons");
        JsonNode armor = equipment.get("armor");
        JsonNode common = equipment.get("common");

        ObjectNode characterEquipment = mapper.createObjectNode();
        characterEquipment.set("Common items", common);
        characterEquipment.set("Weapons", defineWeapons(data, weapons));
        characterEquipment.set("Armor", defineArmor(data, armor));

        HashMap<String, List<Item>> items = new HashMap<>();
        items.put("weapons", new ArrayList<>());
        items.put("armor", new ArrayList<>());
        items.put("common", new ArrayList<>());

        common.forEach(item -> items.get("common").add(new Item(item.get("name").asText())));
        characterEquipment.get("Weapons").get("items").forEach(item -> {
            items.get("weapons").add(new Item(item.get("name").asText()));
        });
        characterEquipment.get("Armor").get("items").forEach(item -> {
            items.get("armor").add(new Item(item.asText()));
        });

        return items;
    }



}
