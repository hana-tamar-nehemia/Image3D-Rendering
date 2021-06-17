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
 * RayTracerBasic class represent a ray scanner on a scene
 *
 *   @author Tamar & Tehila
 */
public class BasicRayTracer extends RayTracerBase {

    /**
     * the constructor of RayTracerBase
     * @param scene the scene that the ray tracer scan
     */
    public BasicRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * Fixed for first magnification of rays for shading
     * rays (its value can be reduced according to the
     * orders of magnitude of the shapes in your
     * image so that the shift is not noticeable
     * in the image
     */
    //private static final double DELTA = 0.1;
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    /**
     * trace ray- get a ray and return the color
     * of the closest intersection point with the ray
     * @param ray the ray
     * @return color
     */
    @Override
    public Color traceRay(Ray ray) {
        //find all the intersections of the ray with the geometries
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(ray);
        //if there are intersections
        if (intersections != null) {
            //find the closest intersection point with the ray
            GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
            //return the color of the closest intersection point
            return calcColor(closestPoint,ray);
        }
        //ray did not intersect any geometrical object so return black
          return Color.BLACK;

    }

    /**
     * Check if there is a shadow on the geometry object
     * @param light the kind of light source on the object
     * @param l the direction of the ray that intersect with the object
     * @param n the normal of the ray
     * @param point the intersection point of the ray with the object
     * @return true if there is no shadow, otherwise- false
     */
    private boolean unshaded(LightSource light, Vector l, Vector n, Point3D point) {
        Vector dir = l.scale(-1);
        Ray lightRay = new Ray(point, dir, n);
        //search for intersections point only between the position of the light source and the object
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay, light.getDistance(point));
        if( intersections == null)
            return true;
        double lightDistance = light.getDistance(point);
        for (GeoPoint gp : intersections) {
            if(alignZero(gp.point.distance(point) - lightDistance) <= 0 && gp.geometry.get_material().Kt == 0)
                return false;
        }
        return true;
    }
    /**
     * the specular part in the Phong model formula
     * @return the color created in this part
     */
    private Color calcSpecular ( double ks, Vector l, Vector n, Vector v,int nShininess, Color lightIntensity){
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        double vrMinus = v.scale(-1).dotProduct(r);
        double vrN = Math.pow(vrMinus, nShininess);
        return lightIntensity.scale(ks * vrN);

    }

    /**
     * the diffusive part in the Phong model formula
     * @return the color created in this part
     */
    private Color calcDiffusive ( double kd, Vector l, Vector n, Color lightIntensity){
        double ln = Math.abs(l.dotProduct(n));
        return lightIntensity.scale(kd * ln);
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

    /**
     *
     * @param geopoint
     * @param ray
     * @param level
     * @param k
     * @return
     */
    private Color calcColor(GeoPoint geopoint, Ray ray, int level, double k) {
        Color color = geopoint.geometry.get_emission();
        color = color.add(calcLocalEffects(geopoint, ray,k));
        return 1 == level ? color : color.add(calcGlobalEffects(geopoint, ray.getDir(), level, k));
    }

    /**
     * get a GeoPoint on a certain object and a ray and find the color in this point by considering all the effects (lights) in the scene
     * @param gp the intersection point in an object
     * @param v the vector of the ray that was sending to the object
     * @return the color of the receiving geoPoint
     */
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


    private Color calcLocalEffects(GeoPoint geopoint, Ray inRay, double k) {
        Color color = geopoint.geometry.get_emission();

        Vector v = inRay.getDir();
        Vector n = geopoint.geometry.getNormal(geopoint.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) {
            return Color.BLACK;
        }

        Material material = geopoint.geometry.get_material();
        int nShininess = material._nShininess;

        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(geopoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, geopoint);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(geopoint.point).scale(ktr);
                    color = color.add(calcDiffusive(material._Kd, l, n, lightIntensity),
                            calcSpecular(material._Ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }
    /**
     *
     * A method that takes from each beam
     * in the list the color that
     * came out and makes them average and
     * returns the new color
     *
     * @param rays the list of tay that hit the objet in one pixel
     * @return
     */
    public Color pixelColorAverage(List<Ray> rays) {
        Color color = Color.BLACK;
        for (Ray n : rays){
            color = color.add(traceRay(n));
        }
        return color.scale(1.d/rays.size());
    }

    /**
     * create the refracted ray of a given ray
     * @param point the start of the given ray
     * @param v the direction of the given ray
     * @param n the normal of the given ray
     * @return the refracted ray
     */
    private Ray constructRefractedRay(GeoPoint point, Vector v, Vector n) {
        return new Ray(point.point,v,n);
    }

    /**
     * create the reflected ray of a given ray
     * @param point the start of the ray
     * @param v the direction of the given ray
     * @param n the normal of the given ray
     * @return the reflected ray
     */
    private Ray constructReflectedRay(GeoPoint point, Vector v, Vector n) {
        Vector r = v.subtract(n.scale(2*v.dotProduct(n)));
        return new  Ray(point.point,r,n);
    }
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        var intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null)
            return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.get_material().Kt;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }





        private GeoPoint findClosesIntersektion(Ray ray){
                var points=_scene.geometries.findGeoIntersections(ray);
                return ray.findClosestGeoPoint(points);
        }
}
