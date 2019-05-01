package com.example.songr;

import com.example.songr.controllers.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SongrApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testHelloController() {
        HelloController controller = new HelloController();

        String actualCapitalization = controller.capitalize("tester teaser");
        assertEquals("Capitalization route return was not expected", "TESTER TEASER", actualCapitalization);

        String actualHelloRoute = controller.sayHello();
        assertEquals("Hello route return was not expected", "Hello World.", actualHelloRoute);
    }

}
