package me.kingofmars4.kessentials.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;

import me.kingofmars4.kessentials.Main;

public class messagesConfig {
	private Main plugin;
	private CommentedYamlConfiguration config;
	private String newline = System.getProperty("line.separator");
	 
	public messagesConfig(Main plugin){
		this.plugin = plugin;
	}
	
	public void reload(){
		loadConfig();
	}
	
    private void loadConfig(){ 
        File f = new File(plugin.getDataFolder(), "messages.yml"); 
         
        if(!f.exists()) { 
            try { 
                f.createNewFile(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
         
        config = new CommentedYamlConfiguration();        

        try { 
            config.load(f); 
        } catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } catch (InvalidConfigurationException e) { 
            e.printStackTrace(); 
        } 
  	
    	addComment("Messages",
				"####################",
				"    # Messages #",
				"####################");
    	
    	addComment("Messages.noPermission","# Message shown when player does not have enough permissions.");
    	addDefault("Messages.noPermission", "&cYou lack permissions!");
    	
    	addComment("Messages.notAvaible", newline, "# Message shown when player targeted is not avaible.", "# VARIABLES: %p for player name");
    	addDefault("Messages.notAvaible", "&9%p &cIs currently not avaible!");
    	
    	
    	
        // Write back config 
        try { 
            config.save(f); 
        } catch (IOException e) { 
            e.printStackTrace(); 
        } 
    } 
	
	public CommentedYamlConfiguration getConfig() {	
		return config;
	}
	
	private boolean hasPath(String path) {
		return config.isSet(path);
	}
	
	private void addComment(String path, String... comment) {
			config.addComment(path, comment);		
	}
	
	private void addDefault(String path, Object defaultValue) {
		if (path.equals("Version"))
			config.set(path, plugin.getDescription().getVersion());
		if (!hasPath(path))
			config.set(path, defaultValue);		
	}
}