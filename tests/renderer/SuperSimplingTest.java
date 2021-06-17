package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class SuperSimplingTest
{

    private Camera camera = new Camera(new Point3D(0,0,1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setDistance(1000) //
            .setViewPlaneSize(500, 500)
            .setNumOfRays(81);

    @Test
    public void TestWithSuperSampling() {
        Scene scene = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLUE), 0.10));

        scene.geometries.add(
                new Triangle(new Point3D(-100, -100, 0), new Point3D(-100, 100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.ORANGE)), // left

                new Triangle(new Point3D(100, 100, 0), new Point3D(100, -100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.RED)), // right

                new Triangle(new Point3D(-100, 100, 0), new Point3D(100, 100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(new Point3D(-100, -100, 0), new Point3D(100, -100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.GREEN)), //down

                new Sphere(60d, new Point3D(-100, -100, 0))
                        .set_emission(new Color(java.awt.Color.ORANGE)) // left down
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Sphere(60d, new Point3D(100, 100, 0))
                        .set_emission(new Color(java.awt.Color.RED)) // right up
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Sphere(60d, new Point3D(-100, 100, 0))
                        .set_emission(new Color(java.awt.Color.BLUE)) // left up
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Sphere(60d, new Point3D(100, -100, 0))
                        .set_emission(new Color(java.awt.Color.GREEN)) // right down
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)));


        scene._lights.add( //
                new SpotLight(new Color(700,600,600), new Point3D(0, 0, 300), new Vector(-1, -1, -4))//
                        .setKl(4E-3).setKq(2E-7)
        );

        ImageWriter imageWriter = new ImageWriter("With SuperSampling", 1000, 1000);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));

        //here we do the multi ays
        render.renderImageForSuperSampling();
        render.writeToImage();
    }

    @Test
    public void TestWithoutSuperSampling() {
        Scene scene = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLUE), 0.10));

        scene.geometries.add(
                new Triangle(new Point3D(-100, -100, 0), new Point3D(-100, 100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.ORANGE)), // left

                new Triangle(new Point3D(100, 100, 0), new Point3D(100, -100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.RED)), // right

                new Triangle(new Point3D(-100, 100, 0), new Point3D(100, 100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(new Point3D(-100, -100, 0), new Point3D(100, -100, 0), new Point3D(0 ,0, 0))
                        .set_emission(new Color(java.awt.Color.GREEN)), //down

                new Sphere(60d, new Point3D(-100, -100, 0))
                        .set_emission(new Color(java.awt.Color.ORANGE)) // left down
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Sphere(60d, new Point3D(100, 100, 0))
                        .set_emission(new Color(java.awt.Color.RED)) // right up
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Sphere(60d, new Point3D(-100, 100, 0))
                        .set_emission(new Color(java.awt.Color.BLUE)) // left up
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Sphere(60d, new Point3D(100, -100, 0))
                        .set_emission(new Color(java.awt.Color.GREEN)) // right down
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)));


        scene._lights.add( //
                new SpotLight(new Color(700,600,600), new Point3D(0, 0, 300), new Vector(-1, -1, -4))//
                        .setKl(4E-3).setKq(2E-7)
        );

        ImageWriter imageWriter = new ImageWriter("Without SuperSampling", 1000, 1000);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer(new BasicRayTracer(scene));

        //here we do the one ray
        render.renderImage();
        render.writeToImage();
    }

    private Camera camera2 = new Camera(new Point3D(0,300,0), new Vector(0,-1,0), new Vector(0,0,1)) //
            .setViewPlaneSize(200, 200).setDistance(1000);

    @Test
    public void level2() {

             Point3D[] pnts = new Point3D[] {  //
            new Point3D(-6.99, 0.63, 16.08), //I 0
            new Point3D(-3.59, -2.69, 14.69), //J 1
            new Point3D(2.87 ,-3.26, 14.57), //K2
            new Point3D( 6.75,0.02 ,16.12), //L3
            new Point3D(7.05, 6.34, 18.93), ////m4
            new Point3D(3.73, 9.59,  20.29), //n5
            new Point3D(- 3.2, 9.95, 20.3), //o6
            new Point3D(- 6.87, 6.82, 18.83), //p7
            new Point3D(- 3.63, 3.44,17.4 ), //Q8
            new Point3D(0.13, 6.59, 18.88 ), //R9
            new Point3D(- 0.23, - 0.1,  15.91), //s10
            new Point3D( 3.42,3.26,  17.48 ), //T11
            new Point3D( -0.3, - 5.91, 13.34), //E12
            new Point3D(-10.31 , 3.88 ,17.45), //H13
            new Point3D(10.31 ,3.16, 17.59 ), //F14
            new Point3D(0.3 , 12.95,21.7 ), //G15

                new Point3D(0,0,0), //A16
                new Point3D(10.35, 9.08, 4.25), //B17
                new Point3D(7.41, 6.33, 2.97), //M 18
                new Point3D(3.74, 3.2, 1.5 ), //N 19
                new Point3D(- 0.2, - 3.98, 8.98 ), //Q 20
                new Point3D(- 0.1, - 1.93, 4.37), //R 21
                new Point3D( 10.41 ,5.16, 13.0 ), //S 22
                new Point3D( 10.52, 7.21 ,8.44), //T 23
                new Point3D(3.20, - 0.89 ,10.36), //U 24
                new Point3D(3.5, 1.25 ,5.79 ), //V 25
                new Point3D(7.0, 2.39, 11.83),//W 26
                new Point3D(7.30, 4.45, 7.21), //Z 27

                new Point3D(- 3.45, - 0.21, 10.32 ), // V 28
                new Point3D(- 6.9, 2.56, 11.75 ), // U 29
                new Point3D(-10.21 , 5.79, 13.12), // N 30
                     new Point3D(-3.3 , 1.19, 5.7), // T 31
                     new Point3D(-6.81 ,4.67, 7.15), //S 32
                new Point3D(- 10.11, 7.83, 8.52), //M 33
                     new Point3D( -3.16, 3.09, 1.3 ), //L 34
                     new Point3D( -6.72, 6.57, 2.76), //K 35
                     new Point3D(- 10.01 ,9.79, 4.11)}; //D 36

        Scene scene = new Scene("Test scene")
                .setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLUE), 0.10));

        scene.geometries.add(

                new Sphere(3, new Point3D(15, 0, 0))
                        .set_emission(new Color(java.awt.Color.GREEN)) // right down
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Sphere(5, new Point3D(18, -18, 10))
                        .set_emission(new Color(java.awt.Color.RED)) // right down
                        .set_material(new Material().setKd(0.5).setKs(0.5).setShininess(30)),

                new Triangle(pnts[13], pnts[7], pnts[0])
                        .set_emission(new Color(java.awt.Color.BLUE)),

                new Triangle(pnts[0], pnts[8], pnts[7])
                        .set_emission(new Color(java.awt.Color.BLUE)), // left

                new Triangle(pnts[6], pnts[7], pnts[8])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // right

                new Triangle(pnts[8], pnts[9], pnts[6])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // right

                new Triangle(pnts[15], pnts[9], pnts[6])
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(pnts[15], pnts[9], pnts[5])
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(pnts[1], pnts[0], pnts[8])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // up

                new Triangle(pnts[1], pnts[8], pnts[10])
                        .set_emission(new Color(java.awt.Color.ORANGE)), //down

                new Triangle(pnts[8], pnts[9], pnts[10])
                        .set_emission(new Color(java.awt.Color.WHITE)), //down

                new Triangle(pnts[9], pnts[10], pnts[11])
                        .set_emission(new Color(java.awt.Color.WHITE)), // left

                new Triangle(pnts[9], pnts[5], pnts[11])
                        .set_emission(new Color(java.awt.Color.ORANGE )), // left

                new Triangle(pnts[4], pnts[5], pnts[11])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // right

                new Triangle(pnts[1], pnts[10], pnts[12])
                        .set_emission(new Color(java.awt.Color.BLUE)), // right

                new Triangle(pnts[2], pnts[12], pnts[10])
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(pnts[2], pnts[11], pnts[10])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // up

                new Triangle(pnts[2], pnts[3], pnts[11])
                        .set_emission(new Color(java.awt.Color.orange)), // up

                new Triangle(pnts[3], pnts[4], pnts[11])
                        .set_emission(new Color(java.awt.Color.BLUE)), //down

                new Triangle(pnts[14], pnts[4], pnts[3])
                        .set_emission(new Color(java.awt.Color.BLUE)),//down

        //********************************************************************************///

        new Triangle(pnts[12], pnts[2], pnts[20])
                .set_emission(new Color(java.awt.Color.ORANGE)), // left

                new Triangle(pnts[20], pnts[2], pnts[24])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // left

                new Triangle(pnts[3], pnts[2], pnts[24])
                        .set_emission(new Color(java.awt.Color.RED)), // right

                new Triangle(pnts[3], pnts[24], pnts[26])
                        .set_emission(new Color(java.awt.Color.RED)), // right

                new Triangle(pnts[3], pnts[14], pnts[26])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // up

                new Triangle(pnts[22], pnts[14], pnts[26])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // up

                new Triangle(pnts[21], pnts[20], pnts[24])
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(pnts[21], pnts[24], pnts[25])
                        .set_emission(new Color(java.awt.Color.BLUE)), //down

                new Triangle(pnts[24], pnts[25], pnts[26])
                        .set_emission(new Color(java.awt.Color.WHITE)), //down

                new Triangle(pnts[27], pnts[25], pnts[26])
                        .set_emission(new Color(java.awt.Color.WHITE)), //down

                new Triangle(pnts[22], pnts[26], pnts[27])
                        .set_emission(new Color(java.awt.Color.GREEN)), // left

                new Triangle(pnts[23], pnts[22], pnts[27])
                        .set_emission(new Color(java.awt.Color.GREEN )), // left

                new Triangle(pnts[16], pnts[21], pnts[25])
                        .set_emission(new Color(java.awt.Color.WHITE)), // right

                new Triangle(pnts[16], pnts[19], pnts[25])
                        .set_emission(new Color(java.awt.Color.WHITE)), // right

                new Triangle(pnts[19], pnts[25], pnts[27])
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(pnts[19], pnts[27], pnts[18])
                        .set_emission(new Color(java.awt.Color.BLUE)), // up

                new Triangle(pnts[23], pnts[27], pnts[18])
                        .set_emission(new Color(java.awt.Color.RED)), // up

                new Triangle(pnts[23], pnts[18], pnts[17])
                        .set_emission(new Color(java.awt.Color.RED)), //down
        //**********************************************************************************//

                new Triangle(pnts[13], pnts[29], pnts[30])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // left

                new Triangle(pnts[13], pnts[0], pnts[29])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // left

                new Triangle(pnts[0], pnts[28], pnts[29])
                        .set_emission(new Color(java.awt.Color.BLUE)), // right

                new Triangle(pnts[1], pnts[0], pnts[28])
                        .set_emission(new Color(java.awt.Color.BLUE)), // right

                new Triangle(pnts[1], pnts[20], pnts[28])
                        .set_emission(new Color(java.awt.Color.WHITE)), // up

                new Triangle(pnts[1], pnts[12], pnts[20])
                        .set_emission(new Color(java.awt.Color.WHITE)), // up

                new Triangle(pnts[30], pnts[32], pnts[33])
                        .set_emission(new Color(java.awt.Color.RED)), // up

                new Triangle(pnts[29], pnts[32], pnts[30])
                        .set_emission(new Color(java.awt.Color.RED)), //down

                new Triangle(pnts[29], pnts[31], pnts[32])
                        .set_emission(new Color(java.awt.Color.ORANGE)), //down

                new Triangle(pnts[29], pnts[31], pnts[28])
                        .set_emission(new Color(java.awt.Color.ORANGE)), // left

                new Triangle(pnts[28], pnts[21], pnts[31])
                        .set_emission(new Color(java.awt.Color.GREEN )), // left

                new Triangle(pnts[28], pnts[20], pnts[21])
                        .set_emission(new Color(java.awt.Color.GREEN)), // right

                new Triangle(pnts[32], pnts[33], pnts[35])
                        .set_emission(new Color(java.awt.Color.GREEN)), // right

                new Triangle(pnts[33], pnts[35], pnts[36])
                        .set_emission(new Color(java.awt.Color.GREEN)), // up

                new Triangle(pnts[32], pnts[34], pnts[35])
                        .set_emission(new Color(java.awt.Color.RED)), // up

                new Triangle(pnts[32], pnts[31], pnts[34])
                        .set_emission(new Color(java.awt.Color.RED)), // up

                new Triangle(pnts[16], pnts[31], pnts[34])
                        .set_emission(new Color(java.awt.Color.ORANGE)), //down

                new Triangle(pnts[31], pnts[16], pnts[21])
                        .set_emission(new Color(java.awt.Color.ORANGE)));//down


//                scene._lights.add( //
//                new SpotLight(new Color(700,600,600), new Point3D(0, 0, 300), new Vector(0, -1, 0))//
//                        .setKl(4E-3).setKq(2E-7)
//        );
        scene._lights.add( //
                new SpotLight(new Color(700,600,600), new Point3D(40, 40, 115), new Vector(0,0,-5)) //
                        .setKl(4E-4).setKq(2E-5));

        ImageWriter imageWriter = new ImageWriter("Without SuperSampling level 2", 1000, 1000);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera2) //
                .setRayTracer(new BasicRayTracer(scene));

        //here we do the one ray
        render.renderImage();
        render.writeToImage();
    }
}
