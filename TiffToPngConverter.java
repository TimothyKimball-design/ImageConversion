

package com.mycompany.convert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TiffToPngConverter {

    public static void main(String[] args) throws IOException {
        
        File tiffFile = new File("art1.tiff");
        BufferedImage image = ImageIO.read(tiffFile);

        
        BufferedImage pngImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        pngImage.getGraphics().drawImage(image, 0, 0, null);

        
        File pngFile = new File("art1.png");
        ImageIO.write(pngImage, "png", pngFile);

        System.out.println("TIFF image converted to PNG successfully.");
    }
}
