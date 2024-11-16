package dev.pretti.prtcustomdurability.commands;


import dev.pretti.prtcustomdurability.PrtCustomDurability;
import dev.pretti.prtcustomdurability.commands.base.BaseCommands;
import dev.pretti.prtcustomdurability.commands.subcommands.APReload;
import dev.pretti.prtcustomdurability.configs.types.MessagesConfig;
import dev.pretti.prtcustomdurability.constants.PermissionsConstants;
import dev.pretti.prtcustomdurability.utils.ReplaceUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class FixDamageCommand extends BaseCommands implements CommandExecutor, TabCompleter
{
  private final MessagesConfig messagesConfig;

  /**
   * Construtor da classe
   */
  public FixDamageCommand(PrtCustomDurability plugin)
  {
    super("FixDamage", PermissionsConstants.COMMAND_FIXDAMAGE_PERM);
    this.messagesConfig      = plugin.getConfigManager().getMessagesConfig();

    // Registros
    register(new APReload("reload", PermissionsConstants.COMMAND_RELOAD_PERM, plugin));
  }

  /**
   * Implementação
   */
  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] strings)
  {
    if(hasPermission(sender))
      {
        if(!run(sender, s, strings))
          {
            messagesConfig.getHelpMessages().forEach(message -> sender.sendMessage(ReplaceUtils.toColorMessage(message)));
          }
      }
    else
      {
        String message = ReplaceUtils.toColorMessage(messagesConfig.getNoPermissionMessage());
        sender.sendMessage(message);
      }
    return true;
  }

  @Override
  public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args)
  {
    if(args.length == 1)
      {
        return getCommandsNames(args[0]);
      }
    else if(args.length > 1)
      {
        return runAutoComplete(sender, args[args.length - 2]);
      }
    return null;
  }
}
