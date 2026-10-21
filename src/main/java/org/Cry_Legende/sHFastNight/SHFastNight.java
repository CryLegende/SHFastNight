package org.Cry_Legende.sHFastNight;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.GameRule;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SHFastNight extends JavaPlugin {
    private int timeMultiplier;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        timeMultiplier = config.getInt("time-multiplier", 2);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getWorlds()) {
                    Boolean doDaylightCycle = world.getGameRuleValue(GameRule.DO_DAYLIGHT_CYCLE);
                    if (doDaylightCycle != null && doDaylightCycle && isNight(world)) {
                        long time = world.getTime();
                        world.setTime(time + timeMultiplier - 1); // -1 because 1 is added by the game itself
                    }
                }
            }
        }.runTaskTimer(this, 0L, 1L);
    }

    private boolean isNight(World world) {
        long time = world.getTime();
        return time >= 13000 && time <= 23500;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}





