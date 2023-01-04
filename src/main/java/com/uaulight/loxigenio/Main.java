package com.uaulight.loxigenio;

import com.google.common.io.Resources;
import com.uaulight.loxigenio.commands.CapaceteCmd;
import com.uaulight.loxigenio.events.OxigenioE;
import com.uaulight.loxigenio.utils.ActionBar;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public final class Main extends JavaPlugin {

    public static Main plugin;
    public static ArrayList<String> worlds;

    public void onEnable() {

        Bukkit.getConsoleSender().sendMessage("§a[LOxigenio] Plugin ativado com sucesso.");
        plugin = this;
        saveDefaultConfig();
        try {
            File file = new File(getDataFolder() + File.separator, "config.yml");
            String allText = Resources.toString(file.toURI().toURL(), StandardCharsets.UTF_8);
            getConfig().loadFromString(allText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        eventos();

        worlds = (ArrayList<String>) getConfig().getStringList("Worlds");
        getCommand("capacete").setExecutor(new CapaceteCmd());
    }

    public void eventos() {

        Bukkit.getPluginManager().registerEvents(new OxigenioE(), this);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()) {
                    if (worlds.contains(p.getWorld().getName()))
                        if (p.getInventory().getHelmet() == null ||
                                (p.getInventory().getHelmet() != null && p.getInventory().getHelmet().getType() == Material.AIR) ||
                                (!p.getInventory().getHelmet().isSimilar(OxigenioE.Oxigenio))) {
                            ActionBar.send(p, getConfig().getString("Mensagens.Desativado").replace("&", "§"));
                            p.damage(1);
                            if (getConfig().getBoolean("Super_pulo") == true) {
                                p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 9999, 1));
                            }
                        } else {
                            if (p.hasPotionEffect(PotionEffectType.JUMP)){
                                p.removePotionEffect(PotionEffectType.JUMP);
                            }
                            ActionBar.send(p, getConfig().getString("Mensagens.Ativado").replace("&", "§"));
                        }
                }

            }
        }.runTaskTimer(this, 10, 20);

    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage("§c[LOxigenio] Plugin desativado com sucesso.");

    }
}
