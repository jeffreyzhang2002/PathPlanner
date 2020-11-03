import com.pathplanner.geometry.Point2D;

public class Point2DTest
{
    public static void main(String[] args)
    {
        Point2D<Integer> p1 = new Point2D<>(10,10);
        Point2D<Integer> p2 = new Point2D<>(p1);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println("Expected: (10,10) + (10,10)");


        p1.setLocation(20, 20);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println("Expected: (20,20) + (10,10)");

        p2.setLocation(-10, -10);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println("Expected: (20,20) + (-10,-10)");

    }

}
