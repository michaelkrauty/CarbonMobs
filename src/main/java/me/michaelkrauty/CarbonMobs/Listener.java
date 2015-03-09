package me.michaelkrauty.CarbonMobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.projectiles.ProjectileSource;

import java.util.Random;

/**
 * Created on 7/24/2014.
 *
 * @author michaelkrauty
 */
public class Listener implements org.bukkit.event.Listener {

	private Main main;

	public Listener(Main main) {
		this.main = main;
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (!event.getEntity().hasMetadata("CarbonCreepers")) {
			if (event.getEntityType() == EntityType.ZOMBIE) {
				if (new Random().nextInt(99) == 0) {
					Zombie zombie = (Zombie) event.getEntity();
					Giant giant = (Giant) zombie.getWorld().spawnEntity(zombie.getLocation(), EntityType.GIANT);
					giant.setMaxHealth(750);
					giant.setHealth(750);
					giant.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 72000, 5));
					zombie.remove();
					giant.setMetadata("giant", new FixedMetadataValue(main, true));
				}
				if (new Random().nextInt(14) == 0) {
					Zombie zombie = (Zombie) event.getEntity();
					zombie.setMaxHealth(250);
					zombie.setHealth(250);
					zombie.setCustomName("Boss Zombie");
					zombie.setCustomNameVisible(true);
					return;
				}
				if (new Random().nextInt(6) == 0) {
					Zombie zombie = (Zombie) event.getEntity();
					zombie.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 72000, 2));
					zombie.setCustomName("Runner Zombie");
					zombie.setCustomNameVisible(true);
					return;
				}
			}

			if (event.getEntityType() == EntityType.PIG_ZOMBIE) {
				if (new Random().nextInt(999) == 0) {
					Main.spawnForgotten(event.getEntity().getLocation());
				}
			}

			if (event.getEntityType() == EntityType.PIG) {
				if (new Random().nextInt(199) == 0) {
					Snowman snowman = (Snowman) event.getEntity().getWorld().spawnEntity(event.getLocation(), EntityType.SNOWMAN);
					event.getEntity().remove();
					snowman.setCustomName("Frosty");
					snowman.setCustomNameVisible(true);
				}
			}

			if (event.getEntityType() == EntityType.WITCH) {
				/*
				if (new Random().nextInt(2) == 0) {
					Witch witch = (Witch) event.getEntity();
					witch.setMetadata("CarbonCreepers", new FixedMetadataValue(main, true));
					for (int i = 1; i < 5; i++) {
						Witch w = (Witch) witch.getWorld().spawnEntity(witch.getLocation(), EntityType.WITCH);
						w.setMetadata("CarbonCreepers", new FixedMetadataValue(main, true));
					}
					main.getLogger().info("Spawned witch group.");
					return;
				}
				*/
			}

			if (event.getEntityType() == EntityType.SPIDER) {
				if (new Random().nextInt(9) == 0) {
					Spider spider = (Spider) event.getEntity();
					spider.getWorld().spawnEntity(spider.getLocation(), EntityType.CAVE_SPIDER).getUniqueId();
					spider.remove();
					return;
				}
				if (new Random().nextInt(19) == 0) {
					Spider spider = (Spider) event.getEntity();
					spider.setMaxHealth(13);
					spider.setHealth(13);
					spider.setCustomName("Venomous Spider");
					spider.setCustomNameVisible(true);
					spider.setMetadata("venom", new FixedMetadataValue(main, true));
					return;
				}
			}

			if (event.getEntityType() == EntityType.SKELETON) {
				if (new Random().nextInt(999) == 0) {
					Skeleton skeleton = (Skeleton) event.getEntity();
					skeleton.setCustomName("Boner");
					skeleton.setCustomNameVisible(true);
				}
			}

			if (event.getEntityType() == EntityType.GHAST) {
				if (new Random().nextInt(49) == 0) {
					Ghast ghast = (Ghast) event.getEntity();
					Main.spawnChargedGhast(ghast);
				}
			}
		}
	}

	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (event.getDamager().hasMetadata("venom")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
			} else if (event.getDamager().hasMetadata("theforgotten")) {
				player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 60, 3));
				player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 60, 3));
			}
		}
	}

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {
		if (event.getEntity().hasMetadata("theforgotten")) {
			event.getEntity().getLocation().getWorld().dropItem(event.getEntity().getLocation(), new ItemStack(Material.NETHER_STAR, 1));
			event.getEntity().remove();
			main.getServer().broadcastMessage(ChatColor.LIGHT_PURPLE + "The Forgotten has been slain!");
		} else if (event.getEntity().hasMetadata("giant")) {
			event.setDroppedExp(500);
		}
	}
}
