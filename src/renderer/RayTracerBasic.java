package renderer;

import elements.LightSource;
import geometries.Intersectable;
import static geometries.Intersectable.GeoPoint;
import static primitives.Util.alignZero;

import primitives.*;
import scene.Scene;

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
            return calcColor(closestPoint,ray);
        }
        //ray did not intersect any geometrical object
          return _scene.background;

    }

    /**
     * Gets a point parameter and returns color.
     * The method will return the ambient lighting color of the scene
     * @param point
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint point, Ray ray) {
        Color result = point.geometry.get_emission();
        Color basicColor= _scene.ambientlight.get_intensity().add(result);
        //Color basicColor =result.add(_scene.ambientlight.getIntensity());
        return basicColor.add(calcLocalEffects(point,ray));
    }

    private Color calcLocalEffects(GeoPoint Gpoint, Ray ray) {
        Vector v = ray.getDir ();
        Vector n = Gpoint.geometry.getNormal(Gpoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
        {
            return Color.BLACK;
        }
        Material material = Gpoint.geometry.get_material();
        int nShininess = material._nShininess;
        double kd = material._Kd;
        double ks = material._Ks;
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(Gpoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(Gpoint.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
    Vector r= l.subtract(n.scale(l.dotProduct(n)*2));
    double vrMinus= v.scale(-1).dotProduct(r);
    double vrN= Math.pow(vrMinus,nShininess);
    return lightIntensity.scale(ks*vrN);

    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd*ln);
    }

}
