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

/*Start Imports*/
import java.io.File;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import org.tritania.horseteleport.util.Log;
import org.tritania.horseteleport.HorseTeleport;
/*End Imports*/

public class Configuration extends YamlConfiguration
{
    private File file;

    public  boolean craftSaddles;
    public  boolean craftArmor;


    public Configuration(File file)
    {
        this.file = file;

        craftSaddles = false;

    }

    public void load()
    {
        try
        {
            super.load(file);
        }
        catch (Exception e)
        {
            Log.warning("Unable to load: %s", file.toString());
        }

        craftSaddles = getBoolean("Allow craftable saddles", craftSaddles);
        craftSaddles = getBoolean("Allow craftable horse armor", craftArmor);

        if (!file.exists())
            save();

    }

    public void save()
    {
        set("Allow craftable saddles", craftSaddles);
        set("Allow craftable horse armor", craftArmor);
        try
        {
            super.save(file);
        }
        catch (Exception e)
        {
            Log.warning("Unable to save: %s", file.toString());
        }
    }
}


