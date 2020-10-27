package fr.customentity.customcore;

import fr.customentity.customcore.logger.BukkitLogger;
import org.bukkit.plugin.java.JavaPlugin;


public abstract class CPlugin extends JavaPlugin {

    private BukkitLogger logger;

    public CPlugin() {
        this.logger = new BukkitLogger(this);
    }

    protected BukkitLogger getPluginLogger() {
        return this.logger;
    }

}
