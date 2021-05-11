package renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
    /**
     * ImageWriterTest test that will contain an initial image construction
     * - a single-color image with a second-color grid of lines.
     * In this test, a grid of 10x16 squares with a resolution of 500 by 800 was built.
     */
    @Test
    void testWriteToImage() {
        ImageWriter imageWriter = new ImageWriter("testpink",800,500);
        for (int i = 0; i < 800; i++) {
            for (int j = 0; j < 500; j++) {
                // 800/16 = 50 Total width divided by the number of pixels gives the width of each pixel
                if (i % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                }
                // 500/10 = 50 The total length divided by the number of pixels gives the height of each pixel
                else if (j % 50 == 0) {
                    imageWriter.writePixel(i, j, Color.BLACK);
                } else {
                    imageWriter.writePixel(i, j, Color.PINK);
                }
            }
        }
        imageWriter.writeToImage();
    }

}