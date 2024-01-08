
package com.mycompany.convert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class JpegToPngConverter {

    public static void main(String[] args) {
        // The path to the input JPEG image.
        String inputPath = "art1.jpg";

        // The path to the output PNG image.
        String outputPath = "art1.png";

        // Read the JPEG image.
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(inputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the image was read successfully.
        if (image == null) {
            System.out.println("Error reading input image.");
            return;
        }

        // Convert the JPEG image to a PNG image.
        try {
            ImageIO.write(image, "png", new File(outputPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Check if the PNG image was written successfully.
        File outputFile = new File(outputPath);
        if (!outputFile.exists()) {
            System.out.println("Error writing output image.");
            return;
        }

        System.out.println("JPEG image converted to PNG successfully.");
    }
}
