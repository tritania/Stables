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

package org.tritania.horseteleport.command;

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

import org.tritania.horseteleport.HorseTeleport;
import org.tritania.horseteleport.util.Message;

import static org.bukkit.entity.Horse.*;
/*End Imports*/

public class Teleport implements CommandExecutor 
{
	public HorseTeleport ht;

    public Teleport(HorseTeleport ht)
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
		else if (player.hasPermission("horseteleport.teleport") && player.getVehicle() != null)
		{
			Player playertwo = Bukkit.getPlayer(args[0]);
			Entity vech = player.getVehicle();
			
			Location ptl = playertwo.getLocation();
			World ptw = playertwo.getWorld();
			player.teleport(ptl);
			
			Horse en = (Horse)vech;
			Variant vart = en.getVariant();
			Color col = en.getColor();
			Style sty = en.getStyle();
			int dom = en.getDomestication();
			int life = en.getTicksLived();
			ItemStack arm = en.getInventory().getArmor();
			double ju = en.getJumpStrength();
			double health = en.getMaxHealth();
			
			en.remove();
			
			en = ptw.spawn(ptl, Horse.class);
			en.setColor(col);
			en.setDomestication(dom);
			en.setVariant(vart);
			en.setStyle(sty);
			en.setTicksLived(life);
			en.setTamed(true);
			en.getInventory().setSaddle(new ItemStack(Material.SADDLE, 1));
			en.getInventory().setArmor(arm);
			en.setJumpStrength(ju);
			en.setMaxHealth(health);
			
			((Horse)en).setPassenger(player);		
		}
		else
		{
			Player playertwo = Bukkit.getPlayer(args[0]);
			Location ptl = playertwo.getLocation();
			player.teleport(ptl);
		}
		return true;
	}
}
