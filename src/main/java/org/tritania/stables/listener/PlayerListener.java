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

package org.tritania.stables.listener;

/*Start Imports*/
import org.bukkit.permissions.PermissibleBase;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vehicle;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import org.tritania.stables.Stables;
import org.tritania.stables.util.Message;

import static org.bukkit.entity.Horse.*;

import net.minecraft.server.v1_7_R3.AttributeInstance;
import net.minecraft.server.v1_7_R3.AttributeModifier;
import net.minecraft.server.v1_7_R3.EntityLiving;
import net.minecraft.server.v1_7_R3.EntityInsentient;
import net.minecraft.server.v1_7_R3.GenericAttributes;
import org.bukkit.craftbukkit.v1_7_R3.entity.CraftLivingEntity;

public class PlayerListener implements Listener
{

    public Stables ht;

    public PlayerListener(Stables ht)
    {
        this.ht = ht;
    }

    public void register()
    {
        PluginManager manager;

        manager = ht.getServer().getPluginManager();
        manager.registerEvents(this, ht);
    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event)
    {
       Player player = event.getPlayer();
       if (ht.store.check(player))
       {

       }

    }

    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
    public void onPlayerLeave(PlayerQuitEvent event)
    {
       Player player = event.getPlayer();

    }
}
