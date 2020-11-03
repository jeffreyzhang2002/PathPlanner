package com.pathplanner.world.actor.properties;

public class StartPointProperties extends StopPointProperties
{
    public StartPointProperties()
    {
        super(0);
        if(super.getCount() != 0)
            throw new IllegalStateException("Stop points have already been created");
    }
}
