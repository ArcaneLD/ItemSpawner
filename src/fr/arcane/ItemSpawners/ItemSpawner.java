package fr.arcane.ItemSpawners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;



public class ItemSpawner extends JavaPlugin {

    public void onEnable() {
        System.out.println("[ItemSpawner] Started.");
        Bukkit.getServer().getPluginManager().registerEvents(new ItemSpawnerListener(), (Plugin) this);
        getCommand("credit").setExecutor(new CreditsCommand());
    }

    public void onDisable() {
        System.out.println("[ItemSpawner] Stoped");
    }

}
