package fr.customentity.customcore.i18n;

import fr.customentity.customcore.CPlugin;
import fr.customentity.customcore.exceptions.InvalidLocaleException;

import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class I18n {

    private CPlugin plugin;
    private Locale currentLocale;
    private String bundleBaseName;

    private ConcurrentMap<String, YamlResourceBundle> localeResourceBundleConcurrentMap;

    public I18n(CPlugin plugin) {
        this(plugin, Locale.getDefault().getLanguage());
    }

    public I18n(CPlugin plugin, String lang) {
        this(plugin, lang, "messages");
    }

    public I18n(CPlugin plugin, String lang, String bundleBaseName) {
        this.plugin = plugin;
        this.localeResourceBundleConcurrentMap = new ConcurrentHashMap<>();

        this.currentLocale = getLocaleByLanguageTag(lang);
        this.bundleBaseName = bundleBaseName;

        try {
            currentLocale.getISO3Language();
            currentLocale.getISO3Country();
        } catch (MissingResourceException e) {
            throw new InvalidLocaleException("Invalid local name");
        }

        this.localeResourceBundleConcurrentMap.put(lang, new YamlResourceBundle(plugin, currentLocale, bundleBaseName));
    }

    public String getString(String path, String lang) {
        YamlResourceBundle yamlResourceBundle = this.localeResourceBundleConcurrentMap.getOrDefault(lang,
                new YamlResourceBundle(plugin, getLocaleByLanguageTag(lang), bundleBaseName));

        this.localeResourceBundleConcurrentMap.putIfAbsent(lang, yamlResourceBundle);

        return yamlResourceBundle.getString(path);
    }

    public List<String> getStringList(String path, String lang) {
        YamlResourceBundle yamlResourceBundle = this.localeResourceBundleConcurrentMap.getOrDefault(lang,
                new YamlResourceBundle(plugin, getLocaleByLanguageTag(lang), bundleBaseName));

        this.localeResourceBundleConcurrentMap.putIfAbsent(lang, yamlResourceBundle);

        return yamlResourceBundle.getStringList(path);
    }

    public String getString(String path) {
        return this.localeResourceBundleConcurrentMap.get(currentLocale.toLanguageTag().replace("-", "_")).getString(path);
    }

    public List<String> getStringList(String path) {
        return this.localeResourceBundleConcurrentMap.get(currentLocale.toLanguageTag().replace("-", "_")).getStringList(path);
    }

    public Optional<YamlResourceBundle> getYamlResourceBundleByLang(String lang) {
        return Optional.ofNullable(localeResourceBundleConcurrentMap.get(lang));
    }

    public static Locale getLocaleByLanguageTag(String lang) {
        String[] splitted = lang.split("_");
        return splitted.length == 1 ? new Locale(splitted[0]) : new Locale(splitted[0], splitted[1]);
    }
}
