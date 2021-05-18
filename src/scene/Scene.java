package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The class contains:
 * The name of the scene
 * Default background color - black
 * Default Environmental Lighting - Lighting itself 0 in black
 * 3D model - by default will be initialized to a blank model
 */
public class Scene {
    private final String _name;
    public Color background = Color.BLACK;
    public AmbientLight ambientlight= new AmbientLight(new Color(192, 192, 192),1.d); ;
    public Geometries geometries = null;
    public List<LightSource> _lights= new LinkedList<>();
    /**
     * A builder that gets the name of the scene and also builds an empty
     * collection of bodies for model D3.
     * @param name
     */
    public Scene(String name) {
        _name = name;
        geometries= new Geometries();
    }

    // setters methods

    public Scene setBackground(Color background) {
        this.background = background;
        return  this;
    }

    public Scene setAmbientLight(AmbientLight ambientlight) {
        this.ambientlight = ambientlight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return  this;
    }

}


