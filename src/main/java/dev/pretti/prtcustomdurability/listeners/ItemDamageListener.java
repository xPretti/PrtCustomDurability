package dev.pretti.prtcustomdurability.listeners;

import dev.pretti.prtcustomdurability.utils.ToolUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ItemDamageListener implements Listener
{
  /**
   * Este método cancela o dano no item da forma vanilla
   */
  @EventHandler
  public void onItemDamage(PlayerItemDamageEvent event)
  {
    ItemStack item = event.getItem();
    if(event.getDamage() > 1 && ToolUtils.isArmor(item.getType()))
      {
        event.setDamage(0);
      }
  }
}
