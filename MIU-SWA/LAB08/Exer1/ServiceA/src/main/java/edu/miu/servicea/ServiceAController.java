package edu.miu.servicea;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

    @Value("${greeting}")
    private String message;

    @GetMapping("/")
    public String greeting() {
        return message;
    }

}
