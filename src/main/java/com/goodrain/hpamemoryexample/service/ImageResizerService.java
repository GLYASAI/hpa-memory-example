package com.goodrain.hpamemoryexample.service;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageResizerService {
    public BufferedImage resize(byte[] image) throws IOException {
        InputStream in = new ByteArrayInputStream(image);
        BufferedImage imageBuf = ImageIO.read(in);

        int targetHeight = (int) (imageBuf.getHeight() * 0.2);
        int targetWidth = (int) (imageBuf.getWidth() * 0.2);

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, imageBuf.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(imageBuf, 0, 0, targetWidth, targetHeight, null);
        g.dispose();

        return resizedImage;
    }
}
