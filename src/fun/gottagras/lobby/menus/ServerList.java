package fun.gottagras.lobby.menus;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fun.gottagras.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerList implements Listener {
    private Main main;
    public ServerList(Main main)
    {
        this.main = main;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event)
    {
        Player player = event.getPlayer();
        if (event.getItem() != null)
        {
            String clickedItem = event.getItem().getItemMeta().getDisplayName();
            String navigatorItem = main.navigator().getItemMeta().getDisplayName();
            if (clickedItem != null)
            {
                if (clickedItem.equals(navigatorItem))
                {
                    player.openInventory(menu());
                }
            }
        }
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null)
        {
            if (inventory.getName().equals(menu().getName()) && clickedItem.getType() != Material.AIR)
            {
                event.setCancelled(true);
                // ITEM NAME
                String clickedItemName = clickedItem.getItemMeta().getDisplayName();
                String uhcName = uhc().getItemMeta().getDisplayName();
                String survivalName = survival().getItemMeta().getDisplayName();

                // BUNGEE
                final ByteArrayDataOutput out = ByteStreams.newDataOutput();

                // CHECK ITEM NAME
                if (clickedItemName.equals(uhcName))
                {
                    out.writeUTF("Connect");
                    out.writeUTF("uhc");
                    player.sendPluginMessage(Main.INSTANCE, "BungeeCord", out.toByteArray());
                }
                else if (clickedItemName.equals(survivalName))
                {
                    out.writeUTF("Connect");
                    out.writeUTF("survival");
                    player.sendPluginMessage(Main.INSTANCE, "BungeeCord", out.toByteArray());
                }
            }
        }
    }

    private Inventory menu()
    {
        Inventory inventory = Bukkit.createInventory(null, 27, "§7Server List");
        inventory.setItem(12, uhc());
        inventory.setItem(14, survival());
        return inventory;
    }

    private ItemStack uhc()
    {
        ItemStack itemStack = new ItemStack(Material.APPLE);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§4Join §6UHC Server");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    private ItemStack survival()
    {
        ItemStack itemStack = new ItemStack(Material.GRASS);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("§4Join §7Survival Server");
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
