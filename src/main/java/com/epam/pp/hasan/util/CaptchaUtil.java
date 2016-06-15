package com.epam.pp.hasan.util;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 
 * @author Yosin_Hasan
 *
 */
public final class CaptchaUtil {

    public static byte[] generateCaptcha(Integer number) throws IOException {
        byte output[] = null;
        BufferedImage bufferedImage;
        int width = 150;
        int height = 50;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Рисовать будем сюда
        Graphics2D g2d = bufferedImage.createGraphics();
        Font font = new Font("Arial", Font.BOLD, 20);
        g2d.setFont(font);
        g2d.setColor(new Color(255, 255, 255));
        g2d.drawString(number.toString(), 30, 30);
        g2d.dispose();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        ImageOutputStream imout = ImageIO.createImageOutputStream(bout);
        if (ImageIO.write(bufferedImage, "png", imout)) {
            output = bout.toByteArray();
        }
        return output;
    }
}
