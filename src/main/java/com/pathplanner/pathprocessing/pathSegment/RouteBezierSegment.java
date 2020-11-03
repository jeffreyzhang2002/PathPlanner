package com.pathplanner.pathprocessing.pathSegment;

import com.pathplanner.geometry.Vector2D;

import java.awt.geom.Point2D;
import java.util.List;

public class RouteBezierSegment<E extends Point2D> extends RouteSegment<E>
{
    public RouteBezierSegment(E startPoint)
    {
        super();
        super.addConstraint(0,startPoint);
    }

    public RouteBezierSegment(List<E> constraintPoint)
    { super(constraintPoint); }

    public RouteBezierSegment()
    { super(); }

    /**
     * Returns the minimum number of points required to fully define this route segment. This value is defaulted at 2 and should not be less than 2
     * @return the minimum required constraint points. Defaults at 2
     */
    public int getMinConstraints()
    { return 3; }

    /**
     * Returns the maximum number of points that can be used to fully define this route segment. This value is defaulted at 2. If there is no maximum, this should return
     * maximum integer value
     * @return the maximum number of constraint points allowed.
     */
    public int getMaxConstraints()
    { return Integer.MAX_VALUE; }

    public void recalculateSegment() {

    }

    @Override
    public Point2D.Double _getPoint(double t) {
        return null;
    }

    @Override
    public double getDistance() {
        return 0;
    }

    @Override
    public Vector2D _getTangent(double t) {
        return null;
    }
}