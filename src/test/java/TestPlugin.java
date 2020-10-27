import fr.customentity.customcore.CPlugin;
import fr.customentity.customcore.PluginInformations;
import fr.customentity.customcore.common.TestedVersions;
import fr.customentity.customcore.i18n.I18n;

@PluginInformations(name = "MonPlugin", testedVersions = {TestedVersions.VER_1_7, TestedVersions.VER_1_11})
public class TestPlugin extends CPlugin {

    private I18n i18n;

    protected void onPluginEnable() {
        saveDefaultConfig();

        this.i18n = new I18n(this, getConfig().getString("locale"), "messages");
        System.out.println(i18n.getString("potato"));
    }

    protected void onPluginDisable() {

    }

    public I18n getI18n() {
        return i18n;
    }
}
