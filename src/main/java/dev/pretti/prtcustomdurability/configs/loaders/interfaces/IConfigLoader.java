package dev.pretti.prtcustomdurability.configs.loaders.interfaces;


import dev.pretti.prtcustomdurability.configs.interfaces.IConfig;

public interface IConfigLoader
{
  /**
   * Método de carregamento
   */
  boolean load(IConfig outputConfig);
}
