package dev.pretti.prtcustomdurability.commands.subcommands;

import dev.pretti.prtcustomdurability.PrtCustomDurability;
import dev.pretti.prtcustomdurability.commands.base.BaseCommand;
import dev.pretti.prtcustomdurability.configs.types.MessagesConfig;
import dev.pretti.prtcustomdurability.utils.ReplaceUtils;
import org.bukkit.command.CommandSender;

public class APReload extends BaseCommand
{
  private final PrtCustomDurability plugin;
  private final MessagesConfig      messagesConfig;

  /**
   * Construtor da classe
   */
  public APReload(String command, String permission, PrtCustomDurability plugin)
  {
    super(command, permission);
    this.plugin         = plugin;
    this.messagesConfig = plugin.getConfigManager().getMessagesConfig();
  }

  /**
   * Implementação do comando
   */
  @Override
  public boolean run(CommandSender sender, String command, String[] args)
  {
    if(isCommand(command))
      {
        if(hasPermission(sender))
          {
            boolean success = plugin.reload();
            String  message = ReplaceUtils.toColorMessage(success ? messagesConfig.getReloadMessage() : messagesConfig.getReloadErrorMessage());
            sender.sendMessage(message);
          }
        else
          {
            String message = ReplaceUtils.toColorMessage(messagesConfig.getNoPermissionMessage());
            sender.sendMessage(message);
          }
        return true;
      }
    return false;
  }
}
