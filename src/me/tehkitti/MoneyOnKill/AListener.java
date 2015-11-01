package me.tehkitti.MoneyOnKill;

import me.tehkitti.MoneyOnKill.Main;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class AListener implements Listener {
	Main main;

	public AListener(Main plugin) {
		this.main = plugin;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKill(PlayerDeathEvent event) {
		if (event.getEntity().getKiller() instanceof Player) {
			String Killer = event.getEntity().getKiller().getName();
			Player Killer_ = event.getEntity().getKiller();
			String Killed = event.getEntity().getPlayer().getName();
			main.econ.depositPlayer(Killer,
					main.getConfig().getInt("money-on-kill"));
			Killer_.sendMessage(ChatColor.RED + "You received $" + main.getConfig().getInt("money-on-kill") + " for killing " + Killed);
		}
	}
}