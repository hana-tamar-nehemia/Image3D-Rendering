package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;

import scene.Scene;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;

/**
 * The department's role is to create the color matrix of the image from the scene.
 * The class will contain fields of ImageWriter, Scene, Camera and Horn Scanner
 *
 *  *   @author Tamar & Tehila
 */
public class Render {

    ImageWriter _imageWriter = null;
    Camera _camera = null;
    RayTracerBase _rayTracerBase = null;

    /**
     * setters
     */

    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracerBase = rayTracer;
        return this;
    }

    /**
     * A method that will first check that a blank value was entered
     * in all the fields and in case of lack throws a suitable deviation
     * You will also make a loop on all the pixels of the ViewPlane,
     * for each pixel a beam will be built and for each beam we will get a color from the horn comb.
     * The women color in the appropriate pixel of the image manufacturer
     */
    public void renderImage() {
        try {
            if (_imageWriter == null) {
                throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
            }
            if (_camera == null) {
                throw new MissingResourceException("missing resource", Camera.class.getName(), "");
            }
            if (_rayTracerBase == null) {
                throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
            }

            //rendering the image
            int nX = _imageWriter.getNx();
            int nY = _imageWriter.getNy();
            for (int i = 0; i < nY; i++) {
                for (int j = 0; j < nX; j++) {
                    Ray ray = _camera.constructRayThroughPixel(nX, nY, j, i);
                    Color pixelColor = _rayTracerBase.traceRay(ray);
                    _imageWriter.writePixel(j, i, pixelColor);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }

    /**
     * A method that will first check that a blank value was entered
     * in all the fields and in case of lack throws a suitable deviation
     * for each pixel a beam will be built and for each beam we
     * will get a color from average of all the color from the rays list
     * The women color in the appropriate pixel of the image manufacturer
     *
     *  *   @author Tamar & Tehila
     */
                public void renderImageForSuperSampling() {
                    try {
                        if (_imageWriter == null) {
                            throw new MissingResourceException("missing resource", ImageWriter.class.getName(), "");
                        }
                        if (_camera == null) {
                            throw new MissingResourceException("missing resource", Camera.class.getName(), "");
                        }
                        if (_rayTracerBase == null) {
                            throw new MissingResourceException("missing resource", RayTracerBase.class.getName(), "");
                        }

                        //rendering the image
                        int nX = _imageWriter.getNx();
                        int nY = _imageWriter.getNy();
                        for (int i = 0; i < nY; i++) {
                            for (int j = 0; j < nX; j++) {
                    //get list of rays from the same one pixel
                    List<Ray> rays = _camera.constructRayThroughPixelSuperSample(nX, nY, j, i);
                    //sending the rays to function that calculate the average of all the color of the rays
                    Color k = _rayTracerBase.pixelColorAverage(rays);
                    _imageWriter.writePixel(j, i, k);
                }
            }
        } catch (MissingResourceException e) {
            throw new UnsupportedOperationException("Not implemented yet" + e.getClassName());
        }
    }


    /**
     * A method that will create a grid of lines.
     * Of course first the method will check that in the field of the image manufacturer
     * a non-empty value was entered and in case of lack of throwing an exception
     *
     * @param interval the intervals between the squares
     * @param color the color of the pixel
     */
    public void printGrid(int interval, Color color) {
        int nX = _imageWriter.getNx();
        int nY = _imageWriter.getNy();
        for (int i = 0; i < nY; i++) {
            for (int j = 0; j < nX; j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(j, i, color);
                }
            }
        }
    }

    /**
     * The method will first check that a blank value has been entered
     * in the image maker's field and then run the appropriate image maker method.
     */
    public void writeToImage() {
        _imageWriter.writeToImage();
    }
}



