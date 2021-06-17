package elements;

import primitives.Color;

/**
 * Abstract class for light sources
 *
 *  * @author Tamar & Tehila
 */

abstract class Light {

    protected Color _intensity;

    /**
     * constructor for the power of the light
     * @param intensity  the color of the light
     */

    protected Light(Color intensity) {
        this._intensity = intensity;
    }

    /**
     *  function that returns the Color ambient intensity value.
     * @return  Color ambient intensity value
     */
    public Color get_intensity() {
        return _intensity;
    }
}
