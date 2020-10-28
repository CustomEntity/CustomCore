import fr.customentity.customcore.CPlugin;
import fr.customentity.customcore.PluginInformations;
import fr.customentity.customcore.common.TestedVersions;
import fr.customentity.customcore.i18n.I18n;
import fr.customentity.customcore.messenger.Messenger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@PluginInformations(name = "MonPlugin", testedVersions = {TestedVersions.VER_1_7, TestedVersions.VER_1_11})
public class TestPlugin extends CPlugin {

    private I18n i18n;

    protected void onPluginEnable() {
        saveDefaultConfig();

        this.i18n = new I18n(this, getConfig().getString("locale"), "messages");
    }

    protected void onPluginDisable() {

    }

    public I18n getI18n() {
        return i18n;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;

        Messenger.sendMessage(player, this.i18n.getString("potato"));
        return true;
    }
}
