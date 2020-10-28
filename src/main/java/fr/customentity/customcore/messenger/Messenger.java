package fr.customentity.customcore.messenger;

import fr.customentity.customcore.utils.messages.ActionBar;
import fr.customentity.customcore.utils.messages.Titles;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

public class Messenger {

    public static void sendMessage(CommandSender sender, String message, String... replace) {
        sendMessage(sender, Collections.singletonList(message), replace);
    }

    public static void sendMessage(CommandSender sender, List<String> messages, String... replace) {
        HashMap<String, String> replaced = new HashMap<>();
        List<String> replaceList = Arrays.asList(replace);
        int index = 0;
        for (String str : replaceList) {
            index++;
            if (index % 2 == 0) continue;
            replaced.put(str, replaceList.get(index));
        }
        messages.forEach(s -> sendMessage(sender, messages, replace));
    }

    private static void sendMessage(CommandSender commandSender, String configMessage, HashMap<String, String> replaced) {
        if (commandSender instanceof Player) {
            sendMessage(((Player) commandSender), configMessage, replaced);
        } else {
            String message = ChatColor.translateAlternateColorCodes('&', configMessage.replace("%sender%", commandSender.getName()));
            if (message.isEmpty()) return;
            for (Map.Entry<String, String> stringEntry : replaced.entrySet()) {
                message = message.replace(stringEntry.getKey(), stringEntry.getValue());
            }
            commandSender.sendMessage(message);
        }
    }

    private static void sendMessage(Player player, String configMessage, HashMap<String, String> replaced) {
        String message = ChatColor.translateAlternateColorCodes('&', configMessage.replace("%sender%", player.getName()));
        if (message.isEmpty()) return;
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI"))
            message = PlaceholderAPI.setPlaceholders(player, message);

        for (Map.Entry<String, String> stringEntry : replaced.entrySet()) {
            message = message.replace(stringEntry.getKey(), stringEntry.getValue());
        }
        if (message.toLowerCase().startsWith("[title]")) {
            if (message.toLowerCase().contains("[subtitle]")) {
                String[] splitted = message.split("[subtitle]");
                Titles.sendTitle(player, 0, 40, 10, splitted[0].replaceAll("(?i)[subtitle]", "").replaceAll("(?i)[title]", ""), splitted[1].replaceAll("(?i)[subtitle]", "").replaceAll("(?i)[title]", ""));
            } else {
                Titles.sendTitle(player, 0, 40, 10, message.replaceAll("(?i)[title]", ""), null);
            }
        } else if (message.toLowerCase().startsWith("[subtitle]")) {
            if (message.toLowerCase().contains("[title]")) {
                String[] splitted = message.split("[title]");
                Titles.sendTitle(player, 0, 40, 10, splitted[0].replaceAll("(?i)[subtitle]", "").replaceAll("(?i)[title]", ""), splitted[1].replaceAll("(?i)[subtitle]", "").replaceAll("(?i)[title]", ""));
            } else {
                Titles.sendTitle(player, 0, 40, 10, message.replaceAll("(?i)[subtitle]", ""), null);
            }
        } else if (message.toLowerCase().startsWith("[actionbar]")) {
            ActionBar.sendActionBar(player, message.replaceAll("(?i)[actionbar]", ""));
        } else {
            player.sendMessage(message);
        }
    }




}
