package dev.pretti.prtcustomdurability.configs.loaders;

import dev.pretti.prtcustomdurability.configs.interfaces.IConfig;
import dev.pretti.prtcustomdurability.configs.interfaces.IConfigSetup;
import dev.pretti.prtcustomdurability.configs.interfaces.IOptionsConfig;
import dev.pretti.prtcustomdurability.configs.loaders.interfaces.IConfigLoader;
import dev.pretti.prtcustomdurability.utils.FileConfigurationUtils;

public class OptionsLoader implements IConfigLoader
{
  private final IConfigSetup config;
  private final IConfigSetup defaultConfig;

  /**
   * Construtor da classe
   */
  public OptionsLoader(IConfigSetup config, IConfigSetup defaultConfig)
  {
    this.config        = config;
    this.defaultConfig = defaultConfig;
  }

  /**
   * Método de carregamento
   */
  @Override
  public boolean load(IConfig outputConfig)
  {
    if(outputConfig != null)
      {
        if(outputConfig instanceof IOptionsConfig)
          {
            IOptionsConfig output = (IOptionsConfig) outputConfig;

            String options   = "options.";
            double minChance = FileConfigurationUtils.getDouble(config.getConfig(), defaultConfig.getConfig(), options + "min-chance") / 100d;
            output.setMaxDurability(FileConfigurationUtils.getInt(config.getConfig(), defaultConfig.getConfig(), options + "max-durability"));
            output.setDurabilityMinChance(minChance);
            return true;
          }
      }
    return false;
  }
}
