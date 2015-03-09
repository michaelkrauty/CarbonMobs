package me.michaelkrauty.CarbonMobs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Player;

/**
 * Created on 7/26/2014.
 *
 * @author michaelkrauty
 */
public class DebugCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (player.hasPermission("carbonmobs.admin"))
			Main.spawnChargedGhast((Ghast) player.getWorld().spawnEntity(player.getLocation(), EntityType.GHAST));
		else
			player.sendMessage("no permission");
		return true;
	}
}
