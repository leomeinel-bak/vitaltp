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

import com.google.common.collect.ImmutableMap;
import com.tamrielnetwork.vitaltp.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VitalTpCmd implements TabExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		// Check args length
		if (args.length < 2) {
			Utils.sendMessage(sender, "no-args");
			return true;
		}
		// Check arg 0
		switch (args[0]) {
			case "tp" -> executeTp(sender, args);
			case "tphere" -> executeTpHere(sender, args);
			default -> Utils.sendMessage(sender, "invalid-option");
		}
		return true;
	}

	private void executeTp(CommandSender sender, String[] args) {
		if (args.length > 2) {
			Utils.sendMessage(sender, "invalid-option");
			return;
		}
		// Check if command sender is a player
		if (!(sender instanceof Player)) {
			Utils.sendMessage(sender, "player-only");
			return;
		}
		// Check perms
		if (!sender.hasPermission("vitaltp.tp")) {
			Utils.sendMessage(sender, "no-perms");
			return;
		}
		if (isInValid(sender, args)) {
			return;
		}
		Player player = Bukkit.getPlayer(args[1]);
		assert player != null;
		((Player) sender).teleport(player.getLocation());
		Utils.sendMessage(sender, ImmutableMap.of("%player%", player.getName()), "tp-done");
	}

	private void executeTpHere(CommandSender sender, String[] args) {
		if (args.length > 2) {
			Utils.sendMessage(sender, "invalid-option");
			return;
		}
		// Check if command sender is a player
		if (!(sender instanceof Player)) {
			Utils.sendMessage(sender, "player-only");
			return;
		}
		// Check perms
		if (!sender.hasPermission("vitaltp.tphere")) {
			Utils.sendMessage(sender, "no-perms");
			return;
		}
		if (isInValid(sender, args)) {
			return;
		}
		Player player = Bukkit.getPlayer(args[1]);
		assert player != null;
		player.teleport(((Player) sender).getLocation());
		Utils.sendMessage(sender, ImmutableMap.of("%player%", player.getName()), "tphere-done");
	}

	private boolean isInValid(CommandSender sender, String[] args) {
		if (Bukkit.getPlayer(args[1]) == null) {
			Utils.sendMessage(sender, "invalid-player");
			return true;
		}
		Player player = Bukkit.getPlayer(args[1]);
		if (player == sender) {
			Utils.sendMessage(sender, "invalid-player");
			return true;
		}
		boolean isOnline = Objects.requireNonNull(player).isOnline();
		if (!isOnline) {
			Utils.sendMessage(sender, "not-online");
			return true;
		}
		return false;
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