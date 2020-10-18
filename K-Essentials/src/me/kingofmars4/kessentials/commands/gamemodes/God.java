package me.kingofmars4.kessentials.commands.gamemodes;

import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.kingofmars4.kessentials.Main;
import me.kingofmars4.kessentials.utils.Messages;
import me.kingofmars4.kessentials.utils.U;

public class God implements CommandExecutor, Listener {
	
	public static ArrayList<Player> godMode = new ArrayList<Player>();
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (p.hasPermission("essentials.god")) {
				
				if (args.length == 1) {
					Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
					
					if (!(t == null)) {
						if (!godMode.contains(t)) {	
							
							godMode.add(t);
							p.sendMessage(Messages.pluginPrefix +U.color("&9God mode has been &aenabled&9 for &e%p&9!").replaceAll("%p", t.getName()));
							t.sendMessage(Messages.pluginPrefix + U.color("&9God mode has been &aenabled&9!"));
							
						} else {
							
							godMode.remove(t);
							p.sendMessage(Messages.pluginPrefix +U.color("&9God mode has been &cdisabled&9 for &e%p&9!").replaceAll("%p", t.getName()));
							t.sendMessage(Messages.pluginPrefix + U.color("&9God mode has been &cdisabled&9!"));
							
						}
					} else { p.sendMessage(Messages.argUnavaible(args[0])); }
				}
				
				
				if (!godMode.contains(p)) {	
					godMode.add(p);
					p.sendMessage(Messages.pluginPrefix + U.color("&9God mode has been &aenabled&9!"));
					
				} else {
					godMode.remove(p);
					p.sendMessage(Messages.pluginPrefix + U.color("&9God mode has been &cdisabled&9!"));
				}
				return true;
				
			} else { p.sendMessage(Messages.noPerm); }
		} else { sender.sendMessage(Messages.mustBePlayer); return true;}
		
		return false;
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			
			if (godMode.contains(p)) {
				e.setCancelled(true);
			}
		}
	}

}
