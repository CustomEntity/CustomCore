package fr.customentity.customcore.utils.location;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class LocationUtils {

    public static Location stringToLocationWithSight(String location) {
        if (location != null) {
            String[] args = location.split(",");
            return new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]), Float.parseFloat(args[4]), Float.parseFloat(args[5]));
        }
        return null;
    }

    public static String locationWithSightToString(Location location) {
        if (location == null) return null;
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
    }

    public static Location stringToLocation(String location) {
        if (location != null) {
            String[] args = location.split(",");
            return new Location(Bukkit.getWorld(args[0]), Double.parseDouble(args[1]), Double.parseDouble(args[2]), Double.parseDouble(args[3]));
        }
        return null;
    }

    public static String locationToString(Location location) {
        if (location == null) return null;
        return location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ();
    }
}
