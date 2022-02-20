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

import com.tamrielnetwork.vitaltp.utils.Chat;
import com.tamrielnetwork.vitaltp.utils.Cmd;
import com.tamrielnetwork.vitaltp.utils.CmdSpec;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class VitalTpCmd implements TabExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

		if (Cmd.checkArgsNotEqualTo(sender, args, 2)) {
			return true;
		}

		switch (args[0]) {
			case "tp" -> handleTp(sender, args, "vitaltp.tp", "tp-done");
			case "tphere" -> handleTp(sender, args, "vitaltp.tphere", "tphere-done");
			default -> Chat.sendMessage(sender, "invalid-option");
		}
		return true;
	}

	public void handleTp(@NotNull CommandSender sender, @NotNull String[] args, @NotNull String perm, @NotNull String playerMessage) {
		Player player = Bukkit.getPlayer(args[1]);

		if (player == null) {
			Chat.sendMessage(sender, "not-online");
			return;
		}

		if (CmdSpec.isInvalidTp(sender, player, perm)) {
			return;
		}

		CmdSpec.doTp(sender, args, player);
	}

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
		@Nullable List<String> tabComplete = new ArrayList<>();
		if (args.length == 1) {
			if (sender.hasPermission("vitaltp.tp")) {
				tabComplete.add("tp");
			}
			if (sender.hasPermission("vitaltp.tphere")) {
				tabComplete.add("tphere");
			}
		} else {
			tabComplete = null;
		}
		return tabComplete;
	}
}