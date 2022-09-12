/*
 * File: VitalTp.java
 * Author: Leopold Meinel (leo@meinel.dev)
 * -----
 * Copyright (c) 2022 Leopold Meinel & contributors
 * SPDX ID: GPL-3.0-or-later
 * URL: https://www.gnu.org/licenses/gpl-3.0-standalone.html
 * -----
 */

package dev.meinel.leo.vitaltp;

import dev.meinel.leo.vitaltp.commands.VitalTpCmd;
import dev.meinel.leo.vitaltp.commands.VitalTphereCmd;
import dev.meinel.leo.vitaltp.files.Messages;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VitalTp extends JavaPlugin {

  private Messages messages;

  @Override
  public void onEnable() {
    registerCommands();
    messages = new Messages();
    Bukkit
        .getLogger()
        .info("VitalTp v" + this.getDescription().getVersion() + " enabled");
    Bukkit.getLogger().info("Copyright (C) 2022 Leopold Meinel");
    Bukkit.getLogger().info("This program comes with ABSOLUTELY NO WARRANTY!");
    Bukkit
        .getLogger()
        .info(
            "This is free software, and you are welcome to redistribute it under certain conditions.");
    Bukkit
        .getLogger()
        .info(
            "See https://github.com/LeoMeinel/VitalTp/blob/main/LICENSE for more details.");
  }

  @Override
  public void onDisable() {
    Bukkit
        .getLogger()
        .info("VitalTp v" + this.getDescription().getVersion() + " disabled");
  }

  private void registerCommands() {
    Objects.requireNonNull(getCommand("tp")).setExecutor(new VitalTpCmd());
    Objects
        .requireNonNull(getCommand("tphere"))
        .setExecutor(new VitalTphereCmd());
  }

  public Messages getMessages() {
    return messages;
  }
}
