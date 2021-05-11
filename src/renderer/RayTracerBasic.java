package renderer;

import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Class inheriting from the RayTracerBase class.
 * The department will have a builder who receives a scene and operates the master class builder
 */

public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * A method of scanning a beam that will look for cuts between
     * the beam and the 3D model of the scene. If no crop points are found -
     * the background color of the scene will be restored. Otherwise the point
     * closest to the beginning of the beam will be found then we will find the point at this point
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        if (intersections != null) {
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            return calcColor(closestPoint);
        }
        //ray did not intersect any geometrical object
          return _scene.background;

    }

    /**
     * Gets a point parameter and returns color.
     * The method will return the ambient lighting color of the scene
     * Adds the color of the object to the point color
     * @param geo
     * @return
     */
    private Color calcColor(GeoPoint geo)
    {
        Color result=geo.geometry.get_emission();
        return result.add(_scene.ambientlight.getIntensity());
    }
}
