package test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ImageTest {

    @Test
    void test(){
        try {
            BufferedImage image = ImageIO.read(new File("D:\\code\\liwux-tank\\images\\bulletD.gif"));
            assertNotNull(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //fail("Not yet");
    }

}
