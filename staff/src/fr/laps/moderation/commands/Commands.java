package fr.laps.moderation.commands;

import fr.laps.moderation.Main;
import fr.laps.moderation.managers.PlayerManager;
import fr.laps.moderation.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("Seul un joueur peut executer cette commande !");
            return false;
        }

        Player player = (Player) sender;

        if(label.equalsIgnoreCase("staff")){
            if(!player.hasPermission("moderation.staff")){
                player.sendMessage("§cVous n'avez pas la permission d'éxecuter cette commande !");
                return false;
            }

            if(PlayerManager.isInModerationMod(player)){
                PlayerManager pm = PlayerManager.getFromPlayer(player);

                Main.getInstance().getModerateurs().remove(player.getUniqueId());
                player.getInventory().clear();
                player.sendMessage("§cVous n'êtes maintenant plus en mode modération");
                pm.giveInventory();
                pm.destroy();
                player.setAllowFlight(false);
                player.setFlying(false);
                return false;
            }

            PlayerManager pm = new PlayerManager(player);
            pm.init();
            Main.getInstance().getModerateurs().add(player.getUniqueId());
            player.sendMessage("§aVous êtes à présent en mode modération");
            pm.saveInventory();
            player.setAllowFlight(true);
            player.setFlying(true);

            ItemBuilder invSee = new ItemBuilder(Material.STICK).setName("§eVoir l'inventaire").setLore("§7Clique droit sur un joueur", "§7pour voir son inventaire.").addUnsafeEnchantment(Enchantment.DURABILITY, 5);
            ItemBuilder freeze = new ItemBuilder(Material.PACKED_ICE).setName("§bFreeze").setLore("§7Clique droit sur un joueur", "§7pour le freeze.").addUnsafeEnchantment(Enchantment.DURABILITY, 5);
            ItemBuilder tpRandom = new ItemBuilder(Material.FEATHER).setName("§aTéléportation aléatoire").setLore("§7Clique droit pour se téléporter", "§7aléatoirement sur un joueur.");
            ItemBuilder vanish = new ItemBuilder(Material.QUARTZ_BLOCK).setName("§2Vanish").setLore("§7Clique droit pour activer/désactiver", "§7le vanish.").addUnsafeEnchantment(Enchantment.DURABILITY, 5);
            ItemBuilder reports = new ItemBuilder(Material.BOOK).setName("§6Voir les signalements").setLore("§7Clique droit sur un joueur", "§7pour voir ses signalements.");

            player.getInventory().setItem(0, invSee.toItemStack());
            player.getInventory().setItem(1, freeze.toItemStack());
            player.getInventory().setItem(4, tpRandom.toItemStack());
            player.getInventory().setItem(8, vanish.toItemStack());
            player.getInventory().setItem(7, reports.toItemStack());
        }

        if(label.equalsIgnoreCase("report")){
            if(args.length != 1){
                player.sendMessage("§cVeuillez saisir le pseudo d'un joueur !");
                return false;
            }

            String targetName = args[0];

            if(Bukkit.getPlayer(targetName) == null){
                player.sendMessage("§cCe joueur n'est pas connecté ou n'existe pas !");
                return false;
            }

            Player target = Bukkit.getPlayer(targetName);

            Inventory inv = Bukkit.createInventory(null, 18, "§bReport: §c" + target.getName());

            inv.setItem(0, new ItemBuilder(Material.IRON_SWORD).setName("§cForceField").toItemStack());
            inv.setItem(1, new ItemBuilder(Material.BOW).setName("§cSpamBow").toItemStack());
            inv.setItem(2, new ItemBuilder(Material.DIAMOND_SWORD).setName("§cKill Aura").toItemStack());
            inv.setItem(3, new ItemBuilder(Material.STICK).setName("§cAnti Kb").toItemStack());
            inv.setItem(4, new ItemBuilder(Material.LEATHER_BOOTS).setName("§cSpeed").toItemStack());
            inv.setItem(5, new ItemBuilder(Material.BLAZE_POWDER).setName("§cReach").toItemStack());
            inv.setItem(6, new ItemBuilder(Material.DIAMOND_ORE).setName("§cX-Ray").toItemStack());
            inv.setItem(7, new ItemBuilder(Material.FEATHER).setName("§cFly").toItemStack());
            inv.setItem(8, new ItemBuilder(Material.FLINT_AND_STEEL).setName("§cCoup Critique").toItemStack());
            inv.setItem(9, new ItemBuilder(Material.POTION).setName("§cAuto Potion").toItemStack());
            inv.setItem(10, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("§cAuto Armor").toItemStack());
            inv.setItem(11, new ItemBuilder(Material.ARROW).setName("§cBow Aimbot").toItemStack());
            player.openInventory(inv);
        }

        return false;
    }
}