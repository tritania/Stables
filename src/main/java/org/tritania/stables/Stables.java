/*
 * Copyright 2014 Erik Wilson <erikwilson@magnorum.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.tritania.stables;

/*Start Imports*/
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import org.tritania.stables.StatBoard;
import org.tritania.stables.Teleportation;
import org.tritania.stables.Configuration;
import org.tritania.stables.Recipes;
import org.tritania.stables.util.Log;
import org.tritania.stables.util.Message;
import org.tritania.stables.command.*;
import org.tritania.stables.listener.*;
import org.tritania.stables.storage.Storage;

public class Stables extends JavaPlugin
{
    public Teleportation moving;
    public StableSystem horsehomes;
    public StatBoard stats;
    public Configuration config;
    public Recipes recipes;
    public String datalocal;
    public Storage store;

    public void onLoad()
    {
        saveResource("readme.txt", true);
        config = new Configuration(new File(getDataFolder(), "config.yml"));
    }

    public void onEnable()
    {
        PluginManager pm;
        Plugin p;

        Log.init(getLogger());
        Message.init(getDescription().getName());

        pm = getServer().getPluginManager();

        config.load();

        datalocal = getDataFolder().getAbsolutePath();

        pm.registerEvents(new VehicleListener(this), this);
        pm.registerEvents(new SpawnListener(this), this);
        pm.registerEvents(new PlayerListener(this), this);

        moving = new Teleportation(this);
        horsehomes = new StableSystem(this);
        store = new Storage(this);
        stats = new StatBoard(this);
        recipes = new Recipes(this);

        getCommand("htpa").setExecutor(new Teleport(this));
        getCommand("htpahere").setExecutor(new Hteleport(this));
        getCommand("haccept").setExecutor(new Haccept(this));
        getCommand("hdeny").setExecutor(new Hdeny(this));
        getCommand("setstable").setExecutor(new SetStable(this));
        getCommand("stable").setExecutor(new CStable(this));
        getCommand("hstat").setExecutor(new Hstat(this));
        getCommand("hspawn").setExecutor(new HSpawn(this));
        getCommand("delstable").setExecutor(new DelStable(this));
        getCommand("race").setExecutor(new CRace(this));
    }

    public void onDisable()
    {

    }

    public void reload()
    {

    }

}
