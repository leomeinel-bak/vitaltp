/*
 * File: VitalTpCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitaltp.commands;

import dev.meinel.leo.vitaltp.utils.commands.Cmd;
import dev.meinel.leo.vitaltp.utils.commands.CmdSpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VitalTpCmd implements CommandExecutor {

  @Override
  public boolean onCommand(
      @NotNull CommandSender sender,
      @NotNull Command command,
      @NotNull String label,
      @NotNull String[] args) {
    if (Cmd.isArgsLengthNotEqualTo(sender, args, 1)) {
      return false;
    }
    doTp(sender, args);
    return true;
  }

  public void doTp(@NotNull CommandSender sender, @NotNull String[] args) {
    Player player = Bukkit.getPlayer(args[0]);
    if (CmdSpec.isInvalidCmd(sender, player, "vitaltp.tp")) {
      return;
    }
    Player senderPlayer = (Player) sender;
    assert player != null;
    senderPlayer.teleport(player.getLocation());
  }
}
