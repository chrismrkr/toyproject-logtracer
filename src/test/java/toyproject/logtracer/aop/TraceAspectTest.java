package toyproject.logtracer.aop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyproject.logtracer.controller.HelloController;
import toyproject.logtracer.service.HelloService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TraceAspectTest {
    @Autowired
    HelloController helloController;
    @Autowired
    HelloService helloService;

    @Test
    void testMain() {
        helloController.main();
    }
    @Test
    void testControllerNormal() {
        helloController.hello("normal");
    }
    @Test
    void testServiceNormal() {
        helloService.doService("normal");
    }
    @Test
    void testException() {
        Assertions.assertThrows(IllegalStateException.class, () -> helloController.hello("error"));
    }
}