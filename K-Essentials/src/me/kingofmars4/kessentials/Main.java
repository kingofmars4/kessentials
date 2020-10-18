package me.kingofmars4.kessentials;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.kingofmars4.kessentials.commands.gamemodes.Fly;
import me.kingofmars4.kessentials.commands.gamemodes.Gamemode;
import me.kingofmars4.kessentials.commands.gamemodes.God;
import me.kingofmars4.kessentials.commands.statsrelated.Heal;
import me.kingofmars4.kessentials.commands.teleports.Tp;
import me.kingofmars4.kessentials.commands.time.ChangeTime;
import me.kingofmars4.kessentials.config.messagesConfig;
import me.kingofmars4.kessentials.config.normalConfig;
import me.kingofmars4.kessentials.utils.Messages;
import me.kingofmars4.kessentials.utils.Settings;
import me.kingofmars4.kessentials.utils.U;

public class Main extends JavaPlugin implements CommandExecutor {
	
	private normalConfig config = new normalConfig(this);
	private messagesConfig messages = new messagesConfig(this);
	PluginManager pm = getServer().getPluginManager();
	public static Plugin plugin; public static Plugin getPlugin(){return plugin;}
	
	@Override
	public void onEnable() {
		plugin = this;
		
		reloadConfig();
		ligarComandos();
		ligarListeners();
		
		if (!loadSettings()) {
    		getLogger().info("Config files failed to load!");
    		this.getServer().getPluginManager().disablePlugin(this);
    		return;
    	}
		
		getLogger().info(this.getDescription().getFullName() + " by KingOfMars4 has been enabled!");
	}
	
	 public void reloadConfig() {
	        if (!getDataFolder().exists()) getDataFolder().mkdirs();  config.reload();
	        if (!getDataFolder().exists()) getDataFolder().mkdirs();  messages.reload();
	    }
	 
	 public void ligarComandos() {
		 getCommand("gmc").setExecutor(new Gamemode());
		 getCommand("gms").setExecutor(new Gamemode());
		 getCommand("gme").setExecutor(new Gamemode());
		 
		 getCommand("heal").setExecutor(new Heal());
		 getCommand("feed").setExecutor(new Heal());
		 
		 getCommand("fly").setExecutor(new Fly());
		 getCommand("god").setExecutor(new God());
		 
		 getCommand("timeset").setExecutor(new ChangeTime());
		 getCommand("sun").setExecutor(new ChangeTime());
		 getCommand("day").setExecutor(new ChangeTime());
		 getCommand("night").setExecutor(new ChangeTime());
		 
		 getCommand("tp").setExecutor(new Tp());
	 }
	 
	 public void ligarListeners() {
		 pm.registerEvents(new Fly(), this);
		 pm.registerEvents(new God(), this);
	 }
	 

	 private boolean loadSettings() {
		 
		 	//CONFIG.YML
		 	Messages.pluginPrefix = U.color(config.getConfig().getString("pluginPrefix"));
		 	Settings.enableServerPrefix = config.getConfig().getConfigurationSection("Options.").getString("enableServerPrefix");
			
			
			// MESSAGES.YML
			Messages.noPermission = U.color(messages.getConfig().getConfigurationSection("Messages").getString("noPermission"));
			Messages.notAvaible = U.color(messages.getConfig().getConfigurationSection("Messages").getString("notAvaible"));
			
			
			// LOAD OPTIONS 
			if (Settings.enableServerPrefix.equalsIgnoreCase("false")) Messages.pluginPrefix = "";

			return true;
		}
	
	 
	@Override
	 public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equals("essentials")) { if (args.length>0) { switch(args[0].toLowerCase()) {
				
					case "reload":
						if (p.hasPermission("essentials.reload")) {
							 
							reloadConfig();
							loadSettings();
							p.sendMessage(Messages.pluginPrefix + U.color("&9Config reloaded &asuccessfully&9!"));
						 } else {
							p.sendMessage(Messages.noPerm);
						}
						
					
				}
			} else {
				if (p.hasPermission("essentials.info")) {
					 p.sendMessage(U.color("&6-------> K-ESSENTIALS <-------"));
					 p.sendMessage(U.color("         &9Plugin Version: &a" + this.getDescription().getVersion()));
					 p.sendMessage(U.color("         &9API Version: &a" + this.getDescription().getAPIVersion()));
					 p.sendMessage(U.color("         &9Made by: &a" + this.getDescription().getAuthors()));
					 p.sendMessage(U.color("&6-------> K-ESSENTIALS <-------"));
				 } else {
					 p.sendMessage(Messages.noPerm);
				 }
			}
			
			return true;
		 }
		return false;
	 }
}
