package xyz.geik.farmer.modules.voucher;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import xyz.geik.farmer.Main;
import xyz.geik.farmer.modules.FarmerModule;
import xyz.geik.farmer.modules.voucher.commands.VoucherCommand;
import xyz.geik.farmer.modules.voucher.configuration.ConfigFile;
import xyz.geik.farmer.modules.voucher.handlers.VoucherEvent;
import xyz.geik.farmer.shades.storage.Config;
import xyz.geik.glib.GLib;
import xyz.geik.glib.chat.ChatUtils;
import xyz.geik.glib.shades.okaeri.configs.ConfigManager;
import xyz.geik.glib.shades.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;

import java.io.File;

/**
 * Voucher module main class
 */
@Getter
public class Voucher extends FarmerModule {

    /**
     * Constructor of class
     */
    public Voucher() {}

    @Getter
    private static Voucher instance;

    private static VoucherEvent voucherEvent;

    private Config langFile;

    @Getter
    private ConfigFile configFile;

    /**
     * onEnable method of module
     */
    public void onEnable() {
        instance = this;
        this.setLang(Main.getConfigFile().getSettings().getLang(), Main.getInstance());
        setupFile();

        if (configFile.isStatus()) {
            voucherEvent = new VoucherEvent();
            Bukkit.getPluginManager().registerEvents(voucherEvent, Main.getInstance());
            Main.getCommandManager().registerCommand(new VoucherCommand());
            String messagex = "&3[" + GLib.getInstance().getName() + "] &a" + getName() + " enabled.";
            ChatUtils.sendMessage(Bukkit.getConsoleSender(), messagex);
        }
        else {
            String messagex = "&3[" + GLib.getInstance().getName() + "] &c" + getName() + " is not loaded.";
            ChatUtils.sendMessage(Bukkit.getConsoleSender(), messagex);
        }
    }

    /**
     * onDisable method of module
     */
    @Override
    public void onDisable() {
        HandlerList.unregisterAll(voucherEvent);
        Main.getCommandManager().unregisterCommand(new VoucherCommand());
    }

    public void setupFile() {
        configFile = ConfigManager.create(ConfigFile.class, (it) -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File(Main.getInstance().getDataFolder(), String.format("/modules/%s/config.yml", getName())));
            it.saveDefaults();
            it.load(true);
        });
    }

}
