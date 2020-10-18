package me.kingofmars4.kessentials.commands.gamemodes;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import me.kingofmars4.kessentials.Main;
import me.kingofmars4.kessentials.utils.Messages;
import me.kingofmars4.kessentials.utils.U;

public class Fly implements CommandExecutor, Listener {
	
	public static ArrayList<Player> flying = new ArrayList<Player>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (p.hasPermission("essentials.fly")) {
				
				if (args.length == 1) {
					Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
					
					if (!(t == null)) {
						if (!flying.contains(t)) {	
							
							t.setAllowFlight(true);
							flying.add(t);
							p.sendMessage(Messages.pluginPrefix +U.color("&9Flying mode has been &aenabled&9 for &e%p&9!").replaceAll("%p", t.getName()));
							t.sendMessage(Messages.pluginPrefix + U.color("&9Flying mode has been &aenabled&9!"));
							
							return true;
						} else {
							
							if (t.isFlying())t.setFlying(false); 
							t.setAllowFlight(true);
							flying.remove(t);
							p.sendMessage(Messages.pluginPrefix +U.color("&9Flying mode has been &cdisabled&9 for &e%p&9!").replaceAll("%p", t.getName()));
							t.sendMessage(Messages.pluginPrefix + U.color("&9Flying mode has been &cdisabled&9!"));
							
							return true;
						}
					} else { p.sendMessage(Messages.argUnavaible(args[0])); }
					return true;
				}
				
				
				if (!flying.contains(p)) {	
					p.setAllowFlight(true);
					flying.add(p);
					p.sendMessage(Messages.pluginPrefix + U.color("&9Flying mode has been &aenabled&9!"));
					
					return true;
				} else {
					if (p.isFlying())p.setFlying(false); 
					p.setAllowFlight(false);
					flying.remove(p);
					p.sendMessage(Messages.pluginPrefix + U.color("&9Flying mode has been &cdisabled&9!"));
					
					return true;
				}
				
			} else { p.sendMessage(Messages.noPerm); }
		} else { sender.sendMessage(Messages.pluginPrefix+U.color("&9You must be a player in order to use this command.")); return true;}
		return false;
	}
	
	
	@EventHandler
	public void changeGamemode(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().equalsIgnoreCase("gamemode survival")) {
			Player p = e.getPlayer();
			if (Fly.flying.contains(p)) {
				if (p.isFlying())p.setFlying(false); 
				p.setAllowFlight(true); 
				Fly.flying.remove(p);
			}
		}
	}
}
