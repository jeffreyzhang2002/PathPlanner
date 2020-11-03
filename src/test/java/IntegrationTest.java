import com.pathplanner.geometry.Point2D;
import com.pathplanner.pathplanners.AStar;
import com.pathplanner.pathplanners.LineOfSightStar;
import com.pathplanner.world.actor.planeActor.PlaneLineActor;
import com.pathplanner.world.actor.planeActor.PlanePointActor;
import com.pathplanner.world.actor.properties.BarrierProperties;
import com.pathplanner.world.actor.properties.StopPointProperties;
import com.pathplanner.world.environment.Plane;

public class IntegrationTest
{
    public static void main (String[] args)
    {
        Plane p = new Plane(500,500);

        PlaneLineActor actor = new PlaneLineActor(
                new Point2D<Double>(100.0,100.0),
                new Point2D<Double>(400.0,400.0), new BarrierProperties());

//        LineOfSightStar pathFinder = new LineOfSightStar(p,
//                new Point2D<Double>(100.0, 400.0),
//                new Point2D<Double>(400.0, 100.0));

        p.addActor(actor);

        PlanePointActor pointActorA = new PlanePointActor(100.0, 400.0, new StopPointProperties());
        PlanePointActor pointActorB = new PlanePointActor(200.0, 400.0, new StopPointProperties());
        PlanePointActor pointActorC = new PlanePointActor(300.0, 400.0, new StopPointProperties());
        PlanePointActor pointActorD = new PlanePointActor(200.0, 400.0, new StopPointProperties());
        PlanePointActor pointActorE = new PlanePointActor(100.0, 400.0, new StopPointProperties());
        PlanePointActor pointActorF = new PlanePointActor(400.0, 100.0, new StopPointProperties());

        p.addActor(pointActorA);
        p.addActor(pointActorB);
        p.addActor(pointActorC);
        p.addActor(pointActorD);
        p.addActor(pointActorE);
        p.addActor(pointActorF);

        try{
            LineOfSightStar pathFinder = (LineOfSightStar) PathFinderFactory.newInstance(AStar.class, p);
            System.out.println(pathFinder.getConstraintPoints());

            pathFinder.generatePath(true);
            System.out.println(pathFinder.getPath());

        } catch (Exception e)
        {
            e.printStackTrace();
        }



//        pathFinder.generatePath(false);

        //System.out.println(pathFinder.getPath());
    }
}
