package fr.carneyandco.sample.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyApplication.class)
public class SpringContextIntegrationTest {

    @Test
    public void whenSpringContextIsBootstrappedThenNoExceptions() {
        // The app can start.
        // Do not add code here.
    }
}