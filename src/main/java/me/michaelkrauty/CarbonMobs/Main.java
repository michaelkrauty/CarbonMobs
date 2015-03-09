package me.michaelkrauty.CarbonMobs;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created on 7/24/2014.
 *
 * @author michaelkrauty
 */
public class Main extends JavaPlugin {

	private static Main plugin;

	public void onEnable() {
		this.plugin = this;
		getServer().getPluginManager().registerEvents(new Listener(this), this);
		getServer().getPluginCommand("carbonmobs").setExecutor(new DebugCommand());
	}

	public static void spawnForgotten(Location location) {
		Skeleton forgotten = (Skeleton) location.getWorld().spawnEntity(location, EntityType.SKELETON);
		forgotten.setSkeletonType(Skeleton.SkeletonType.WITHER);
		forgotten.getEquipment().setItemInHand(new ItemStack(Material.DIAMOND_SWORD, 1));
		forgotten.setRemoveWhenFarAway(false);
		forgotten.setMaxHealth(1000);
		forgotten.setHealth(1000);
		forgotten.setCustomName("The Forgotten");
		forgotten.setCustomNameVisible(true);
		forgotten.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 2));
		forgotten.setMetadata("theforgotten", new FixedMetadataValue(plugin, true));
	}

	public static void spawnChargedGhast(Ghast ghast) {
		ghast.setMetadata("chargedghast", new FixedMetadataValue(plugin, true));
		ghast.setCustomName("Charged Ghast");
		ghast.setCustomNameVisible(true);
		ghast.setMaxHealth(ghast.getMaxHealth() * 2);
		ghast.setHealth(ghast.getMaxHealth());
	}
}
