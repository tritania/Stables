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
import org.tritania.stables.wrappers.Race;

import static org.bukkit.entity.Horse.*;

import net.minecraft.server.v1_8_R1.AttributeInstance;
import net.minecraft.server.v1_8_R1.AttributeModifier;
import net.minecraft.server.v1_8_R1.EntityLiving;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.GenericAttributes;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftLivingEntity;

public class StatBoard
{

    public Stables ht;

    public StatBoard(Stables ht)
    {
        this.ht = ht;
    }


    public void setStat(Player player)
    {
        Entity en = player.getVehicle();
        Horse horse = (Horse) en;

        ScoreboardManager mang = Bukkit.getScoreboardManager();
        Scoreboard board = mang.getNewScoreboard();

        Objective objective = board.registerNewObjective("Stats", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.BLUE +  "Horse Stats");

        Score style = objective.getScore(Bukkit.getOfflinePlayer("Style " + horse.getStyle().toString()));
        Score color = objective.getScore(Bukkit.getOfflinePlayer("Color " + horse.getColor().toString()));
        Score jump = objective.getScore(Bukkit.getOfflinePlayer("Jump Strength "));
        Score speed = objective.getScore(Bukkit.getOfflinePlayer("Speed "));

        AttributeInstance attributes = ((EntityInsentient)((CraftLivingEntity)horse).getHandle()).getAttributeInstance(GenericAttributes.d);
        double hspeed = attributes.getValue();
        int hspeedt = (int) (hspeed * 100);

        style.setScore(1);
        color.setScore(1);
        jump.setScore((int) (horse.getJumpStrength() * 10));
        speed.setScore(hspeedt);

        player.setScoreboard(board);
    }

    public void setRace(Race race, Player player)
    {

    }

    public void removeBoard(Player player)
    {
        ScoreboardManager manger = Bukkit.getScoreboardManager();
        Scoreboard board = manger.getNewScoreboard();

        player.setScoreboard(board);
    }

}

