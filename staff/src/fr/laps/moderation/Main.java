package fr.laps.moderation;

import fr.laps.moderation.commands.Commands;
import fr.laps.moderation.listeners.*;
import fr.laps.moderation.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {
    private static Main instance;

    private List<UUID> moderateurs;
    private Map<UUID, PlayerManager> players;
    private Map<UUID, Location> frozenPlayers;

    @Override
    public void onEnable() {
        setup();
    }

    public static Main getInstance() {
        return instance;
    }

    private void setup(){
        instance = this;
        registerEvents();
        registerCommands();
        moderateurs = new ArrayList<>();
        players = new HashMap<>();
        frozenPlayers = new HashMap<>();
    }

    private void registerEvents(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ReportEvents(), this);
        pm.registerEvents(new ModCancels(), this);
        pm.registerEvents(new ModItemsInteract(), this);
        pm.registerEvents(new PlayerChat(), this);
        pm.registerEvents(new PlayerQuit(), this);
    }

    private void registerCommands(){
        getCommand("staff").setExecutor(new Commands());
        getCommand("report").setExecutor(new Commands());
    }

    public List<UUID> getModerateurs() {
        return moderateurs;
    }

    public Map<UUID, PlayerManager> getPlayers() {
        return players;
    }

    public Map<UUID, Location> getFrozenPlayers() {
        return frozenPlayers;
    }

    public boolean isFreeze(Player player){
        return getFrozenPlayers().containsKey(player.getUniqueId());
    }
}