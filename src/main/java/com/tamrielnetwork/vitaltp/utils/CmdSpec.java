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

package com.tamrielnetwork.vitaltp.utils;

import com.google.common.collect.ImmutableMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CmdSpec {

	public static void doTp(@NotNull CommandSender sender, @NotNull String[] args, @NotNull Player player) {
		Player senderPlayer = (Player) sender;

		switch (args[0]) {
			case "tp" -> {
				player.teleport(senderPlayer.getLocation());
				Chat.sendMessage(player, ImmutableMap.of("%player%", sender.getName()), "tp-done");
			}
			case "tphere" -> {
				senderPlayer.teleport(player.getLocation());
				Chat.sendMessage(player, ImmutableMap.of("%player%", sender.getName()), "tphere-done");
			}
			default -> Chat.sendMessage(sender, "cmd");
		}
	}

	public static boolean isInvalidTp(@NotNull CommandSender sender, @NotNull Player player, @NotNull String perm) {
		if (Cmd.checkSender(sender) || Cmd.checkPerm(sender, perm)) {
			return true;
		}
		if (player == sender) {
			Chat.sendMessage(sender, "same-player");
			return true;
		}
		return false;
	}

}
