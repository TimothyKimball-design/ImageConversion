
package com.mycompany.convert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class JpgToIcoConverter {

    public static void main(String[] args) throws IOException {
       
        File jpgFile = new File("art1.jpg");
        BufferedImage image = ImageIO.read(jpgFile);

        
        BufferedImage icoImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        icoImage.getGraphics().drawImage(image, 0, 0, null);

       
        try {
            FileOutputStream fos = new FileOutputStream("art1.ico");
            writeIcoHeader(fos, icoImage.getWidth(), icoImage.getHeight());
            writeIcoImage(fos, icoImage);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("JPG image converted to ICO successfully.");
    }

    private static void writeIcoHeader(FileOutputStream fos, int width, int height) throws IOException {
       
        fos.write(new byte[] { 0x00, 0x00, 0x01, 0x00 });
        
        fos.write(new byte[] { 0x01, 0x00 });
        
        fos.write(new byte[] { 0x01, 0x00 });
        
        fos.write(new byte[] { (byte) width, 0x00 });
        
        fos.write(new byte[] { (byte) height, 0x00 });
        
        fos.write(new byte[] { 0x00, 0x00 });
        
        fos.write(new byte[] { 0x00, 0x00 });
        
        fos.write(new byte[] { 0x01, 0x00 });
        
        fos.write(new byte[] { 0x20, 0x00 });
       
        fos.write(new byte[] { 0x00, 0x00, 0x00, 0x00 });
        
        fos.write(new byte[] { 0x00, 0x00, 0x00, 0x00 });
    }

    private static void writeIcoImage(FileOutputStream fos, BufferedImage image) throws IOException {
        
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int rgb = image.getRGB(x, y);
                fos.write(new byte[] { (byte) ((rgb >> 16) & 0xFF), (byte) ((rgb >> 8) & 0xFF), (byte) (rgb & 0xFF) });
            }
        }
    }
}