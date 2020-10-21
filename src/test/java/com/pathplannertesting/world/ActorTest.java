package com.pathplannertesting.world;

import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.Actor;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ActorTest {

    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getPosition()
    {
        Actor<Double> actorA = new ActorWrapper<Double>();
        assertEquals(actorA.getPosition(), new Point2D<Double>(0.0, 0.0));

        actorA.setPosition(new Point2D<Double>(15.0,15.0));
        assertEquals(actorA.getPosition(), new Point2D<Double>(0.0, 0.0));

        System.out.println("Actor A: " + actorA.isSolid + " " + actorA.isStatic);

        Actor<Double> actorB = new ActorWrapper<Double>(new Point2D<Double>(15.0, 15.0));
        assertEquals(actorB.getPosition(), new Point2D<Double>(15.0, 15.0));

        actorB.setPosition(new Point2D<Double>(10.0,10.0));
        assertEquals(actorB.getPosition(), new Point2D<Double>(15.0, 15.0));

        System.out.println("Actor B: " + actorB.isSolid + " " + actorB.isStatic);

        Actor<Double> actorC = new ActorWrapper<Double>(new Point2D<Double>(15.0,15.0), false, false);
        assertEquals(actorC.getPosition(), new Point2D<Double>(15.0, 15.0));

        actorC.setPosition(new Point2D<Double>(25.0,25.0));
        assertEquals(actorC.getPosition(), new Point2D<Double>(25.0, 25.0));

        System.out.println("Actor C: " + actorC.isSolid + " " + actorC.isStatic);

        Actor<Double> actorD = new ActorWrapper<Double>();
        assertEquals(actorD.getPosition(), new Point2D<Double>(0.0, 0.0));

        actorA.setPosition(null);

        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("non-null value expected");

        System.out.println("Actor D: " + actorD.isSolid + " " + actorD.isStatic);

    }

    @Test
    public void setPosition()
    {
        Actor<Integer> actor = new ActorWrapper<>(new Point2D<>(10,10), false, false);
        actor.setPosition(new Point2D<Integer>(15, 15));
        assertEquals(actor.getPosition(), new Point2D<Integer>(15, 15));
    }

    @Test
    public void toStringTest()
    {
        Actor<Integer> actor = new ActorWrapper<>(new Point2D<>(10,10), false, false);
        System.out.println(actor);
    }


    public class ActorWrapper<E extends Number> extends Actor<E>
    {
        public ActorWrapper(Point2D<E> p)
        {
            super(p);
        }

        public ActorWrapper()
        {
            super();
        }

        public ActorWrapper(Point2D<E> p, boolean a, boolean b)
        {
            super(p,a,b);
        }
    }
}