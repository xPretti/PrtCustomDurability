package dev.pretti.prtcustomdurability.listeners;

import dev.pretti.prtcustomdurability.PrtCustomDurability;
import dev.pretti.prtcustomdurability.configs.interfaces.IOptionsConfig;
import dev.pretti.prtcustomdurability.utils.MathUtils;
import dev.pretti.prtcustomdurability.utils.ToolUtils;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class EntityDamageListener implements Listener
{
  private final IOptionsConfig optionsConfig;

  /**
   * Contrutor da classe
   */
  public EntityDamageListener(PrtCustomDurability plugin)
  {
    optionsConfig = plugin.getConfigManager().getOptionsConfig();
  }

  /**
   * Método de implementação do custom durability
   */
  @EventHandler
  public void onEntityDamage(EntityDamageEvent event)
  {
    double damage = event.getDamage();
    if(damage > 1)
      {
        Entity entity = event.getEntity();
        if(entity instanceof Player)
          {
            Player          player    = (Player) event.getEntity();
            PlayerInventory inventory = player.getInventory();
            damageArmor(player, inventory.getHelmet(), damage);
            damageArmor(player, inventory.getChestplate(), damage);
            damageArmor(player, inventory.getLeggings(), damage);
            damageArmor(player, inventory.getBoots(), damage);
          }
      }
  }

  /**
   * Retornos
   */
  private int getArmorDamage(ItemStack item, int damage)
  {
    int    durability = item.getEnchantmentLevel(Enchantment.DURABILITY);
    double chance     = MathUtils.getSmoothChanceReverse(durability, optionsConfig.getMaxDurability(), 0.75, optionsConfig.getDurabilityMinChance());
    int    count      = durability > 0 ? MathUtils.getUnexpectedOccurrencesInt(chance, damage) : 0;
    return Math.max(damage - count, 0);
  }

  /**
   * Métodos para aplicar danos
   */
  private void damageArmor(Player player, ItemStack item, double damage)
  {
    if(item != null)
      {
        damage /= 4d;
        if(damage < 1d)
          {
            damage = 1d;
          }
        int newDamage = getArmorDamage(item, (int) damage);
        if(newDamage >= 1)
          {
            ToolUtils.damageArmor(player, item, newDamage);
          }
      }
  }

}
