package renderer;

import elements.LightSource;
import geometries.Geometries;
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

public class BasicRayTracer extends RayTracerBase {

    public BasicRayTracer(Scene scene) {
        super(scene);
    }
    //Fixed for first magnification of rays for shading
// rays (its value can be reduced according to the
// orders of magnitude of the shapes in your
// image so that the shift is not noticeable
// in the image
//    private static final double DELTA = 0.1;
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

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
          return Color.BLACK;

    }

    /**
     * Gets a point parameter and returns color.
     * The method will return the ambient lighting color of the scene
     * @param geopoint
     * @param ray
     * @return
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientlight.get_intensity());
    }

    private Color calcColor(GeoPoint geopoint, Ray ray, int level, double k) {
        Color color = geopoint.geometry.get_emission();
        color = color.add(calcLocalEffects(geopoint, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray.getDir(), level, k));
    }

    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.get_material();
        double kkr = k * material.Kr;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp, v, n), level, material.Kr, kkr);
        double kkt = k * material.Kt;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(calcGlobalEffect(constructRefractedRay(gp, v, n), level, material.Kt, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = ray.findClosestGeoPoint(_scene.geometries.findGeoIntersections(ray));
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    private Ray constructRefractedRay(GeoPoint point, Vector v, Vector n) {
        return new Ray(point.point,v);
    }

    private Ray constructReflectedRay(GeoPoint point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2*v.dotProduct(n)));
        return new  Ray(point.point,r);
    }



        private Color calcLocalEffects (GeoPoint gpoint, Ray ray){
            Vector v = ray.getDir();
            Vector n = gpoint.geometry.getNormal(gpoint.point);
            double nv = alignZero(n.dotProduct(v));
            if (nv == 0) {
                return Color.BLACK;
            }
            Material material = gpoint.geometry.get_material();
            int nShininess = material._nShininess;
            double kd = material._Kd;
            double ks = material._Ks;
            Color color = Color.BLACK;
            for (LightSource lightSource : _scene._lights) {
                Vector l = lightSource.getL(gpoint.point);
                double ln = alignZero(n.dotProduct(l));
                if (ln * nv > 0) { // sign(nl) == sing(nv)\
                    if (unshaded(lightSource, l,n,gpoint.point)) {
                        Color lightIntensity = lightSource.getIntensity(gpoint.point);
                        color = color.add(calcDiffusive(material._Kd, l, n, lightIntensity),
                                calcSpecular(material._Ks, l, n, v, nShininess, lightIntensity));
                    }
                }
            }
            return color;
        }

    /**
     *
     * @param light
     * @param l
     * @param n
     * @param point
     * @return
     */

            private boolean unshaded(LightSource light, Vector l, Vector n, Point3D point) {
                Vector dir = l.scale(-1);
                Ray lightRay = new Ray(point, dir, n);
                //search for intersections point only between the position of the light source and the object
               List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay, light.getDistance(point));
               
                return intersections == null;
            }



        private Color calcSpecular ( double ks, Vector l, Vector n, Vector v,int nShininess, Color lightIntensity){
            Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
            double vrMinus = v.scale(-1).dotProduct(r);
            double vrN = Math.pow(vrMinus, nShininess);
            return lightIntensity.scale(ks * vrN);

        }

        private Color calcDiffusive ( double kd, Vector l, Vector n, Color lightIntensity){
            double ln = Math.abs(l.dotProduct(n));
            return lightIntensity.scale(kd * ln);
        }
}
