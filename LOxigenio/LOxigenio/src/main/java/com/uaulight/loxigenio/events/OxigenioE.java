package com.uaulight.loxigenio.events;

import com.uaulight.loxigenio.Main;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class OxigenioE implements Listener {

    public static ItemStack Oxigenio;

    public OxigenioE() {
        ArrayList<String> lore = new ArrayList<>();
        for (String s : Main.plugin.getConfig().getStringList("Capacete.Item.Lore")) {
            lore.add(s.replace("&", "§"));
        }

        Oxigenio = new ItemStack(Material.getMaterial(Main.plugin.getConfig().getString("Capacete.Item.Material")));
        ItemMeta meta = Oxigenio.getItemMeta();
        meta.setDisplayName(Main.plugin.getConfig().getString("Capacete.Item.Nome").replace("&", "§"));
        meta.setLore(lore);
        Oxigenio.setItemMeta(meta);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (Main.worlds.contains(p.getWorld().getName()))
            p.getInventory().setHelmet(Oxigenio);

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();

        if (Main.worlds.contains(p.getWorld().getName()))
            p.getInventory().setHelmet(Oxigenio);
    }
}
