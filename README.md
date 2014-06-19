HorseTeleport
=============

A Bukkit plugin to allow teleportation of horses on Minecraft multiplayer servers

## Commands ##
* /htpa playername      Allows the sender to teleport his friend on his horse to him
* /htpahere playername  Allows the sender to teleport himself on his horse to his friend
* /haccept              Accepts any pending teleportation requests
* /hdeny                Denies any pending teleportation requests
* /setstable            allowing a bed type spawn for the horse
* /stable               teleports only the horse back to the stable location
* /delstable            deletes a stable
* /hspawn               creates a horse to the given parameters

## Permissons ##
* horseteleport.teleport        Players with this permisson can teleport while on their horse
* horseteleport.spawn           Players with this permisson can spawn horses with /hspawn

## Configuration ##
These values are set to false by default to enable them set them to true
* Allow craftable saddles       Allows saddles to be crafted
* Allow craftable horse armor   Allows armor to be crafted


## Installation ##
To build and install this plugin simply install apache maven download this source code and run:
```mvn clean package install```
inside the source code directory. Then move the jar file inside of the target directory to your server's plugin directory.


