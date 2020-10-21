package com.pathplannertesting.geometry;

import com.pathplanner.geometry.Point2D;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class Point2DTest
{
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void setY()
    {
        Point2D<Double> point = new Point2D<>(10.0, 10.0);
        Assert.assertEquals(point.Y().doubleValue(), 10.0, 0.1);

        point.setY(5.0);
        Assert.assertEquals(point.Y().doubleValue(), 5.0, 0.1);

        point.setY(-5.1);
        Assert.assertEquals(point.Y().doubleValue(), -5.1, 0.1);

        point.setY(-7.2);
        Assert.assertNotEquals(point.Y().doubleValue(), -5.1, 0.1);

        point.setY(Double.MAX_VALUE);
        Assert.assertEquals(point.Y(), Double.MAX_VALUE, 0.1);

        point.setY(Double.MIN_VALUE);
        Assert.assertEquals(point.Y(), Double.MIN_VALUE, 0.1);

        point.setY(Double.MIN_NORMAL);
        Assert.assertEquals(point.Y(), Double.MIN_NORMAL, 0.1);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("non-null value expected");

        point.setY(Double.NaN);

        point.setY(null);

        Point2D<Integer> pointA = new Point2D<>(10, 10);
        Assert.assertEquals(pointA.Y().intValue(), 10);

        pointA.setY(5);
        Assert.assertEquals(pointA.Y().intValue(), 5);

        pointA.setY(-5);
        Assert.assertEquals(pointA.Y().intValue(), -5);

        pointA.setY(-7);
        Assert.assertNotEquals(pointA.Y().doubleValue(), -5);

        pointA.setY(Integer.MAX_VALUE);
        Assert.assertEquals(pointA.Y().intValue(), Integer.MAX_VALUE);

        pointA.setY(Integer.MIN_VALUE);
        Assert.assertEquals(pointA.Y().intValue(), Integer.MIN_VALUE);


    }

    @Test
    public void setLocation1()
    {
        Point2D<Double> point = new Point2D<>(10.0, 10.0);

        point.setLocation(new Point2D(5.2,5.3));

        assert(point.getX().doubleValue() == 5.2);
        assert(point.getY().doubleValue() == 5.3);

        Point2D<Integer> pointA = new Point2D<>(10, 10);

        pointA.setLocation(new Point2D(5.0,5.0));

        assert(pointA.getX().doubleValue() == 5);
        assert(pointA.getY().doubleValue() == 5);
    }

    @Test
    public void distance()
    {
        Point2D<Double> pointA = new Point2D<>(0.0,0.0);
        Point2D<Double> pointB = new Point2D<>(3.0,4.0);
        assert (pointA.distance(pointB) == 5);

        Point2D<Integer> pointC = new Point2D<>(3,4);
        assert (pointA.distance(pointC) == 5);

        Point2D<Integer> pointD = new Point2D<>(0,0);
        assert (pointA.distance(pointD) == 0);

        assert (pointA.distance(pointB) == Point2D.distance(pointA, pointB));

        Point2D<Integer> pointE = new Point2D<>(4,3);
        System.out.println(pointE.distance(pointA));

    }

    @Test
    public void distanceSq()
    {
        Point2D<Double> pointA = new Point2D<>(0.0,0.0);
        Point2D<Double> pointB = new Point2D<>(3.0,4.0);
        assert (Math.pow(pointA.distance(pointB),2) == pointA.distanceSq(pointB));

        Point2D<Integer> pointC = new Point2D<>(3,4);
        assert (Math.pow(pointA.distance(pointC),2) == pointA.distanceSq(pointC));

        Point2D<Integer> pointD = new Point2D<>(0,0);
        assert (pointA.distanceSq(pointD) == 0);

        assert (pointA.distanceSq(pointB) == Point2D.distanceSq(pointA, pointB));

        Point2D<Integer> pointE = new Point2D<>(4,3);
        System.out.println(pointE.distanceSq(pointA));
    }

    @Test
    public void equals()
    {
        Point2D<Integer> pointA = new Point2D(10,10);
        Point2D<Double> pointB = new Point2D(10.0,10.0);
        Point2D<Float> pointC = new Point2D(10f,10f);
        Point2D<Integer> pointD = new Point2D(5,5);


        assert (pointA.equals(pointB));
        assert (pointA.equals(pointC));
        assert (pointB.equals(pointC));
        assert !(pointA.equals(pointD));
    }
}