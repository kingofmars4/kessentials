package me.kingofmars4.kessentials.commands.teleports;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kingofmars4.kessentials.Main;
import me.kingofmars4.kessentials.utils.Messages;
import me.kingofmars4.kessentials.utils.U;

public class Tp implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (p.hasPermission("essentials.tp")) {
				 
				if (args.length == 1) {
					Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
					
					if (!(t == null)) {
						
						p.teleport(t);
						p.sendMessage(Messages.pluginPrefix + U.color("&9Sussecuflly teleported to &a%p&9!".replaceAll("%p", t.getName())));
						
					} else { p.sendMessage(Messages.argUnavaible(args[0])); }
					return true;
				} else {
					p.sendMessage(Messages.pluginPrefix + U.color("&9Please specify a player to teleport!"));
				}
				return true;
			} else { p.sendMessage(Messages.noPerm); }
			
		} else { sender.sendMessage(Messages.pluginPrefix+U.color("&9You must be a player in order to use this command.")); return true;}
		
		return false;
	}

}

