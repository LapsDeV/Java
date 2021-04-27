package fr.laps.moderation.listeners;

import fr.laps.moderation.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if(PlayerManager.isInModerationMod(player)){
            PlayerManager.getFromPlayer(player).destroy();
        }
    }
}