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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.Bukkit;

import org.tritania.horseteleport.HorseTeleport;
import org.tritania.horseteleport.util.Message;
import org.tritania.horseteleport.util.Log;

public class Recipes
{
    public HorseTeleport ht;

    public Recipes(HorseTeleport ht)
    {
        this.ht = ht;
        addArmor();
        addSadles();
    }

    public void addArmor()
    {

    }

    public void addSadles()
    {
        ShapedRecipe saddle = new ShapedRecipe(new ItemStack(Material.SADDLE));
        saddle.shape("SBS", "SAS", "LLL");
        saddle.setIngredient('S', Material.STRING);
        saddle.setIngredient('A', Material.AIR);
        saddle.setIngredient('B', Material.STICK);
        saddle.setIngredient('L', Material.LEATHER);
        Bukkit.getServer().addRecipe(saddle);
    }
}
