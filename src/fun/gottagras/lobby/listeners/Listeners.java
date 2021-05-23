package fun.gottagras.lobby.listeners;

import fun.gottagras.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listeners implements Listener {
    private Main main;
    public Listeners(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (player.getGameMode() != GameMode.CREATIVE)
        {
            event.setCancelled(true);
        }
        else event.setCancelled(false);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
        if (event.getCause() == EntityDamageEvent.DamageCause.VOID)
        {
            event.setCancelled(false);
        }
        else event.setCancelled(true);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();
        player.setFoodLevel(20);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        player.setGameMode(GameMode.SURVIVAL);
        player.setHealthScale(1);
        player.setHealth(1);
        player.setFoodLevel(20);
        main.clearEffect(player);
        main.clearStuff(player);
        player.getInventory().setItem(4, main.navigator());

        // TP SPAWN
        if (player.getUniqueId().toString().equalsIgnoreCase("85273375-464a-43e3-b68c-90b5b297db13") || player.getUniqueId().toString().equalsIgnoreCase("5c90273c-8e95-4186-8a2f-c44152f272fc"))
        {
            Location prison = new Location(Bukkit.getWorld("world_nether"), -16.5, 96, 15.5);
            player.teleport(prison);
        }
        else
        {
            Location spawn = new Location(Bukkit.getWorld("world_nether"), -16.5, 92.5, 18.5, 180, 0);
            player.teleport(spawn);
        }
    }
}
