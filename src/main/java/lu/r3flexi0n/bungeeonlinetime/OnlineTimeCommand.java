package lu.r3flexi0n.bungeeonlinetime;

import lu.r3flexi0n.bungeeonlinetime.utils.Language;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class OnlineTimeCommand extends Command {

    OnlineTimeCommand(String command, String permission, String... aliases) {
        super(command, permission, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(Language.ONLY_PLAYER));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;

        if (args.length == 0) {

            if (!player.hasPermission("onlinetime.own")) {
                player.sendMessage(new TextComponent(Language.NO_PERMISSION));
                return;
            }

            ProxyServer.getInstance().getScheduler().runAsync(BungeeOnlineTime.INSTANCE, () -> {
                try {

                    OnlineTime onlineTime = BungeeOnlineTime.SQL.getOnlineTime(player.getUniqueId());
                    if (onlineTime == null) {
                        player.sendMessage(new TextComponent(Language.PLAYER_NOT_FOUND
                                .replace("%PLAYER%", player.getName())));
                        return;
                    }

                    long seconds = onlineTime.getTime() / 1000;
                    int hours = (int) (seconds / 3600);
                    int days = (int) (seconds / 86400);
                    int minutes = (int) ((seconds % 3600) / 60);

                    player.sendMessage(new TextComponent(Language.ONLINE_TIME
                            .replace("%PLAYER%", player.getName())
                            .replace("%DAYS%", String.valueOf(days))
                            .replace("%HOURS%", String.valueOf(hours))
                            .replace("%MINUTES%", String.valueOf(minutes))));

                } catch (Exception ex) {
                    player.sendMessage(new TextComponent(Language.ERROR));
                    ex.printStackTrace();
                }
            });

        } else if (args.length == 2 && args[0].equals("get")) {

            if (!player.hasPermission("onlinetime.others")) {
                player.sendMessage(new TextComponent(Language.NO_PERMISSION));
                return;
            }

            ProxyServer.getInstance().getScheduler().runAsync(BungeeOnlineTime.INSTANCE, () -> {
                try {

                    OnlineTime onlineTime = BungeeOnlineTime.SQL.getOnlineTime(args[1]);
                    if (onlineTime == null) {
                        player.sendMessage(new TextComponent(Language.PLAYER_NOT_FOUND
                                .replace("%PLAYER%", args[1])));
                        return;
                    }

                    long seconds = onlineTime.getTime() / 1000;
                    int hours = (int) (seconds / 3600);
                    int days = (int) (seconds / 86400);
                    int minutes = (int) ((seconds % 3600) / 60);

                    player.sendMessage(new TextComponent(Language.ONLINE_TIME
                            .replace("%PLAYER%", onlineTime.getName())
                            .replace("%DAYS%", String.valueOf(days))
                            .replace("%HOURS%", String.valueOf(hours))
                            .replace("%MINUTES%", String.valueOf(minutes))));

                } catch (Exception ex) {
                    player.sendMessage(new TextComponent(Language.ERROR));
                    ex.printStackTrace();
                }
            });

        } else if (args.length == 1 && args[0].equalsIgnoreCase("top")) {

            if (!player.hasPermission("onlinetime.top")) {
                player.sendMessage(new TextComponent(Language.NO_PERMISSION));
                return;
            }

            player.sendMessage(new TextComponent(Language.TOP_TIME_LOADING));

            ProxyServer.getInstance().getScheduler().runAsync(BungeeOnlineTime.INSTANCE, () -> {
                try {

                    StringBuilder builder = new StringBuilder();
                    builder.append(Language.TOP_TIME_ABOVE);
                    builder.append("\n");
                    for (OnlineTime onlineTimes : BungeeOnlineTime.SQL.getTopOnlineTimes(BungeeOnlineTime.TOP_ONLINETIMES_LIMIT)) {

                        long seconds = onlineTimes.getTime() / 1000;
                        int hours = (int) (seconds / 3600);
                        int days = (int) (seconds / 86400);
                        int minutes = (int) ((seconds % 3600) / 60);

                        builder.append(Language.TOP_TIME
                                .replace("%PLAYER%", onlineTimes.getName())
                                .replace("%DAYS%", String.valueOf(days))
                                .replace("%HOURS%", String.valueOf(hours))
                                .replace("%MINUTES%", String.valueOf(minutes)));
                        builder.append("\n");
                    }
                    builder.append(Language.TOP_TIME_BELOW);

                    player.sendMessage(new TextComponent(builder.toString()));

                } catch (Exception ex) {
                    player.sendMessage(new TextComponent(Language.ERROR));
                    ex.printStackTrace();
                }
            });

        } else if (args.length == 1 && args[0].equalsIgnoreCase("rankup")){
            if (!player.hasPermission("onlinetime.rankup")){
                player.sendMessage(new TextComponent(Language.NO_PERMISSION));
                return;
            }

            ProxyServer.getInstance().getScheduler().runAsync(BungeeOnlineTime.INSTANCE, () -> {
                try {

                    OnlineTime onlineTime = BungeeOnlineTime.SQL.getOnlineTime(player.getUniqueId());
                    if (onlineTime == null) {
                        player.sendMessage(new TextComponent(Language.PLAYER_NOT_FOUND
                                .replace("%PLAYER%", player.getName())));
                        return;
                    }

                    long seconds = onlineTime.getTime() / 1000;
                    int hours = (int) (seconds / 3600);
                    int days = (int) (seconds / 86400);
                    int minutes = (int) ((seconds % 3600) / 60);

                    if (days == 60){
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + player.getName() + " group set elder");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb networksync");

                        player.sendMessage(new TextComponent(Language.PLAYER_RANKUP
                                .replace("%PLAYER%", player.getName())
                                .replace("%DAYS%", String.valueOf(days))
                                .replace("%HOURS%", String.valueOf(hours))
                                .replace("%MINUTES%", String.valueOf(minutes))));
                    } else if (days == 20){
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + player.getName() + " group set mayor");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb networksync");

                        player.sendMessage(new TextComponent(Language.PLAYER_RANKUP
                                .replace("%PLAYER%", player.getName())
                                .replace("%DAYS%", String.valueOf(days))
                                .replace("%HOURS%", String.valueOf(hours))
                                .replace("%MINUTES%", String.valueOf(minutes))));
                    } else if (days == 10){
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + player.getName() + " group set steward");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb networksync");

                        player.sendMessage(new TextComponent(Language.PLAYER_RANKUP
                                .replace("%PLAYER%", player.getName())
                                .replace("%DAYS%", String.valueOf(days))
                                .replace("%HOURS%", String.valueOf(hours))
                                .replace("%MINUTES%", String.valueOf(minutes))));
                    } else if (days == 1){
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb user " + player.getName() + " group set commoner");
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), "lpb networksync");

                        player.sendMessage(new TextComponent(Language.PLAYER_RANKUP
                                .replace("%PLAYER%", player.getName())
                                .replace("%DAYS%", String.valueOf(days))
                                .replace("%HOURS%", String.valueOf(hours))
                                .replace("%MINUTES%", String.valueOf(minutes))));
                    } else {
                        player.sendMessage(new TextComponent(Language.NOT_ENOUGH_ONTIME
                                .replace("%PLAYER%", player.getName())
                                .replace("%DAYS%", String.valueOf(days))
                                .replace("%HOURS%", String.valueOf(hours))
                                .replace("%MINUTES%", String.valueOf(minutes))));
                    }

                } catch (Exception ex) {
                    player.sendMessage(new TextComponent(Language.ERROR));
                    ex.printStackTrace();
                }
            });

        } else if (args.length == 1 && args[0].equalsIgnoreCase("resetall")) {

            if (!player.hasPermission("onlinetime.resetall")) {
                player.sendMessage(new TextComponent(Language.NO_PERMISSION));
                return;
            }

            ProxyServer.getInstance().getScheduler().runAsync(BungeeOnlineTime.INSTANCE, () -> {
                try {

                    BungeeOnlineTime.SQL.resetAll();
                    player.sendMessage(new TextComponent(Language.RESET_ALL));

                } catch (Exception ex) {
                    player.sendMessage(new TextComponent(Language.ERROR));
                    ex.printStackTrace();
                }
            });

        } else if (args.length == 2 && args[0].equalsIgnoreCase("reset")) {

            if (!player.hasPermission("onlinetime.reset")) {
                player.sendMessage(new TextComponent(Language.NO_PERMISSION));
                return;
            }

            ProxyServer.getInstance().getScheduler().runAsync(BungeeOnlineTime.INSTANCE, () -> {
                try {

                    BungeeOnlineTime.SQL.reset(args[1]);
                    player.sendMessage(new TextComponent(Language.RESET_PLAYER
                            .replace("%PLAYER%", args[1])));

                } catch (Exception ex) {
                    player.sendMessage(new TextComponent(Language.ERROR));
                    ex.printStackTrace();
                }
            });

        } else {
            player.sendMessage(new TextComponent("§7Commands:"));
            player.sendMessage(new TextComponent("§7/ontime"));
            player.sendMessage(new TextComponent("§7/ontime get <player>"));
            player.sendMessage(new TextComponent("§7/ontime top"));
            player.sendMessage(new TextComponent("§7/ontime rankup"));
        }
    }
}
