package dev.pretti.prtcustomdurability.utils;

import dev.pretti.prtcustomdurability.enums.EnumMaterialSolutionType;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

public class ToolUtils
{
  private static final int[] ARMOR_RANGES = {
          EnumMaterialSolutionType.LEATHER_HELMET.ordinal(), EnumMaterialSolutionType.NETHERITE_HELMET.ordinal(),
          EnumMaterialSolutionType.LEATHER_CHESTPLATE.ordinal(), EnumMaterialSolutionType.NETHERITE_CHESTPLATE.ordinal(),
          EnumMaterialSolutionType.LEATHER_LEGGINGS.ordinal(), EnumMaterialSolutionType.NETHERITE_LEGGINGS.ordinal(),
          EnumMaterialSolutionType.LEATHER_BOOTS.ordinal(), EnumMaterialSolutionType.NETHERITE_BOOTS.ordinal()
  };

  /**
   * Metodos de verificações do tipo de Armadura
   */
  public static boolean isArmor(Material material)
  {
    return isHelmet(material) || isChestplate(material) || isLeggings(material) || isBoots(material);
  }

  public static boolean isHelmet(Material material)
  {
    return isInRange(material, ARMOR_RANGES[0], ARMOR_RANGES[1]);
  }

  public static boolean isChestplate(Material material)
  {
    return isInRange(material, ARMOR_RANGES[2], ARMOR_RANGES[3]);
  }

  public static boolean isLeggings(Material material)
  {
    return isInRange(material, ARMOR_RANGES[4], ARMOR_RANGES[5]);
  }

  public static boolean isBoots(Material material)
  {
    return isInRange(material, ARMOR_RANGES[6], ARMOR_RANGES[7]);
  }

  /**
  * Métodos de danificação
  */
  public static void damageArmor(Player player, ItemStack item, int damage)
  {
    item.setDurability((short) (item.getDurability() + damage));
    if(item.getDurability() > item.getType().getMaxDurability())
      {
        Material material = item.getType();
        Bukkit.getPluginManager().callEvent(new PlayerItemBreakEvent(player, item));
        item.setDurability((short) 0);
        item.setAmount(item.getAmount() - 1);
        if(item.getAmount() <= 0)
          {
            if(ToolUtils.isHelmet(material))
              {
                player.getInventory().setHelmet(null);
              }
            else if(ToolUtils.isChestplate(material))
              {
                player.getInventory().setChestplate(null);
              }
            else if(ToolUtils.isLeggings(material))
              {
                player.getInventory().setLeggings(null);
              }
            else if(ToolUtils.isBoots(material))
              {
                player.getInventory().setBoots(null);
              }
          }
      }
  }

  /**
   * Metodos de verificações
   */
  private static boolean isInRange(Material material, int startOrdinal, int endOrdinal)
  {
    EnumMaterialSolutionType newMaterial = EnumMaterialSolutionType.tryConvert(material);
    if(newMaterial != null)
      {
        int ordinal = newMaterial.ordinal();
        return ordinal >= startOrdinal && ordinal <= endOrdinal;
      }
    return false;
  }
}
