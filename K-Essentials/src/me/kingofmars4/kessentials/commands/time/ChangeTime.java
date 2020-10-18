package me.kingofmars4.kessentials.commands.time;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import me.kingofmars4.kessentials.utils.Messages;
import me.kingofmars4.kessentials.utils.U;

public class ChangeTime implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			if (p.hasPermission("essentials.time")) {
				 
				// TIMESET
				if (cmd.getName().equalsIgnoreCase("timeset")) {
					if ( args.length == 0 || args.length>2) {
						p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/timeset (ticks) {world}"));
					} else {
						if (args.length == 1) {
							if (isInt(args[0])) {
								p.getWorld().setTime(Integer.parseInt(args[0]));
								p.sendMessage(Messages.pluginPrefix + U.color("&9Time has been changed to &e%t ticks &9in the world &6'%w' &9!".replaceAll("%t", args[0]).replaceAll("%w", p.getWorld().getName())));
							} else {
								p.sendMessage(Messages.pluginPrefix + U.color("&cArgument &6'ticks' &cmust be an number!"));
								p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/timeset (ticks) {world}"));
							}
						}
						
						if (args.length == 2) {
							World w = Bukkit.getWorld(args[1]);
							
							if (w != null) {
								if (isInt(args[0])) {
									w.setTime(Integer.parseInt(args[0]));
									p.sendMessage(Messages.pluginPrefix + U.color("&9Time has been changed to &e%t ticks &9in the world &6'%w' &9!".replaceAll("%t", args[0]).replaceAll("%w", w.getName())));
								} else {
									p.sendMessage(Messages.pluginPrefix + U.color("&cArgument &6'ticks' &cmust be an number!"));
									p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/timeset (ticks) {world}"));
								}
							} else {
								p.sendMessage(Messages.pluginPrefix + U.color("&6'%w' &cis an invalid world!").replaceAll("%w", args[1]));
								p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/timeset (ticks) {world}"));
							}
						}
					}
				}
				
				
				// SUN
				if (cmd.getName().equalsIgnoreCase("sun")) {
					
					if (args.length>1) {
						p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/sun {world}"));
					}
					
					if (args.length == 0) {
						World w = p.getWorld();
						
						w.setTime(10000);
						w.setStorm(false); 
						w.setThundering(false); 
						p.sendMessage(Messages.pluginPrefix + U.color("&9Weather and time in world &6'%w' &9has been set to &eday&9 and &eclear&9!").replaceAll("%w", w.getName()));
					} 
					
					if (args.length == 1) {
						World w = Bukkit.getWorld(args[0]);

						if (w != null) {	
							w.setTime(10000);
							w.setStorm(false); 
							w.setThundering(false); 
							p.sendMessage(Messages.pluginPrefix + U.color("&9Weather and time in world &6'%w' &9has been set to &eday&9 and &eclear&9!").replaceAll("%w", w.getName()));
						} else {
							p.sendMessage(Messages.pluginPrefix + U.color("&6'%w' &cis an invalid world!").replaceAll("%w", args[0]));
							p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/sun {world}"));
						}
					}
				}
				
				
				// DAY
				if (cmd.getName().equalsIgnoreCase("day")) {
					
					if (args.length>1) {
						p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/day {world}"));
					}
					
					if (args.length == 0) {
						World w = p.getWorld();
						
						w.setTime(10000); 
						p.sendMessage(Messages.pluginPrefix + U.color("&9Time in world &6'%w' &9has been set to &eday&9!").replaceAll("%w", w.getName()));
					} 
					
					if (args.length == 1) {
						World w = Bukkit.getWorld(args[0]);

						if (w != null) {	
							w.setTime(10000);
							p.sendMessage(Messages.pluginPrefix + U.color("&9Time in world &6'%w' &9has been set to &eday &9!").replaceAll("%w", w.getName()));
						} else {
							p.sendMessage(Messages.pluginPrefix + U.color("&6'%w' &cis an invalid world!").replaceAll("%w", args[0]));
							p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/day {world}"));
						}
					}
				}
				
				
				// NIGHT
				if (cmd.getName().equalsIgnoreCase("night")) {
					
					if (args.length>1) {
						p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/night {world}"));
					}
					
					if (args.length == 0) {
						World w = p.getWorld();
						
						w.setTime(20000); 
						p.sendMessage(Messages.pluginPrefix + U.color("&9Time in world &6'%w' &9has been set to &enight&9!").replaceAll("%w", w.getName()));
					} 
					
					if (args.length == 1) {
						World w = Bukkit.getWorld(args[0]);

						if (w != null) {	
							w.setTime(20000);
							p.sendMessage(Messages.pluginPrefix + U.color("&9Time in world &6'%w' &9has been set to &enight &9!").replaceAll("%w", w.getName()));
						} else {
							p.sendMessage(Messages.pluginPrefix + U.color("&6'%w' &cis an invalid world!").replaceAll("%w", args[0]));
							p.sendMessage(Messages.pluginPrefix + U.color("&cCorrect usage: &e/night {world}"));
						}
					}
				}
				
				return true;
			} else { p.sendMessage(Messages.noPerm); }
			
		} else { sender.sendMessage(Messages.mustBePlayer); return true;}
		
		return false;
	}
	
	private boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
}
