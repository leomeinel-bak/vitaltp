/*
 * File: CmdSpec.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitaltp.utils.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CmdSpec {

  private CmdSpec() {
    throw new IllegalStateException("Utility class");
  }

  public static boolean isInvalidCmd(
      @NotNull CommandSender sender,
      Player player,
      @NotNull String perm) {
    return (Cmd.isInvalidSender(sender) ||
        Cmd.isNotPermitted(sender, perm) ||
        Cmd.isInvalidPlayer(sender, player));
  }
}
