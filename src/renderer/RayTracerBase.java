package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * Contains a scene field. A constructor that receives an object parameter of a scene.
 * A method that receives a beam in a parameter and returns color
 */
public abstract class RayTracerBase {

    protected Scene _scene;
//    private static final int MAX_CALC_COLOR_LEVEL = 10;
//    private static final double MIN_CALC_COLOR_K = 0.001;

    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    public abstract Color traceRay(Ray ray);

}
