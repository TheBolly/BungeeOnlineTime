package lu.r3flexi0n.bungeeonlinetime.utils;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;

public class Language {

    public static String ONLY_PLAYER = "&7This command can only be executed by players.";
    public static String NO_PERMISSION = "&7You do not have access to this command.";
    public static String ERROR = "&7An error occured.";
    public static String ERROR_SAVING = "&7Error while saving your ontime.";
    public static String PLAYER_NOT_FOUND = "&7Player '&6%PLAYER%&7' was not found.";
    public static String ONLINE_TIME = "&a%PLAYER% &f-> &6%DAYS%&7Days &6%HOURS%&7Hr &6%MINUTES%&7Min";
    public static String TOP_TIME_LOADING = "&7Top 10 players are loading...";
    public static String TOP_TIME_ABOVE = "&f====== &dTop Players &f======";
    public static String TOP_TIME = "&a%PLAYER%&7 &6%DAYS%&7Days &6%HOURS%&7Hr &6%MINUTES%&7Min";
    public static String TOP_TIME_BELOW = "f=======================";
    public static String RESET_ALL = "&7The database has been reset.";
    public static String RESET_PLAYER = "&6%PLAYER%&7's ontime has been reset.";
    public static String NOT_ENOUGH_ONTIME = "&6%PLAYER%&7 not enough ontime.";
    public static String PLAYER_RANKUP = "&6%PLAYER%&7 ranked up.";

    public static void create(Configuration config) {
        addDefault(config, "Language.onlyPlayer", ONLY_PLAYER);
        addDefault(config, "Language.noPermission", NO_PERMISSION);
        addDefault(config, "Language.error", ERROR);
        addDefault(config, "Language.errorSaving", ERROR_SAVING);
        addDefault(config, "Language.playerNotFound", PLAYER_NOT_FOUND);
        addDefault(config, "Language.onlineTime", ONLINE_TIME);
        addDefault(config, "Language.topTimeLoading", TOP_TIME_LOADING);
        addDefault(config, "Language.topTimeAbove", TOP_TIME_ABOVE);
        addDefault(config, "Language.topTime", TOP_TIME);
        addDefault(config, "Language.topTimeBelow", TOP_TIME_BELOW);
        addDefault(config, "Language.resetAll", RESET_ALL);
        addDefault(config, "Language.resetPlayer", RESET_PLAYER);
        addDefault(config, "Language.notEnoughOntime", NOT_ENOUGH_ONTIME);
        addDefault(config, "Language.playerRankup", PLAYER_RANKUP);
    }

    public static void load(Configuration config) {
        ONLY_PLAYER = ChatColor.translateAlternateColorCodes('&', config.getString("Language.onlyPlayer"));
        NO_PERMISSION = ChatColor.translateAlternateColorCodes('&', config.getString("Language.noPermission"));
        ERROR = ChatColor.translateAlternateColorCodes('&', config.getString("Language.error"));
        ERROR_SAVING = ChatColor.translateAlternateColorCodes('&', config.getString("Language.errorSaving"));
        PLAYER_NOT_FOUND = ChatColor.translateAlternateColorCodes('&', config.getString("Language.playerNotFound"));
        ONLINE_TIME = ChatColor.translateAlternateColorCodes('&', config.getString("Language.onlineTime"));
        TOP_TIME_LOADING = ChatColor.translateAlternateColorCodes('&', config.getString("Language.topTimeLoading"));
        TOP_TIME_ABOVE = ChatColor.translateAlternateColorCodes('&', config.getString("Language.topTimeAbove"));
        TOP_TIME = ChatColor.translateAlternateColorCodes('&', config.getString("Language.topTime"));
        TOP_TIME_BELOW = ChatColor.translateAlternateColorCodes('&', config.getString("Language.topTimeBelow"));
        RESET_ALL = ChatColor.translateAlternateColorCodes('&', config.getString("Language.resetAll"));
        RESET_PLAYER = ChatColor.translateAlternateColorCodes('&', config.getString("Language.resetPlayer"));
        NOT_ENOUGH_ONTIME = ChatColor.translateAlternateColorCodes('&', config.getString("Language.notEnoughOntime"));
        PLAYER_RANKUP = ChatColor.translateAlternateColorCodes('&', config.getString("Language.playerRankup"));
    }

    private static void addDefault(Configuration config, String path, Object value) {
        if (!config.contains(path)) {
            config.set(path, value);
        }
    }
}
