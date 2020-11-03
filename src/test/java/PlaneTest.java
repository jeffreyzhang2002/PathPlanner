import com.pathplanner.geometry.Line2D;
import com.pathplanner.geometry.Point2D;
import com.pathplanner.world.actor.planeActor.PlaneLineActor;
import com.pathplanner.world.actor.properties.Properties;
import com.pathplanner.world.environment.Plane;

public class PlaneTest
{
    public static void main(String[] args)
    {
        Plane p = new Plane(100,100);
        p.addActor(
                new PlaneLineActor(
                        new Line2D<Double>(
                                new Point2D<Double>(10.0,10.0),
                                new Point2D<Double>(10.0,90.0)),
                        new Properties()));

        System.out.println(p.getBoundingPoints());
        System.out.println(p.getVertexPoints());
        System.out.println(p.LineOfSight(new Point2D(20,10), new Point2D(20,90)));
    }
}
