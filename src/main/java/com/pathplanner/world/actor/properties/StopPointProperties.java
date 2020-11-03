package com.pathplanner.world.actor.properties;

import java.util.List;

public class StopPointProperties extends Properties
{
    public final static int WAYPOINT_START = 0;
    public static int WAYPOINT_END = 0;
    private static int count = 0;
    public final int index;

    public StopPointProperties(int index)
    {
        this.index = index;
    }

    public StopPointProperties()
    {
        this.index = count;
        count++;
        WAYPOINT_END = count;
    }

    public boolean isSolid()
    { return false; }

    public boolean isStatic()
    { return true; }

    public static void resetCount()
    {
        count = 0;
        WAYPOINT_END = 0;
    }

    public static int getCount()
    { return count; }
}
