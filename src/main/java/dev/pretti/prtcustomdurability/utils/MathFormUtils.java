package dev.pretti.prtcustomdurability.utils;

public class MathFormUtils
{
  /**
   * Formulas usadas pelo padrão do minicraft para calcular chances de causar danos
   */
  public static double getToolsDamageChanceByMinecraft(int level)
  {
    return 100.0 / (1 + level);
  }

  public static double getArmorDamageChanceByMinecraft(int level)
  {
    return 60.0 + (40.0 / (1 + level));
  }
}
