package dev.pretti.prtcustomdurability.info;

import dev.pretti.prtcustomdurability.configs.interfaces.IOptionsConfig;
import dev.pretti.prtcustomdurability.utils.LogUtils;
import dev.pretti.prtcustomdurability.utils.MathFormUtils;
import dev.pretti.prtcustomdurability.utils.MathUtils;

public class DebugInitializer
{
  public static void Show(IOptionsConfig optionsConfig)
  {
    LogUtils.logNormal("Chances Initialization Information:");

    String format = "Level: §f%d§8, Vanilla: §c%.2f%%§8, Custom: §a%.2f%%§8.";

    for(int i = 0; i < optionsConfig.getMaxDurability()+1; i++)
      {
        double vanilla = MathFormUtils.getArmorDamageChanceByMinecraft(i);
        double custom  = MathUtils.getSmoothChanceReverse(i, optionsConfig.getMaxDurability(), 0.75, optionsConfig.getDurabilityMinChance());

        LogUtils.logNormal(String.format(format, i, vanilla, custom));
      }
  }
}
