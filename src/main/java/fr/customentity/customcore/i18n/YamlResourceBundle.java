package fr.customentity.customcore.i18n;

import fr.customentity.customcore.CPlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class YamlResourceBundle {

    private CPlugin plugin;

    private Locale locale;

    private String bundleBaseName;

    private ConcurrentMap<String, List<String>> cachedResourceContent;

    private FileConfiguration yamlConfig;

    private File yamlFile;

    private String fileName;

    public YamlResourceBundle(CPlugin plugin, Locale locale, String bundleBaseName) {
        this.plugin = plugin;
        this.locale = locale;
        this.bundleBaseName = bundleBaseName;
        this.cachedResourceContent = new ConcurrentHashMap<>();
        this.fileName = this.bundleBaseName + "_" + locale.toLanguageTag().replace("-", "_") + ".yml";

        this.setupYamlResource();
    }

    private void setupYamlResource() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        yamlFile = new File(plugin.getDataFolder(), this.fileName);
        if (!yamlFile.exists()) {
            plugin.saveResource(this.fileName, false);
        }
        yamlConfig = YamlConfiguration.loadConfiguration(yamlFile);
    }

    private void refreshCache() {
        yamlConfig = YamlConfiguration.loadConfiguration(yamlFile);
        cachedResourceContent.forEach((s, o) -> cachedResourceContent.put(s, yamlConfig.getStringList(s)));
    }

    public void clearCache() {
        this.cachedResourceContent.clear();
    }

    public String getString(String path) {
        return this.getString(path, true);
    }

    public List<String> getStringList(String path) {
        return this.getStringList(path, true);
    }

    public String getString(String path, boolean useCacheIfPresent) {
        if(useCacheIfPresent)return cachedResourceContent.getOrDefault(path, Collections.singletonList(yamlConfig.getString(path))).get(0);
        return yamlConfig.getString(path);
    }

    public List<String> getStringList(String path, boolean useCacheIfPresent) {
        if(useCacheIfPresent)return cachedResourceContent.getOrDefault(path, yamlConfig.getStringList(path));
        return yamlConfig.getStringList(path);
    }


    public String getBundleBaseName() {
        return bundleBaseName;
    }

    public Locale getLocale() {
        return locale;
    }

    public ConcurrentMap<String, List<String>> getCachedResourceContent() {
        return cachedResourceContent;
    }
}
