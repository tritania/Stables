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

package org.tritania.stables.command;

/*Start Imports*/
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

import org.tritania.stables.Stables;
import org.tritania.stables.util.Message;

import static org.bukkit.entity.Horse.*;
/*End Imports*/

public class Hteleport implements CommandExecutor
{
    public Stables ht;

    public Hteleport(Stables ht)
    {
        this.ht = ht;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        if (args.length < 1)
        {
            Message.info(sender, command.getUsage());
            return true;
        }
        if ( Bukkit.getPlayer(args[0]) == player)
        {
            Message.info(sender, command.getUsage());
            return true;
        }
        //the person being teleported needs permission not the one giving the command
        else if (Bukkit.getPlayer(args[0]).hasPermission("horseteleport.teleport")) //tpahere
        {
            Message.info(sender, "Sending request.");
            ht.moving.issueRequestHere(player, Bukkit.getPlayer(args[0]));
        }

        return true;
    }
}
