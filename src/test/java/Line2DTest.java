import com.pathplanner.geometry.Line2D;
import com.pathplanner.geometry.Point2D;

public class Line2DTest
{
    public static void main(String[] args)
    {
        Point2D<Integer> p1 = new Point2D<>(10,10);
        Point2D<Integer> p2 = new Point2D<>(5,5);

        Line2D<Integer> line1 = new Line2D<>(p1,p2);

        System.out.println(line1);


        p1.setLocation(15, 15);

        System.out.println(line1);

        line1.getP1().setLocation(15, 15);

        System.out.println(line1);


    }
}
