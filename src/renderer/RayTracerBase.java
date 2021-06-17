package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.List;

/**
 * Contains a scene field. A constructor that receives an object parameter of a scene.
 * A method that receives a beam in a parameter and returns color
 *
 *   @author Tamar & Tehila
 */
public abstract class RayTracerBase {

    protected Scene _scene;

    /**
     * the constructor of RatTracerBase
     * @param scene the scene that the ray tracer scan
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * get a ray and trace the scene with it. find the color of the intersection point with the ray
     * @param ray the ray that trace the scene
     * @return the color of the intersection point
     */
    public abstract Color traceRay(Ray ray);

    public abstract Color pixelColorAverage(List<Ray> rays);
}
