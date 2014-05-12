/*
 * Copyright 2012-2014 Erik Wilson <erikwilson@magnorum.com>
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

package org.tritania.horseteleport;

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

import org.tritania.horseteleport.command.*;

public class HorseTeleport extends JavaPlugin
{
	
	public void onLoad()
	{
		
	}
	
	public void onEnable()
	{
		
		PluginManager pm;
		Plugin p;
		
		Log.init(getLogger());
		Message.init(getDescription().getName());
		
		pm = getServer().getPluginManager();
		config.load();
		
		sqlengine = new ChrysusStorage(this);
        sqlengine.initialize();
        
        pm.registerEvents(new ChrysusListener(this), this);

        getCommand("htpa").setExecutor(new Teleport(this));
        getCommand("htpahere").setExecutor(new Hteleport(this));
      
		
	}
	
	public void onDisable() 
	{

    }
    
	public void reload()
	{

	}
	
	
