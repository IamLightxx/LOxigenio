package com.uaulight.loxigenio.commands;

import com.uaulight.loxigenio.Main;
import com.uaulight.loxigenio.events.OxigenioE;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CapaceteCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (p.hasPermission(Main.plugin.getConfig().getString("Permissao"))) {
            if (p.getInventory().getHelmet() == null || (p.getInventory().getHelmet() != null &&
                    p.getInventory().getHelmet().getType() == Material.AIR)) {

                if (p.getItemInHand() == null || (p.getInventory().getItemInHand() != null &&
                        p.getInventory().getItemInHand().getType() == Material.AIR)) {
                    p.sendMessage(Main.plugin.getConfig().getString("Mensagens.Sem_item_mao").replace("&", "§"));
                    return false;
                }

                if (p.getItemInHand().isSimilar(OxigenioE.Oxigenio)) {
                    p.getInventory().setHelmet(OxigenioE.Oxigenio);
                    p.getInventory().setItemInHand(new ItemStack(Material.AIR));
                    p.sendMessage(Main.plugin.getConfig().getString("Mensagens.Capacete_equipado").replace("&", "§"));
                    return false;
                } else {

                    p.getInventory().setHelmet(OxigenioE.Oxigenio);
                    p.sendMessage(Main.plugin.getConfig().getString("Mensagens.Capacete_equipado").replace("&", "§"));

                }
            } else if (p.getInventory().getHelmet().isSimilar(OxigenioE.Oxigenio)) {
                p.sendMessage(Main.plugin.getConfig().getString("Mensagens.Capacete_ja_equipado").replace("&", "§"));
            } else {
                p.sendMessage(Main.plugin.getConfig().getString("Mensagens.Capacete_diferente").replace("&", "§"));
            }

        } else {
            p.sendMessage(Main.plugin.getConfig().getString("Mensagens.Sem_perm"));
        }
            return false;
        }
    }
