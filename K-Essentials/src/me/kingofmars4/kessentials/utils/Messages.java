package me.kingofmars4.kessentials.utils;

public class Messages {

	 public static String pluginPrefix;
	 public static String noPermission;
	 public static String notAvaible;
	 
	 
	 
	 public static String noPerm = pluginPrefix + noPermission;
	 public static String mustBePlayer = pluginPrefix + U.color("&cYou must be a player in order to use that command.");
	 public static String argUnavaible(String p) { return pluginPrefix+notAvaible.replaceAll("%p", p); }
}
