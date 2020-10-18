package me.kingofmars4.kessentials.commands.statsrelated;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.kingofmars4.kessentials.Main;
import me.kingofmars4.kessentials.utils.Messages;
import me.kingofmars4.kessentials.utils.U;

public class Heal implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (cmd.getName().equalsIgnoreCase("feed")) {
				if (p.hasPermission("essentials.feed")) {
					if (args.length == 1) {
						Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
						
						
						if (!(t == null)) {
							p.sendMessage(Messages.pluginPrefix + U.color("&9You have fed &a%p").replaceAll("%p", t.getName()));
							t.setFoodLevel(20);
							t.sendMessage(Messages.pluginPrefix + U.color("&9You have been satiated"));
						} else { p.sendMessage(Messages.argUnavaible(args[0])); }
						return true;
					}
					
					p.sendMessage(Messages.pluginPrefix + U.color("&9You have been satiated"));
					p.setFoodLevel(20);
					
					return true;
				} else { p.sendMessage(Messages.noPerm); }
			}

			if (p.hasPermission("essentials.heal")) {
				if (args.length == 1) {
					Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
					
					
					if (!(t == null)) {
						p.sendMessage(Messages.pluginPrefix + "&aYou have healed &9%p".replaceAll("%p", t.getName()));
						t.setHealth(p.getMaxHealth());
						t.setFoodLevel(20);
						t.sendMessage(Messages.pluginPrefix + "&aYou have been healed");
					} else { p.sendMessage(Messages.argUnavaible(args[0])); }
					return true;
				}
				
				p.sendMessage(Messages.pluginPrefix + "&aYou have been healed");
				p.setHealth(p.getMaxHealth());
				p.setFoodLevel(20);
				
				return true;
			} else { p.sendMessage(Messages.noPerm); }
			
			
			
		} else { sender.sendMessage(Messages.mustBePlayer); return true;}
		
		
		return false;
	}
}
