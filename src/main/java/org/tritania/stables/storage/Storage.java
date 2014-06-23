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

package org.tritania.stables.storage;

import java.util.HashMap;
import java.util.Iterator;
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
import java.util.ArrayList;

import org.bukkit.permissions.PermissibleBase;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Material;

import org.tritania.stables.Stables;
import org.tritania.stables.util.*;
import org.tritania.stables.wrappers.*;

public class Storage implements Serializable
{
    public Stables st;

    public Storage(Stables st)
    {
        new File(st.datalocal + "/playerdata").mkdirs();
        this.st = st;
    }

    public void savePlayer(Player player, Stable stable)
    {
        String playerId = player.getUniqueId().toString();
        try
        {
            File data =  new File(st.datalocal + "/playerdata/" + playerId);
            FileOutputStream fos   = new FileOutputStream(data);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(stable);
            oos.flush();
            oos.close();
            fos.close();
        }
        catch(Exception ex)
        {
            Log.severe("  " + ex.getMessage());
        }
    }

    public boolean check(Player player)
    {
        File data = new File(st.datalocal + "/playerdata/" + player.getUniqueId().toString());
        if (data.exists())
        {
            return true;
        }
        else if (player.hasPermission("stables.teleport"))
        {
            try
            {
                data.createNewFile();
            }
            catch(Exception ex)
            {
                Log.severe("  " + ex.getMessage());
            }
            return false;
        }
        else
        {
            return false;
        }
    }

    public Stable loadPlayer(Player player)
    {
        Stable stable = null;
        try
        {
            File data            = new File(st.datalocal + "/playerdata/" + player.getUniqueId().toString());
            FileInputStream fis  = new FileInputStream(data);
            ObjectInputStream ois= new ObjectInputStream(fis);

            stable = (Stable)ois.readObject();

            ois.close();
            fis.close();
        }
        catch(Exception ex)
        {
            Log.severe(" " + ex.getMessage());
        }
        return stable;
    }

    public void loadPlayers()
    {
        Player[] playersSave = Bukkit.getOnlinePlayers();
        for (Player play : playersSave)
        {
            if (check(play))
            {
                loadPlayer(play);
            }
        }
    }

    public void savePlayers()
    {
        Player[] playersSave = Bukkit.getOnlinePlayers();
        for (Player play : playersSave)
        {
            if (check(play))
            {
                savePlayer(play, st.horsehomes.getStableObj(play));
                st.horsehomes.removePlayerStable(play);
            }
        }
    }
}
