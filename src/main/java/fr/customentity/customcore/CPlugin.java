package fr.customentity.customcore;

import fr.customentity.customcore.common.TestedVersions;
import fr.customentity.customcore.logger.BukkitLogger;
import fr.customentity.customcore.utils.reflections.ReflectionUtils;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;


public abstract class CPlugin extends JavaPlugin {

    private BukkitLogger logger;

    public CPlugin() {
        this.logger = new BukkitLogger(this);
    }

    @Override
    public void onEnable() {
        this.onPluginEnable();

        if (!isVersionTested())
            this.logger.log(BukkitLogger.LogLevel.WARNING, "You are using the plugin on an untested server version! It is likely that you will encounter problems.");
    }

    @Override
    public void onDisable() {
        this.onPluginDisable();
    }

    protected void onPluginEnable() {
    }

    protected void onPluginDisable() {
    }

    public PluginInformations getPluginInformations() {
        Class<?> clazz = this.getClass();
        if (!clazz.isAnnotationPresent(PluginInformations.class)) return null;
        return clazz.getAnnotation(PluginInformations.class);
    }

    private boolean isVersionTested() {
        PluginInformations pluginInformations = this.getPluginInformations();
        if (pluginInformations == null) return true;
        List<TestedVersions> testedVersions = Arrays.asList(pluginInformations.testedVersions());
        return testedVersions.contains(TestedVersions.ALL)
                || testedVersions.contains(TestedVersions.NONE)
                || testedVersions.stream().filter(version -> version != TestedVersions.ALL &&
                version != TestedVersions.NONE).anyMatch(version -> version.getIntegerVersion() == ReflectionUtils.getServerVersionInteger());

    }

    public void setLogger(BukkitLogger logger) {
        this.logger = logger;
    }

    public BukkitLogger getPluginLogger() {
        return this.logger;
    }

}
