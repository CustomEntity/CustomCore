package fr.customentity.customcore.logger;

import fr.customentity.customcore.CPlugin;
import fr.customentity.customcore.PluginInformations;
import org.bukkit.Bukkit;

public class BukkitLogger {

    private String prefix;
    private boolean debugMode;

    public BukkitLogger(CPlugin cPlugin) {
        PluginInformations pluginInformations = cPlugin.getPluginInformations();
        this.prefix = pluginInformations == null ? cPlugin.getDescription().getName() : pluginInformations.name();
    }

    public BukkitLogger(String prefix) {
        this.prefix = prefix;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void log(LogLevel logLevel, String message) {
        Bukkit.getConsoleSender().sendMessage(logLevel.getColor() + "[" + this.prefix + "] " + message);
    }

    public void log(LogLevel logLevel, String prefix, String message) {
        Bukkit.getConsoleSender().sendMessage(logLevel.getColor() + "[" + prefix + "] " + message);
    }

    public void debug(LogLevel logLevel, String message) {
        if (!isDebugMode()) return;
        Bukkit.getConsoleSender().sendMessage(logLevel.getColor() + "[" + this.prefix + "] " + message);
    }

    public enum LogLevel {
        INFO("§7"),
        WARNING("§6"),
        ERROR("§c"),
        SUCCESS("§a");

        private final String logColor;

        LogLevel(String logColor) {
            this.logColor = logColor;
        }

        public String getColor() {
            return logColor;
        }
    }
}
