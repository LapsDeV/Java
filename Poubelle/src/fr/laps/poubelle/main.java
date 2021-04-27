package fr.laps.poubelle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class main
        extends JavaPlugin
        implements Listener
{


    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "le plugin poubelle est démarer");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED +
                    "Seulement un joueur peut éxécuter la commande.");
            return true;
        }
        Player p = (Player)sender;
        if (cmd.getName().equalsIgnoreCase("poubelle") && p.hasPermission("Trash.Use"))
        {
            Inventory inv1 = Bukkit.getServer().createInventory(p, 54, ChatColor.GOLD + "Poubelle");
            p.openInventory(inv1);
            return true;
        }
        return false;
    }
}