package toyproject.logtracer.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import toyproject.logtracer.annotation.Trace;
import toyproject.logtracer.service.HelloService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloController {
    private final HelloService helloService;
    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/hello")
    @Trace
    public String hello(@RequestParam(name = "type", defaultValue = "helloService") String type) {
        helloService.doService(type);
        return type;
    }
}
