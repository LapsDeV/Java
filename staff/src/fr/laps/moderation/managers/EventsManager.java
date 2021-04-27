package fr.laps.moderation.managers;

import fr.laps.moderation.Main;

import fr.laps.moderation.listeners.ModCancels;
import fr.laps.moderation.listeners.ReportEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class EventsManager {

    public void registers(){
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ReportEvents(), Main.getInstance());
        pm.registerEvents(new ModCancels(), Main.getInstance());
    }
}