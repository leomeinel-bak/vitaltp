/*
 * VitalTp is a Spigot Plugin that gives players the ability to teleport to each other.
 * Copyright © 2022 Leopold Meinel
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see https://github.com/TamrielNetwork/VitalTp/blob/main/LICENSE
 */

package com.tamrielnetwork.vitaltp.commands;

import com.tamrielnetwork.vitaltp.utils.commands.Cmd;
import com.tamrielnetwork.vitaltp.utils.commands.CmdSpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VitalTpCmd implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

		if (Cmd.isArgsLengthNotEqualTo(sender, args, 1)) {
			return true;
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