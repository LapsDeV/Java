package fr.laps.moderation.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ReportEvents implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();

        switch(e.getCurrentItem().getType()){

            case IRON_SWORD:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cForceField")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case BOW:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSpamBow")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case DIAMOND_SWORD:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cKill Aura")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case STICK:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAnti Kb")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case LEATHER_BOOTS:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cSpeed")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case BLAZE_POWDER:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cReach")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case DIAMOND_ORE:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cX-Ray")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case FEATHER:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cFly")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case FLINT_AND_STEEL:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cCoup Critique")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case POTION:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAuto Potion")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case DIAMOND_CHESTPLATE:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cAuto Armor")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;

            case ARROW:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§cBow Aimbot")){
                    e.setCancelled(true);
                    player.closeInventory();
                    sendToMods(e.getCurrentItem().getItemMeta().getDisplayName(), e.getInventory().getName().substring(12));
                    player.sendMessage("§aVous avez bien signalé ce joueur !");
                }
                break;




            default: break;

        }
    }

    private void sendToMods(String reason, String targetName) {
        for(Player players : Bukkit.getOnlinePlayers()){
            if(players.hasPermission("mod.receive")){
                players.sendMessage("§bLe joueur §a" + targetName + " §ba été signalé pour : " + reason);
            }
        }
    }
}