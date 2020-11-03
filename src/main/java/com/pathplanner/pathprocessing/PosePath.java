package com.pathplanner.pathprocessing;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.geometry.Pose2D;
import com.pathplanner.geometry.Vector2D;

import java.util.List;

public class PosePath extends Path
{
    public PosePath(List<Pose2D<Double>> list)
    {
        super(list);
    }

    @Override
    public Pose2D<Double> getPosition(int index) {
        return (Pose2D<Double>) super.getPosition(index);
    }

    public Vector2D getVelocity(int index)
    { return ((Pose2D<Double>) super.getPosition(index)).getVelocity(); }
}
