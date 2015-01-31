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
import java.util.HashMap;

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
import org.bukkit.scheduler.BukkitRunnable;

import org.tritania.stables.Stables;
import org.tritania.stables.util.Message;

import static org.bukkit.entity.Horse.*;

import net.minecraft.server.v1_8_R1.AttributeInstance;
import net.minecraft.server.v1_8_R1.AttributeModifier;
import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.GenericAttributes;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;
/*End Imports*/

public class Teleportation
{
    private  HashMap<Player, Integer> type = new HashMap<Player, Integer>();
    private  HashMap<Player, Player> destination = new HashMap<Player, Player>();

    public Stables ht;

    public Teleportation(Stables ht)
    {
        this.ht = ht;
    }

    public void teleportHere(Player one, Player two)
    {
        if (two.getVehicle() != null) //tpahere
        {
            Entity vech = two.getVehicle();

            Location ptl = one.getLocation();
            World ptw = one.getWorld();
            two.teleport(ptl);

            Horse en = (Horse)vech;
            Variant vart = en.getVariant();
            Color col = en.getColor();
            Style sty = en.getStyle();
            int dom = en.getDomestication();
            int life = en.getTicksLived();
            ItemStack arm = en.getInventory().getArmor();
            ItemStack saddle = en.getInventory().getSaddle();
            double ju = en.getJumpStrength();
            double health = en.getMaxHealth();
            String name = en.getCustomName();
            AttributeInstance preattributes = ((EntityInsentient)((CraftLivingEntity)en).getHandle()).getAttributeInstance(GenericAttributes.d);
            double speed = preattributes.getValue();

            en.remove();

            en = ptw.spawn(ptl, Horse.class);
            en.setColor(col);
            en.setDomestication(dom);
            en.setVariant(vart);
            en.setStyle(sty);
            en.setTicksLived(life);
            en.setTamed(true);
            en.getInventory().setSaddle(saddle);
            en.getInventory().setArmor(arm);
            en.setJumpStrength(ju);
            en.setMaxHealth(health);
            en.setAdult();
            en.setCustomName(name);
            if (name == null || name.equals("Horse"))
                en.setCustomNameVisible(false);
            else
                en.setCustomNameVisible(true);

            AttributeInstance postattributes = ((EntityInsentient)((CraftLivingEntity)en).getHandle()).getAttributeInstance(GenericAttributes.d);
            postattributes.setValue(speed);

            ((Horse)en).setPassenger(two);
        }
        else
        {
            Location ptl = one.getLocation();
            two.teleport(ptl);
        }
    }

    public void teleportThere(Player one, Player two)
    {
        if (one.getVehicle() != null) //tpa
        {
            Entity vech = one.getVehicle();

            Location ptl = two.getLocation();
            World ptw = two.getWorld();
            one.teleport(ptl);

            Horse en = (Horse)vech;
            Variant vart = en.getVariant();
            Color col = en.getColor();
            Style sty = en.getStyle();
            int dom = en.getDomestication();
            int life = en.getTicksLived();
            ItemStack arm = en.getInventory().getArmor();
            ItemStack saddle = en.getInventory().getSaddle();
            double ju = en.getJumpStrength();
            double health = en.getMaxHealth();
            String name = en.getCustomName();

            AttributeInstance preattributes = ((EntityInsentient)((CraftLivingEntity)en).getHandle()).getAttributeInstance(GenericAttributes.d);
            double speed = preattributes.getValue();

            en.remove();

            en = ptw.spawn(ptl, Horse.class);
            en.setColor(col);
            en.setDomestication(dom);
            en.setVariant(vart);
            en.setStyle(sty);
            en.setTicksLived(life);
            en.setTamed(true);
            en.getInventory().setSaddle(saddle);
            en.getInventory().setArmor(arm);
            en.setJumpStrength(ju);
            en.setMaxHealth(health);
            en.setAdult();
            en.setCustomName(name);
            if (name == null || name.equals("Horse"))
                en.setCustomNameVisible(false);
            else
                en.setCustomNameVisible(true);

            AttributeInstance postattributes = ((EntityInsentient)((CraftLivingEntity)en).getHandle()).getAttributeInstance(GenericAttributes.d);
            postattributes.setValue(speed);

            ((Horse)en).setPassenger(one);
        }
        else
        {
            Location ptl = two.getLocation();
            one.teleport(ptl);
        }
    }

    public void issueRequestThere(Player one, Player two) //one wants to go to two
    {
        CommandSender stwo = (CommandSender) two;
        Message.info(stwo, one.getPlayerListName() + " Wants to teleport to you, type /haccept to accept or /hdeny to deny");
        type.put(two, 0);
        destination.put(two, one);
        teleportTimer(one, two);
    }

    public void issueRequestHere(Player one, Player two) //one wants two to come to one
    {
        CommandSender stwo = (CommandSender) two;
        Message.info(stwo, one.getPlayerListName() + " Wants you to teleport to them, type /haccept to accept or /hdeny to deny");
        type.put(two, 1);
        destination.put(two, one);
        teleportTimer(one, two);
    }

    public void teleportTimer(final Player one, final Player two)
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                if (destination.containsKey(two))
                {
                    Message.info(one, "Your teleport request expired");
                    Message.info(two, "Teleport request expired.");
                    denied(two);
                }
            }
        }.runTaskLater(ht, 1200L);
    }

    public void accepted(Player player)
    {
        if(type.containsKey(player))
        {
            Player two = destination.get(player);
            int ttype = type.get(player);
            if (ttype == 0)
            {
                teleportThere(two, player); //two is the player teleporting
                type.remove(player);
                destination.remove(player);
            }

            else
            {
                teleportHere(two, player); //player is the player teleporting
                type.remove(player);
                destination.remove(player);
            }
        }
    }

    public void denied(Player player)
    {
        type.remove(player);
        destination.remove(player);
    }
}

