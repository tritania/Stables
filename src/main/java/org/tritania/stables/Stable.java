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

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

public class Stable implements Serializable
{
    public HashMap<String, String> stableList = new HashMap<String, String>();

    public boolean checkStable(String name)
    {
        if(stableList.containsKey(name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getLocation(String name)
    {
        return stableList.get(name);
    }

    public void addStable(String name, String location)
    {
        stableList.put(name, location);
    }

    public void delStable(String name)
    {
        stableList.remove(name);
    }

    public String getNames()
    {
        String list = "Stables:";
        for (Map.Entry<String, String> entry : stableList.entrySet())
        {
            list += " " + entry.getKey();
        }
        return list;
    }
}

