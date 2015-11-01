package me.tehkitti.MoneyOnKill;

import java.util.logging.Level;

import me.tehkitti.MoneyOnKill.AListener;
import me.tehkitti.MoneyOnKill.Main;

import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public static String v = "1.0.0";
	public static Main instance;
	public Economy econ;

	@Override
	public void onDisable() {
		pluginInfo("of MoneyOnKill Disabled!");
	}

	@Override
	public void onEnable() {

		Main.instance = this;
		pluginInfo("of MoneyOnKill Enabled!");
		getConfig().options().copyDefaults(true);
		saveConfig();
		getServer().getPluginManager()
				.registerEvents(new AListener(this), this);
		setupVault();

	}

	void setupVault() {
		Plugin vault = getServer().getPluginManager().getPlugin("Vault");
		if (vault != null && vault instanceof Vault && vault.isEnabled()) {
			Bukkit.getLogger().log(Level.INFO,
					"Hooked Vault v" + vault.getDescription().getVersion());
			if (!setupEconomy())
				Bukkit.getLogger().log(Level.WARNING,
						"No economy rewards not given from kills!");
		} else
			Bukkit.getLogger().log(Level.WARNING,
					"Vault not loaded: No economy support!");
	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = getServer()
				.getServicesManager().getRegistration(Economy.class);
		if (economyProvider != null) {
			econ = economyProvider.getProvider();
		}
		return (econ != null);
	}

	public static void pluginInfo(String message) {
		System.out.println("[MoneyOnKill] Version " + v + " " + message);
	}

}