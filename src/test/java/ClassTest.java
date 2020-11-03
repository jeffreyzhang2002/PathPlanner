import com.pathplanner.world.environment.Plane;

import java.lang.reflect.Constructor;

public class ClassTest
{
    public static void main(String... args) {

            Class<?> c = Plane.class;
            Constructor[] allConstructors = c.getDeclaredConstructors();
            for (Constructor ctor : allConstructors) {
                Class<?>[] pType  = ctor.getParameterTypes();
                for (int i = 0; i < pType.length; i++) {
                    System.out.println(ctor.toGenericString());
                }
            }

            // production code should handle this exception more gracefully
    }
}
