package me.kingofmars4.kessentials.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.bukkit.configuration.InvalidConfigurationException;

import me.kingofmars4.kessentials.Main;

public class normalConfig {
	private Main plugin;
	private CommentedYamlConfiguration config;
	@SuppressWarnings("unused")
	private String newline = System.getProperty("line.separator");
	 
	public normalConfig(Main plugin){
		this.plugin = plugin;
	}
	
	public void reload(){
		loadConfig();
	}
	
	// Method to load TownyFlight\config.yml
    private void loadConfig(){ 
        File f = new File(plugin.getDataFolder(), "config.yml"); 
         
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
        
        addComment("Version","# K-Essentials by KingOfMars4.");        
        addDefault("Version", plugin.getDescription().getVersion()); 
    	addComment("pluginPrefix","# Prefix to all messages seen in game.");
    	addDefault("pluginPrefix", "&8[&6K-Essentials&8] ");

    	addComment("Options", newline,
    						"#################",
    						"#    Options    #",
    						"#################");
    	addComment("Options.enableServerPrefix","# If set to true, all messages in-game will have the server prefix that you choose.");
    	addDefault("Options.enableServerPrefix","true");
    	
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