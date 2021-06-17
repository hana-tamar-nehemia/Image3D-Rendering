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
 *
 *  @author Tamar & Tehila
 */
public class Scene {
    private final String _name;//the name of the scene
    public Color background = Color.BLACK;//the color background of the scene
    public AmbientLight ambientlight= new AmbientLight(); ;//the ambient light of the scene
    public Geometries geometries = null;//the geometries participate in the scene
    public List<LightSource> _lights= new LinkedList<>();//all the lights participate in the scene
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


