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
import org.bukkit.Server;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Material;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.ChatColor;

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

public class HSpawn implements CommandExecutor
{
    public Stables ht;
    public Horse horse;

    public HSpawn(Stables ht)
    {
        this.ht = ht;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        Player player = (Player) sender;
        if (player.hasPermission("stables.spawn"))
        {
            if (args.length < 1)
            {
                Message.info(sender, command.getUsage());
            }
            else if (args.length == 6)
            {
                ItemStack saddle = new ItemStack(Material.SADDLE, 1);

                Location location = player.getLocation();

                String colorS = args[0].toUpperCase();
                Color color = Color.valueOf(colorS);

                String styleS = args[1].toUpperCase();
                Style style = Style.valueOf(styleS);

                String variantS = args[2].toUpperCase();
                Variant variant = Variant.valueOf(variantS);

                int jump = Integer.parseInt(args[3]);
                double speed = Double.parseDouble(args[4]) / 100;
                int health = Integer.parseInt(args[5]);

                World world = player.getWorld();

                horse = world.spawn(location, Horse.class);
                horse.setColor(color);
                horse.setDomestication(horse.getMaxDomestication());
                horse.setVariant(variant);
                horse.setStyle(style);
                horse.setTamed(true);
                horse.getInventory().setSaddle(saddle);
                horse.setJumpStrength(jump);
                horse.setMaxHealth(health);
                horse.setAdult();

                AttributeInstance postattributes = ( (EntityInsentient) ( (CraftLivingEntity) horse).getHandle()).getAttributeInstance(GenericAttributes.d);
                postattributes.setValue(speed);

                horse.setPassenger(player);
            }
        }
        else
        {
            Message.info(sender, "You don't have permission for that!");
        }


        return true;
    }
}


