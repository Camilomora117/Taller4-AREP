package edu.escuelaing.arem.ASE.app.reflexion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class InvokeMain {

    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            Class[] argTypes = new Class[]{String[].class, int.class};
            Method main = c.getDeclaredMethod("main", argTypes);
            String[] mainArgs = Arrays.copyOfRange(args, 1, args.length);
            System.out.format("invoking %s.main()%n", c.getName());
            main.invoke(null, (Object) mainArgs, 1);
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
    }
}