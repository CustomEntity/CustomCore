package fr.customentity.customcore.utils.player;

import fr.customentity.customcore.utils.reflections.ReflectionUtils;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.InvocationTargetException;

public class PlayerUtils {

    public static void resetPlayer(Player player) {
        player.setMaxHealth(20);
        player.setFoodLevel(20);
        player.setSaturation(20);
        player.setExp(0);
        player.setLevel(0);
        player.setFallDistance(0);
        player.setFireTicks(0);

        clearFullInventory(player);
        removePotionEffects(player);
    }

    public static void removePotionEffects(Player player) {
        for (PotionEffect effect : player.getActivePotionEffects())
            player.removePotionEffect(effect.getType());
    }

    public static void resetArmorDurability(Player player) {
        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (itemStack != null && itemStack.getType() != Material.AIR) itemStack.setDurability((short) -1);
        }
    }

    public static void clearFullInventory(Player player) {
        player.closeInventory();
        player.getInventory().clear();
        player.setItemInHand(new ItemStack(Material.AIR));
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public static void clearInventory(Player player) {
        player.closeInventory();
        player.getInventory().clear();
        player.setItemInHand(new ItemStack(Material.AIR));
    }

    public static int getPing(Player player) {
        try {
            Class<?> craftPlayer = ReflectionUtils.getCraftClass("entity.CraftPlayer");
            Object handle = craftPlayer.getMethod("getHandle").invoke(player);
            return (Integer) handle.getClass().getDeclaredField("ping")
                    .get(handle);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | NoSuchFieldException ignored) {}
        return 0;
    }
}
