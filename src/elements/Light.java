package elements;

import primitives.Color;

/**
 * Abstract class for light sources
 */
abstract class Light {
    protected Color _intensity;

    /**
     * constractor
     * @param intensity
     */
    protected  Light(Color intensity) {
        this._intensity = intensity;
    }

    /**
     * getter method
     * @return
     */
    public Color get_intensity() {
        return _intensity;
    }
}
