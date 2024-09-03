package xyz.geik.farmer.modules.voucher.configuration;

import lombok.Getter;
import lombok.Setter;
import xyz.geik.glib.shades.okaeri.configs.OkaeriConfig;
import xyz.geik.glib.shades.okaeri.configs.annotation.Comment;
import xyz.geik.glib.shades.okaeri.configs.annotation.NameStrategy;
import xyz.geik.glib.shades.okaeri.configs.annotation.Names;

import java.util.Arrays;
import java.util.List;

/**
 * Modules file
 *
 * @author geik
 * @since 2.0
 */
@Getter
@Setter
@Names(strategy = NameStrategy.IDENTITY)
public class ConfigFile extends OkaeriConfig {

    @Comment({"if you don't want to use voucher system",
            "which is place farmer with a voucher only console",
            "and players with farmer.admin permission can give voucher.",
            "you can disable buy feature and give farmer with command"})
    private boolean status = true;

    @Comment({"if you want to use voucher system with override method",
            "you can enable this setting. it will override voucher to farmer",
            "which replace farmer level same as voucher level when voucher level",
            "is higher than farmer level."})
    private boolean useWhenFarmerExist = false;

    @Comment({"if you want to give voucher when farmer removed",
            "you can enable this setting. it will give voucher to player"})
    private boolean giveVoucherWhenRemove = false;

}