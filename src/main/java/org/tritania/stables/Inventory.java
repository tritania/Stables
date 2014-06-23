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

import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Material;

import org.tritania.stables.Stables;
import org.tritania.stables.util.*;

public class Inventory
{
    public Stables st;

    public Inventory(Stables st)
    {
        this.st = st;
    }

    public void placeItems(Player player, ItemStack reward)
    {
        player.getInventory().addItem(reward);
    }

    public void removeItems(Player player, ItemStack cost)
    {
        player.getInventory().removeItem(cost);
    }

    public boolean checkForItems(Player player, Material item, int amount)
    {
        return player.getInventory().contains(item, amount);
    }

}
