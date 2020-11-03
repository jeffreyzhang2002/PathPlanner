package com.pathplanner.geometry;

public class Pose2D<T extends Number> extends Point2D<T>
{
    private Vector2D velocity, acceleration;

    public Pose2D(T x, T y, Vector2D velocity, Vector2D acceleration)
    {
        super(x, y);
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Pose2D(Point2D<T> position, Vector2D velocity, Vector2D acceleration)
    {
        super(position);
        this.velocity = velocity;
        this.acceleration = acceleration;
    }

    public Pose2D(T x, T y, Vector2D velocity)
    {
        super(x,y);
        this.velocity = velocity;
        acceleration = new Vector2D(0, 0);
    }

    public Pose2D(Point2D<T> position, Vector2D velocity)
    {
        super(position);
        this.velocity = velocity;
        acceleration = new Vector2D(0, 0);
    }

    public Pose2D(Point2D<T> position)
    {
        super(position);
        this.velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0, 0);
    }

    public Pose2D(T x, T y)
    {
        super(x,y);
        this.velocity = new Vector2D(0,0);
        acceleration = new Vector2D(0, 0);
    }

    public Vector2D getVelocity()
    { return velocity; }

    public void setVelocity(Vector2D velocity)
    { this.velocity = velocity; }

}
