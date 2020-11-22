import character.CharacterData;
import character.EquipmentDataParser;
import character.classes.*;
import com.fasterxml.jackson.databind.JsonNode;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Application {
    public static void main(String[] args) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";

        Properties appProps = new Properties();
        appProps.load(new FileInputStream(appConfigPath));

        final String token = appProps.getProperty("token");
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

        EquipmentDataWorker worker = new EquipmentDataWorker();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();
            if ("!get".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                CharacterData characterData = new CharacterData();
                try {
                    Map<String, String> equipment = worker.generateEquipment(characterData);
                    characterData.setAdventureEquipment(equipment);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                channel.createEmbed(spec ->
                        spec.setTitle("Character Name")
                        .addField("Hit points", Integer.toString(characterData.getHitPoints()), true)
                        .addField("AC", "UNDEFINED", true)
                        .addField("Class", getClassTitle(characterData), true)
                        .addField("Alignment", "Neutral", true)
                        .addField("Characteristics", characterData.getCharacteristics().toString(), true)
                        .addField("Saving throws", characterData.getCharacterClass().getSavingThrows().toString(), true)
                        .addField("Abilities", characterData.getCharacterClass().getAbilities().toString(), true)
                        .addField("Common", characterData.getAdventureEquipment().get("Common"), false)
                        .addField("Special", characterData.getAdventureEquipment().get("Special"), false)
                        .addField("Armor", characterData.getAdventureEquipment().get("Armor"), false)
                        .addField("Weapons", characterData.getAdventureEquipment().get("Weapons"), false)
                ).block();
            }
        });

        gateway.onDisconnect().block();
    }

    private static String getClassTitle(CharacterData characterData) {
        String className = characterData.getCharacterClass().getClass().getSimpleName();
        if(className.equals(Fighter.class.getSimpleName())) {
            className =  ":crossed_swords:".concat(className);
        }

        if(className.equals(Cleric.class.getSimpleName())) {
            className =  ":medical_symbol:".concat(className);
        }

        if(className.equals(Dwarf.class.getSimpleName())) {
            className =  ":axe:".concat(className);
        }

        if(className.equals(Elf.class.getSimpleName())) {
            className =  ":woman_elf:".concat(className);
        }

        if(className.equals(Halfling.class.getSimpleName())) {
            className =  ":meat_on_bone:".concat(className);
        }

        if(className.equals(MagicUser.class.getSimpleName())) {
            className =  ":magic_wand:".concat(className);
        }

        if(className.equals(MagicUser.class.getSimpleName())) {
            className =  ":dagger:".concat(className);
        }

        return className;
    };
}
