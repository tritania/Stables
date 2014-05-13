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

package org.tritania.horseteleport;

/*Start Imports*/
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.bukkit.permissions.PermissibleBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Vehicle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Material;

import org.tritania.horseteleport.HorseTeleport;
import org.tritania.horseteleport.util.Message;
import org.tritania.horseteleport.util.Log;

import static org.bukkit.entity.Horse.*;
/*End Imports*/

public class Stables
{
	public HashMap<Player, Location> stablelocations = new HashMap<Player, Location>();
	
	public HorseTeleport ht;

    public Stables(HorseTeleport ht)
    {
        this.ht = ht;
    }
    
    public void loadStables() 
    {
		try
		{
			File data            = new File("stables.data");
			FileInputStream fis  = new FileInputStream(data);
			ObjectInputStream ois= new ObjectInputStream(fis);

			stablelocations = (HashMap<Player,Location>)ois.readObject();

			ois.close();
			fis.close();
		}
		catch(Exception ex)
		{
			Log.severe(" " + ex.getMessage());
		}
	}
	
	public void offloadStables()
	{
		try
		{
			File data =  new File("stables.data");
			FileOutputStream fos   = new FileOutputStream(data);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(stablelocations);
			oos.flush();
			oos.close();
			fos.close();
			
		}
		catch(Exception ex)
		{
			Log.severe("  " + ex.getMessage());
		}
	}
	
	public void updateStable(Player player, Location location)
	{
		if(stablelocations.containsKey(player))
		{
			stablelocations.remove(player);
		}
		stablelocations.put(player, location);
	}
	
	public Location getStable(Player player)
	{
		return stablelocations.get(player);
	}
	
	public boolean hasStable(Player player)
	{
		if(stablelocations.containsKey(player))
			return true;
		else
			return false;
	}
    

}
   
