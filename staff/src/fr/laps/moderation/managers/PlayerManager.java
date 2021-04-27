package fr.laps.moderation.managers;

import fr.laps.moderation.Main;
import fr.laps.moderation.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerManager {

    private Player player;
    private ItemStack[] items = new ItemStack[40];
    private boolean vanished;

    public PlayerManager(Player player){
        this.player = player;
        vanished = false;
    }

    public void init(){
        Main.getInstance().getPlayers().put(player.getUniqueId(), this);
        Main.getInstance().getModerateurs().add(player.getUniqueId());
        player.sendMessage("§aVous êtes à présent en mode modération");
        saveInventory();
        player.setAllowFlight(true);
        player.setFlying(true);

        ItemBuilder invSee = new ItemBuilder(Material.PAPER).setName("§eVoir l'inventaire").setLore("§7Clique droit sur un joueur", "§7pour voir son inventaire.");
        ItemBuilder reports = new ItemBuilder(Material.BOOK).setName("§6Voir les signalements").setLore("§7Clique droit sur un joueur", "§7pour voir ses signalements.");
        ItemBuilder freeze = new ItemBuilder(Material.PACKED_ICE).setName("§bFreeze").setLore("§7Clique droit sur un joueur", "§7pour le freeze.");
        ItemBuilder kbTester = new ItemBuilder(Material.STICK).setName("§dTest de recul").setLore("§7Clique gauche sur un joueur", "§7pour tester son recul.").addUnsafeEnchantment(Enchantment.KNOCKBACK, 5);
        ItemBuilder killer = new ItemBuilder(Material.BLAZE_ROD).setName("§cTueur de joueur").setLore("§7Clique droit sur un joueur", "§7pour le tuer.");
        ItemBuilder tpRandom = new ItemBuilder(Material.ARROW).setName("§aTéléportation aléatoire").setLore("§7Clique droit pour se téléporter", "§7aléatoirement sur un joueur.");
        ItemBuilder vanish = new ItemBuilder(Material.BLAZE_POWDER).setName("§2Vanish").setLore("§7Clique droit pour activer/désactiver", "§7le vanish.");

        player.getInventory().setItem(0, invSee.toItemStack());
        player.getInventory().setItem(1, reports.toItemStack());
        player.getInventory().setItem(2, freeze.toItemStack());
        player.getInventory().setItem(3, kbTester.toItemStack());
        player.getInventory().setItem(4, killer.toItemStack());
        player.getInventory().setItem(5, tpRandom.toItemStack());
        player.getInventory().setItem(6, vanish.toItemStack());
    }

    public void destroy(){
        Main.getInstance().getPlayers().remove(player.getUniqueId());
        Main.getInstance().getModerateurs().remove(player.getUniqueId());
        player.getInventory().clear();
        player.sendMessage("§cVous n'êtes maintenant plus en mode modération");
        giveInventory();
        player.setAllowFlight(false);
        player.setFlying(false);
        setVanished(false);
    }

    public static PlayerManager getFromPlayer(Player player){
        return Main.getInstance().getPlayers().get(player.getUniqueId());
    }

    public static boolean isInModerationMod(Player player){
        return Main.getInstance().getModerateurs().contains(player.getUniqueId());
    }

    public ItemStack[] getItems() {
        return items;
    }

    public boolean isVanished() {
        return vanished;
    }

    public void setVanished(boolean vanished){
        this.vanished = vanished;
        if(vanished){
            Bukkit.getOnlinePlayers().forEach(players -> players.hidePlayer(player));
        } else {
            Bukkit.getOnlinePlayers().forEach(players -> players.showPlayer(player));
        }
    }

    public void saveInventory(){
        for(int slot = 0; slot < 36; slot++){
            ItemStack item = player.getInventory().getItem(slot);
            if(item != null){
                items[slot] = item;
            }
        }

        items[36] = player.getInventory().getHelmet();
        items[37] = player.getInventory().getChestplate();
        items[38] = player.getInventory().getLeggings();
        items[39] = player.getInventory().getBoots();

        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }

    public void giveInventory(){
        player.getInventory().clear();

        for(int slot = 0; slot < 36; slot++){
            ItemStack item = items[slot];
            if(item != null){
                player.getInventory().setItem(slot, item);
            }
        }

        player.getInventory().setHelmet(items[36]);
        player.getInventory().setChestplate(items[37]);
        player.getInventory().setLeggings(items[38]);
        player.getInventory().setBoots(items[39]);
    }
}