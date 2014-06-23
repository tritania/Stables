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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.Bukkit;

import org.tritania.stables.Stables;
import org.tritania.stables.util.Message;
import org.tritania.stables.util.Log;

public class Recipes
{
    public Stables ht;

    public Recipes(Stables ht)
    {
        this.ht = ht;
        addArmor();
        addSadles();
    }

    public void addArmor()
    {
        if (ht.config.craftArmor)
        {
            ShapedRecipe diamond = new ShapedRecipe(new ItemStack(Material.DIAMOND_BARDING, 1));
            ShapedRecipe gold = new ShapedRecipe(new ItemStack(Material.GOLD_BARDING, 1));
            ShapedRecipe iron = new ShapedRecipe(new ItemStack(Material.IRON_BARDING, 1));

            diamond.shape("  D", "DDD", "D D");
            diamond.setIngredient('D', Material.DIAMOND_BLOCK);

            gold.shape("  D", "DDD", "D D");
            gold.setIngredient('D', Material.GOLD_BLOCK);

            iron.shape("  D", "DDD", "D D");
            iron.setIngredient('D', Material.IRON_BLOCK);

            Bukkit.getServer().addRecipe(diamond);
            Bukkit.getServer().addRecipe(gold);
            Bukkit.getServer().addRecipe(iron);

            Log.info("Recipes for armor added");
        }
    }

    public void addSadles()
    {
        if (ht.config.craftSaddles)
        {
            ShapedRecipe saddle = new ShapedRecipe(new ItemStack(Material.SADDLE, 1));
            saddle.shape("SBS", "S S", "LLL");
            saddle.setIngredient('S', Material.STRING);
            saddle.setIngredient('B', Material.STICK);
            saddle.setIngredient('L', Material.LEATHER);
            Bukkit.getServer().addRecipe(saddle);
            Log.info("Recipe for saddles added");
        }
    }
}
