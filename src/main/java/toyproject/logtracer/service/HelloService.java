package toyproject.logtracer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import toyproject.logtracer.annotation.Trace;

@Slf4j
@Service
public class HelloService {
    @Trace
    public void doService(String type) {
        if(type.equals("error")) {
            throw new IllegalStateException("error occurs.");
        } else {
            log.info("[do helloService] {}", type);
        }
    }
}
