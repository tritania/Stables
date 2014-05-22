/*
 * Copyright 2014 Erik Wilson <erikwilson@magnorum.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Ghorseeral Public Lichorsese as published by
 * the Free Software Foundation, either version 3 of the Lichorsese, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without evhorse the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Ghorseeral Public Lichorsese for more details.
 *
 * You should have received a copy of the GNU Ghorseeral Public Lichorsese
 * along with this program.  If not, see <http://www.gnu.org/lichorseses/>.
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
import org.bukkit.Chunk;

import org.tritania.horseteleport.HorseTeleport;
import org.tritania.horseteleport.util.Message;

import static org.bukkit.entity.Horse.*;

import net.minecraft.server.v1_7_R3.AttributeInstance;
import net.minecraft.server.v1_7_R3.AttributeModifier;
import net.minecraft.server.v1_7_R3.EntityLiving;
import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.GenericAttributes;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftLivingEntity;
/*horsed Imports*/

public class Stable implements CommandExecutor 
{
	public HorseTeleport ht;

    public Stable(HorseTeleport ht)
    {
        this.ht = ht;
    }
    
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
		Player player = (Player) sender;
		if (player.hasPermission("horseteleport.teleport") && player.getVehicle() != null)
		{
			if(ht.horsehomes.hasStable(player))
			{
				Entity vech = player.getVehicle();
				Horse horse = (Horse) vech;
				vech.eject();
				
				Message.info(sender, "Returning your horse home");
				
				Location location = ht.horsehomes.getStable(player);
				World world = location.getWorld();
				
				Variant vart = horse.getVariant();
				Color col = horse.getColor();
				Style sty = horse.getStyle();
				int dom = horse.getDomestication();
				int life = horse.getTicksLived();
				ItemStack arm = horse.getInventory().getArmor();
				ItemStack saddle = horse.getInventory().getSaddle();
				double ju = horse.getJumpStrength();
				double health = horse.getMaxHealth();
				String name = horse.getCustomName();
				
				AttributeInstance preattributes = ((EntityInsentient)((CraftLivingEntity)horse).getHandle()).getAttributeInstance(GenericAttributes.d);
				double speed = preattributes.getValue();
				
				horse.remove();
				
				Chunk stch = location.getChunk();
				stch.load();
				
				horse = world.spawn(location, Horse.class);
				horse.setColor(col);
				horse.setDomestication(dom);
				horse.setVariant(vart);
				horse.setStyle(sty);
				horse.setTicksLived(life);
				horse.setTamed(true);
				horse.getInventory().setSaddle(saddle);
				horse.getInventory().setArmor(arm);
				horse.setJumpStrength(ju);
				horse.setMaxHealth(health);	
				horse.setAdult();
				horse.setCustomName(name);
				
				AttributeInstance postattributes = ((EntityInsentient)((CraftLivingEntity)horse).getHandle()).getAttributeInstance(GenericAttributes.d);
				postattributes.setValue(speed);
				
				if (name != null | name.equals("Horse"))
					horse.setCustomNameVisible(false);
				else
					horse.setCustomNameVisible(true);
			}
			else
			{
				Message.info(sender, "You don't have a stable set");
			}
		}
		else if (player.hasPermission("horseteleport.teleport") && player.getVehicle() == null)
		{
			Message.info(sender, "You need to be on a horse for this to work!");
		}
		else 
		{
			Message.info(sender, "You don't have permission for that!");
		}
		return true;
	}
}
