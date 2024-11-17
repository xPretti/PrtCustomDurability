package dev.pretti.prtcustomdurability;

import dev.pretti.prtcustomdurability.commands.CustomDurabilityCommand;
import dev.pretti.prtcustomdurability.configs.ConfigManager;
import dev.pretti.prtcustomdurability.info.DebugInitializer;
import dev.pretti.prtcustomdurability.listeners.EntityDamageListener;
import dev.pretti.prtcustomdurability.listeners.ItemDamageListener;
import dev.pretti.prtcustomdurability.utils.LogUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PrtCustomDurability extends JavaPlugin
{
  private static PrtCustomDurability instance;
  private        ConfigManager       configManager;

  private boolean isInitialized;

  /**
   * Implementações
   */
  @Override
  public void onLoad()
  {
    loadInstances();
  }

  @Override
  public void onEnable()
  {
    registerEvents();
    registerCommands();

    load();

  }

  @Override
  public void onDisable()
  {
    LogUtils.log("");
    LogUtils.logNormal("Finishing...");
    LogUtils.log("");
  }

  /**
   * Inicializadores
   */
  public boolean reload()
  {
    return load();
  }

  protected boolean load()
  {
    String initMessage = isInitialized ? "Re-Initializing..." : "Initializing...";
    LogUtils.log("");
    LogUtils.logNormal(initMessage);

    boolean success = configManager.load();

    LogUtils.logNormal("");
    if(success)
      {
        DebugInitializer.Show(configManager.getOptionsConfig());
        LogUtils.logNormal("Everything initialized correctly.");
      }
    else
      {
        LogUtils.logError("&4Something went wrong during the initialization process.");
      }
    LogUtils.log("");

    isInitialized = true;

    return success;
  }

  /**
   * Métodos de carregamentos da instância
   */
  protected void loadInstances()
  {
    instance      = this;
    configManager = new ConfigManager();
  }

  /**
   * Métodos de registros de eventos
   */
  protected void registerEvents()
  {
    Bukkit.getPluginManager().registerEvents(new EntityDamageListener(this), this);
    Bukkit.getPluginManager().registerEvents(new ItemDamageListener(), this);
  }

  /**
   * Métodos de registros de comandos
   */
  protected void registerCommands()
  {
    getCommand("CustomDurability").setExecutor(new CustomDurabilityCommand(this));
  }

  /**
   * Métodos de retornos
   */
  public static PrtCustomDurability getInstance()
  {
    return instance;
  }

  public ConfigManager getConfigManager()
  {
    return configManager;
  }
}
