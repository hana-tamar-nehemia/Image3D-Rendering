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
public class AmbientLight {
    /**
     * Light intensity
     */
    final private Color _intensity;

    public AmbientLight() {
        _intensity=Color.BLACK;
    }

    /**
     * Constructor
     * @param Ia intensity color
     * @param Ka constant for intensity
     */
    public AmbientLight(Color Ia, double Ka) {
        _intensity = Ia.scale(Ka);
    }

    /**
     * get intensity color
     * @return intensity
     */
    public Color getIntensity() {
        return _intensity;
    }

}

