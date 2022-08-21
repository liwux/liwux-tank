package test;


import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class ImageTest {

    @Test
    void test(){
        try {
            BufferedImage image = ImageIO.read(new File("D:\\code\\liwux-tank\\images\\bulletD.gif"));
            assertNotNull(image);
            BufferedImage image1 = ImageIO.read(Objects.requireNonNull(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif")));
            assertNotNull(image1);
            BufferedImage image2 = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("images/bulletD.gif")));
            assertNotNull(image2);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //fail("Not yet");
    }

}
