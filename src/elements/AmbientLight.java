package elements;

import primitives.Color;

/**
 * AmbientLight class for "Environmental Lighting".
 * The builder will receive two parameters: Color parameter - in light of original filling
 * And a double-type parameter - a reduction factor of filler light
 * The class contains a Color intensity intensity fill field,
 * a constructor that receives values and calculates the final power of fill,
 * and a getIntensity () function that returns the Color ambient intensity value.
 */
public class AmbientLight extends Light{
    /**
     * constractor
     *
     * @param intensity orginal light intensity parameter
     * @param ka the light intensity damping parameter
     */
    public AmbientLight(Color intensity, double ka) {
        super(intensity.scale(ka));
    }
    public AmbientLight() {
        super(Color.BLACK);
    }
}

