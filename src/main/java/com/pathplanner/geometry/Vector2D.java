package com.pathplanner.geometry;

import java.lang.Math;

/**
 * An extension to the relatively impotent java.awt.geom.Point2D.Double,
 * Vector2D allows mathematical manipulation of 2-component vectors.
 */
public class Vector2D extends Point2D<Double>
{

    public Vector2D(double i, double j)
    { super(i,j); }


    public Vector2D(Vector2D v)
    {
        super(v);
    }

    public double getMag()
    { return Math.hypot(super.getX().doubleValue(), super.getY().doubleValue()); }

    public double getAngle()
    { return Math.atan2(super.getY().doubleValue(), super.getX().doubleValue()); }

    public void setPolar(double mag, double angle)
    { super.setLocation((mag * Math.cos(angle)), (mag * Math.sin(angle))); }

    /** Sets the vector's radius, preserving its angle. */
    public void setMag(double mag)
    { setPolar(mag, getAngle()); }

    /** Sets the vector's angle, preserving its radius. */
    public void setTheta(double angle)
    { setPolar(getMag(), angle); }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.getX() + other.getX(), this.getY() + other.getY());
    }

    /** The difference of the vector and rhs: this - rhs */
    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.getX() - other.getX(), this.getY() - other.getY());
    }

    /** Product of the vector and scalar */
    public Vector2D scale(double scalar)
    { return new Vector2D(scalar * super.getX(), scalar * super.getY()); }

    /** Dot product of the vector and rhs */
    public double dotProduct(Vector2D other) {
        return super.getX() * other.getX() + super.getY() * other.getY();
    }

    /**
     * Since Vector2D works only in the x-y plane, (u x v) points directly along
     * the z axis. This function returns the value on the z axis that (u x v)
     * reaches.
     *
     * @return signed magnitude of (this x rhs)
     */
    public double crossProduct(Vector2D other) {
        return super.getX() * other.getY() - super.getY() * other.getX();
    }

    /** Product of components of the vector: compenentProduct( <x y>) = x*y. */
    public double componentProduct() {
        return super.getX() * super.getY();
    }

    /** Componentwise product: <this.x*rhs.x, this.y*rhs.y> */
    public Vector2D componentwiseProduct(Vector2D rhs) {
        return new Vector2D(super.getX() * rhs.getX(), super.getY() * rhs.getY());
    }

    /**
     * Returns a new vector with the same direction as the vector but with
     * length 1, except in the case of zero vectors, which return a copy of
     * themselves.
     */
    public static Vector2D unitVector(Vector2D v) {
        if (v.getMag() != 0) {
            return new Vector2D(v.getX() / v.getMag(), v.getY() / v.getMag());
        }
        return new Vector2D(0,0);
    }

    public void unitVector() {
        if (getMag() != 0) {
            super.setLocation(super.getX() / getMag(), super.getY() / getMag());
        }
        super.setLocation(0.0,0.0);
    }

//    /** Polar version of the vector, with radius in x and angle in y */
//    public Vector2D toPolar() {
//        return new Vector2D(Math.sqrt(super.getX() * super.getX() + super.getY() * super.getY()), Math.atan2(super.getY(), super.getX()));
//    }
//
//    /** Rectangular version of the vector, assuming radius in x and angle in y*/
//    public Vector2D toRect() {
//        return new Vector2D(super.getX() * Math.cos(super.getY()), super.getX() * Math.sin(super.getY()));
//    }

    /** @return Standard string representation of a vector: "<x, y>" */
    public String toString() {
        return "<" + super.getX() + ", " + super.getY() + ">";
    }

    public static Vector2D add(Vector2D a, Vector2D b)
    { return a.add(b); }

    public static Vector2D subtract(Vector2D a, Vector2D b)
    { return a.subtract(b); }

    public static double dotProduct(Vector2D a, Vector2D b)
    { return a.dotProduct(b); }

    public static double crossProduct(Vector2D a, Vector2D b)
    { return a.crossProduct(b); }
}
