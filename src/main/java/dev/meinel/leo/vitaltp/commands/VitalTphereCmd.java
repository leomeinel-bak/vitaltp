/*
 * File: VitalTphereCmd.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2023 Leopold Meinel & contributors
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

public class VitalTphereCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
            @NotNull String label, @NotNull String[] args) {
        if (!Cmd.isArgsLengthEqualTo(sender, args, 1)) {
            return false;
        }
        doTphere(sender, args);
        return true;
    }

    public void doTphere(@NotNull CommandSender sender, @NotNull String[] args) {
        Player player = Bukkit.getPlayer(args[0]);
        if (CmdSpec.isInvalidCmd(sender, player, "vitaltp.tphere")) {
            return;
        }
        Player senderPlayer = (Player) sender;
        assert player != null;
        player.teleport(senderPlayer.getLocation());
    }
}
