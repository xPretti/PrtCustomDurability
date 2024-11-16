package dev.pretti.prtcustomdurability.configs.loaders;

import dev.pretti.prtcustomdurability.configs.interfaces.IConfig;
import dev.pretti.prtcustomdurability.configs.interfaces.IConfigSetup;
import dev.pretti.prtcustomdurability.configs.interfaces.IMessagesConfig;
import dev.pretti.prtcustomdurability.configs.loaders.interfaces.IConfigLoader;
import dev.pretti.prtcustomdurability.utils.FileConfigurationUtils;

public class MessagesLoader implements IConfigLoader
{
  private final IConfigSetup config;
  private final IConfigSetup defaultConfig;

  /**
   * Construtor da classe
   */
  public MessagesLoader(IConfigSetup config, IConfigSetup defaultConfig)
  {
    this.config        = config;
    this.defaultConfig = defaultConfig;
  }

  /**
   * Método de carregamento
   * - Retorna a estrutura contendo as mensagens.
   */
  @Override
  public boolean load(IConfig outputConfig)
  {
    if(outputConfig != null)
      {
        if(outputConfig instanceof IMessagesConfig)
          {
            IMessagesConfig output = (IMessagesConfig) outputConfig;
            output.setNoPermissionMessage(FileConfigurationUtils.getString(config.getConfig(), defaultConfig.getConfig(), "messages.no-permission"));
            output.setReloadMessage(FileConfigurationUtils.getString(config.getConfig(), defaultConfig.getConfig(), "messages.reload"));
            output.setReloadErrorMessage(FileConfigurationUtils.getString(config.getConfig(), defaultConfig.getConfig(), "messages.reload-error"));
            output.setHelpMessages(FileConfigurationUtils.getStringList(config.getConfig(), defaultConfig.getConfig(), "messages.help"));
            return true;
          }
      }
    return false;
  }
}
