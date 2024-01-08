
package com.mycompany.convert;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PngToBmpConverter {

    public static void main(String[] args) {
        
        BufferedImage pngImage = null;
        try {
            pngImage = ImageIO.read(new File("art1.png"));
        } catch (IOException e) {
            System.out.println("Error reading PNG file: " + e.getMessage());
            return;
        }

        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(pngImage, "png", baos);
        } catch (IOException e) {
            System.out.println("Error converting PNG image to byte array: " + e.getMessage());
            return;
        }
        byte[] pngBytes = baos.toByteArray();

       
        byte[] bmpHeader = new byte[14];
        bmpHeader[0] = 'B';
        bmpHeader[1] = 'M';
        bmpHeader[2] = (byte) (pngBytes.length + bmpHeader.length);
        bmpHeader[3] = 0;
        bmpHeader[4] = 0;
        bmpHeader[5] = 0;
        bmpHeader[6] = 0;
        bmpHeader[7] = 0;
        bmpHeader[8] = 40;
        bmpHeader[9] = 0;
        bmpHeader[10] = 0;
        bmpHeader[11] = 0;
        bmpHeader[12] = (byte) (pngImage.getWidth());
        bmpHeader[13] = (byte) (pngImage.getHeight());

        
        byte[] bmpInfoHeader = new byte[40];
        bmpInfoHeader[0] = 40;
        bmpInfoHeader[1] = 0;
        bmpInfoHeader[2] = 0;
        bmpInfoHeader[3] = 0;
        bmpInfoHeader[4] = (byte) (pngImage.getWidth());
        bmpInfoHeader[5] = (byte) (pngImage.getHeight());
        bmpInfoHeader[6] = 1;
        bmpInfoHeader[7] = 0;
        bmpInfoHeader[8] = 24;
        bmpInfoHeader[9] = 0;
        bmpInfoHeader[10] = 0;
        bmpInfoHeader[11] = 0;
        bmpInfoHeader[12] = 0;
        bmpInfoHeader[13] = 0;
        bmpInfoHeader[14] = 0;
        bmpInfoHeader[15] = 0;

        
        byte[] bmpFile = new byte[bmpHeader.length + bmpInfoHeader.length + pngBytes.length];

       
        System.arraycopy(bmpHeader, 0, bmpFile, 0, bmpHeader.length);
        System.arraycopy(bmpInfoHeader, 0, bmpFile, bmpHeader.length, bmpInfoHeader.length);
        System.arraycopy(pngBytes, 0, bmpFile, bmpHeader.length + bmpInfoHeader.length, pngBytes.length);

        
        try {
            FileOutputStream fos = new FileOutputStream("output.bmp");
            fos.write(bmpFile);
            fos.close();
        } catch (IOException e) {
            System.out.println("Error writing BMP file: " + e.getMessage());
            return;
        }

        System.out.println("PNG image converted to BMP successfully.");
    }
}