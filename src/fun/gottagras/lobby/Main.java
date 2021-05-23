package fun.gottagras.lobby;

import fun.gottagras.lobby.listeners.Listeners;
import fun.gottagras.lobby.menus.ServerList;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        // BUNGEE CORD
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        INSTANCE = this;

        // LISTENERS
        getServer().getPluginManager().registerEvents(new Listeners(this), this);

        // WORLD OPTIONS
        World lobby = Bukkit.getWorld("world");
        lobby.setDifficulty(Difficulty.PEACEFUL);
        lobby.setWaterAnimalSpawnLimit(0);
        lobby.setAnimalSpawnLimit(0);
        lobby.setMonsterSpawnLimit(0);
        lobby.setAmbientSpawnLimit(0);

        // MENUS
        getServer().getPluginManager().registerEvents(new ServerList(this), this);
    }

    public ItemStack navigator()
    {
        ItemStack itemStack = new ItemStack(Material.COMPASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§1Menu");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void clearStuff(Player player)
    {
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().clear();
        player.setLevel(0);
        player.setExp(0);
    }

    public void clearEffect(Player player)
    {
        Collection<PotionEffect> effect = player.getActivePotionEffects();
        for (PotionEffect potionEffect : effect)
        {
            PotionEffectType potionType = potionEffect.getType();
            player.removePotionEffect(potionType);
        }
    }

    public static Main INSTANCE;
}
