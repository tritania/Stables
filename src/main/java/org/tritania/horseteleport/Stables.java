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
import java.io.Serializable;
import java.util.UUID;

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
import org.tritania.horseteleport.Stable;

import static org.bukkit.entity.Horse.*;
/*End Imports*/

public class Stables implements Serializable
{
    public HashMap<UUID, Stable> stablelocations = new HashMap<UUID, Stable>();

    public HorseTeleport ht;

    public Stables(HorseTeleport ht)
    {
        this.ht = ht;
    }

    public void loadStables()
    {
        try
        {
            File data            = new File(ht.datalocal + "/stables.data");
            FileInputStream fis  = new FileInputStream(data);
            ObjectInputStream ois= new ObjectInputStream(fis);

            stablelocations = (HashMap<UUID,Stable>)ois.readObject();

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
            File data =  new File(ht.datalocal + "/stables.data");
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

    public void updateStable(Player player, Location location, String name)
    {
        UUID playerId = player.getUniqueId();
        String local = location.getWorld().getName() + "," + String.valueOf(location.getX()) + "," + String.valueOf(location.getY()) + "," + String.valueOf(location.getZ());
        Stable stable = stablelocations.get(playerId);
        stable.addStable(name, local);
    }

    public Location getStable(Player player, String name)
    {
        UUID playerId = player.getUniqueId();
        Stable stable = stablelocations.get(playerId);
        String[] ld = stable.getLocation(name).split(",");
        Location location = new Location(Bukkit.getWorld(ld[0]),Double.parseDouble(ld[1]),Double.parseDouble(ld[2]),Double.parseDouble(ld[3]));
        return location;
    }

    public boolean check(Player player, String name)
    {
        UUID playerId = player.getUniqueId();
        Stable stable = stablelocations.get(playerId);
        return stable.checkStable(name);
    }

    public boolean hasStable(Player player)
    {
        UUID playerId = player.getUniqueId();
        if(stablelocations.containsKey(playerId))
        {
            return true;
        }
        else
        {
            Stable stable = new Stable();
            stablelocations.put(playerId, stable);
            return false;
        }
    }

    public String getStableNames(Player player)
    {
        UUID playerId = player.getUniqueId();
        Stable stable = stablelocations.get(playerId);
        String names = stable.getNames();
        return names;
    }

    public void deleteStable(Player player, String name)
    {
        UUID playerId = player.getUniqueId();
        Stable stable = stablelocations.get(playerId);
        stable.delStable(name);
    }
}

