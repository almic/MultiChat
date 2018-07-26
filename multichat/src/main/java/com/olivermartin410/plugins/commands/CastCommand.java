package com.olivermartin410.plugins.commands;

import java.util.Iterator;

import com.olivermartin410.plugins.CastControl;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.plugin.Command;

/**
 * Cast Command
 * <p> The Custom broadcAST (CAST) command allows you to create your own customised broadcast formats </p>
 * 
 * @author Oliver Martin (Revilo410)
 *
 */
public class CastCommand extends Command {

	private static String[] aliases = new String[] {};

	public CastCommand() {
		super("cast", "multichat.cast.admin", aliases);
	}

	public void showCommandUsage(CommandSender sender) {
		sender.sendMessage(new ComponentBuilder("Usage:").color(ChatColor.GREEN).create());
		sender.sendMessage(new ComponentBuilder("/cast add <name> <format>").color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder("/cast remove <name>").color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder("/cast list").color(ChatColor.AQUA).create());
		sender.sendMessage(new ComponentBuilder("/<castname> <message>").color(ChatColor.AQUA).create());
	}

	@Override
	public void execute(CommandSender sender, String[] args) {

		if (args.length < 1) {

			showCommandUsage(sender);

		} else if (args.length == 1) {

			if (args[0].toLowerCase().equals("list")) {

				Iterator<String> it = CastControl.castList.keySet().iterator();
				String currentItem;

				sender.sendMessage(new ComponentBuilder("List of avaliable casts:").color(ChatColor.GREEN).create());
				while (it.hasNext()) {
					currentItem = it.next();
					sender.sendMessage(new ComponentBuilder(currentItem + ": " + CastControl.castList.get(currentItem)).color(ChatColor.AQUA).create());
				}

			} else {
				showCommandUsage(sender);
			}

		} else if (args.length == 2) {

			if (args[0].toLowerCase().equals("remove")) {

				if (CastControl.existsCast(args[1])) {

					CastControl.removeCast(args[1]);
					sender.sendMessage(new ComponentBuilder("Removed cast: " + args[1].toUpperCase()).color(ChatColor.GREEN).create());

				} else {

					sender.sendMessage(new ComponentBuilder("Sorry, no such cast found: " + args[1].toUpperCase()).color(ChatColor.RED).create());
				}

			} else {

				showCommandUsage(sender);

			}

		} else if (args.length == 3) {

			if (args[0].toLowerCase().equals("add")) {

				if (!(CastControl.existsCast(args[1])) && !args[1].equalsIgnoreCase("cast")) {

					CastControl.addCast(args[1], args[2]);
					sender.sendMessage(new ComponentBuilder("Added cast: " + args[1].toUpperCase()).color(ChatColor.GREEN).create());

				} else {

					sender.sendMessage(new ComponentBuilder("Sorry, cast already exists: " + args[1].toUpperCase()).color(ChatColor.RED).create());
				}

			} else {

				showCommandUsage(sender);

			}

		} else if (args.length >= 3) {

			if (args[0].toLowerCase().equals("add")) {

				int counter = 0;
				String message = "";

				for (String arg : args) {
					if (!(counter == 2)) {
						counter++;
					} else {
						message = message + arg + " ";
					}
				}

				if (!CastControl.existsCast(args[1])) {

					CastControl.addCast(args[1], message);
					sender.sendMessage(new ComponentBuilder("Added cast: " + args[1].toUpperCase()).color(ChatColor.GREEN).create());

				} else {

					sender.sendMessage(new ComponentBuilder("Sorry, cast already exists: " + args[1].toUpperCase()).color(ChatColor.RED).create());
				}

			} else {

				showCommandUsage(sender);

			}

		} else {

			showCommandUsage(sender);
		}
	}
}
