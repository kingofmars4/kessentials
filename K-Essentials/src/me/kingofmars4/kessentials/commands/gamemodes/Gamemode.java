package me.kingofmars4.kessentials.commands.gamemodes;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.kingofmars4.kessentials.Main;
import me.kingofmars4.kessentials.utils.Messages;
import me.kingofmars4.kessentials.utils.U;

public class Gamemode implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,  String[] args) {
		
		Player p = (Player) sender;
		
		// CREATIVE
		if (cmd.getName().equalsIgnoreCase("gmc") || cmd.getName().equalsIgnoreCase("gm1")) {
			if (p.hasPermission("kessentials.gmc") || p.hasPermission("kessentials.gamemode")) {
				
				if (args.length == 1) {
					Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
					
					if (!(t == null)) {
						
						if (t.getGameMode().equals(GameMode.CREATIVE)) {
							p.sendMessage(Messages.pluginPrefix+ U.color("&9%p &cis are alredy in &ecreative &cgamemode!".replaceAll("%p", t.getName())));
						} else {
							t.setGameMode(GameMode.CREATIVE);
							t.sendMessage(Messages.pluginPrefix + U.color("&aYour gamemode has been updated to &ecreative"));
							p.sendMessage(Messages.pluginPrefix + U.color("&aYou updated &9%p &agamemode to &ecreative".replaceAll("%p", t.getName())));
						}
						
					} else { p.sendMessage(Messages.argUnavaible(args[0])); }
					return true;
				}
				
				
				if (p.getGameMode().equals(GameMode.CREATIVE)) {
					p.sendMessage(Messages.pluginPrefix+ U.color("&cYou are alredy in &ecreative &cgamemode!"));
				} else {
					p.setGameMode(GameMode.CREATIVE);
					p.sendMessage(Messages.pluginPrefix + U.color("&aYour gamemode has been updated to &ecreative"));
				}
				
			} else { 
				p.sendMessage(Messages.noPerm);
			}
			
			return true;
		}
		
		
		// SURVIVAL
		if (cmd.getName().equalsIgnoreCase("gms") || cmd.getName().equalsIgnoreCase("gm0")) {
			if (p.hasPermission("kessentials.gms") || p.hasPermission("kessentials.gamemode")) {
				
				if (args.length == 1) {
					Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
					
					if (!(t == null)) {
						
						if (t.getGameMode().equals(GameMode.SURVIVAL)) {
							p.sendMessage(Messages.pluginPrefix+ U.color("&9%p &cis are alredy in &esurvival &cgamemode!".replaceAll("%p", t.getName())));
						} else {
							t.setGameMode(GameMode.SURVIVAL);
							t.sendMessage(Messages.pluginPrefix + U.color("&aYour gamemode has been updated to &esurvival"));
							p.sendMessage(Messages.pluginPrefix + U.color("&aYou updated &9%p &agamemode to &esurvival".replaceAll("%p", t.getName())));
							if (Fly.flying.contains(t)) if (t.isFlying())t.setFlying(false); t.setAllowFlight(true); Fly.flying.remove(t);
						}
						
					} else { p.sendMessage(Messages.argUnavaible(args[0])); }
					return true;
				}
				
				
				if (p.getGameMode().equals(GameMode.SURVIVAL)) {
					p.sendMessage(Messages.pluginPrefix+ U.color("&cYou are alredy in &esurvival &cgamemode!"));
				} else {
					p.setGameMode(GameMode.SURVIVAL);
					p.sendMessage(Messages.pluginPrefix + U.color("&aYour gamemode has been updated to &esurvival"));
					if (Fly.flying.contains(p)) if (p.isFlying())p.setFlying(false); p.setAllowFlight(true); Fly.flying.remove(p);
				}
				
			} else { 
				p.sendMessage(Messages.noPerm);
			}
			
			return true;
		}
		
		
		// SPECTATOR
				if (cmd.getName().equalsIgnoreCase("gme") || cmd.getName().equalsIgnoreCase("gm3")) {
					if (p.hasPermission("kessentials.gme") || p.hasPermission("kessentials.gamemode")) {
						
						if (args.length == 1) {
							Player t = Main.getPlugin().getServer().getPlayer(args[0].toLowerCase());
							
							if (!(t == null)) {
								
								if (t.getGameMode().equals(GameMode.SPECTATOR)) {
									p.sendMessage(Messages.pluginPrefix+ U.color("&9%p &cis are alredy in &espectator &cgamemode!".replaceAll("%p", t.getName())));
								} else {
									t.setGameMode(GameMode.SPECTATOR);
									t.sendMessage(Messages.pluginPrefix + U.color("&aYour gamemode has been updated to &espectator"));
									p.sendMessage(Messages.pluginPrefix + U.color("&aYou updated &9%p &agamemode to &espectator".replaceAll("%p", t.getName())));
								}
								
							} else { p.sendMessage(Messages.argUnavaible(args[0])); }
							return true;
						}
						
						
						if (p.getGameMode().equals(GameMode.SPECTATOR)) {
							p.sendMessage(Messages.pluginPrefix+ U.color("&cYou are alredy in &espectator &cgamemode!"));
						} else {
							p.setGameMode(GameMode.SPECTATOR);
							p.sendMessage(Messages.pluginPrefix + U.color("&aYour gamemode has been updated to &espectator"));
						}
						
					} else { 
						p.sendMessage(Messages.noPerm);
					}
					
					return true;
				}
		
		return false;
	}

}
