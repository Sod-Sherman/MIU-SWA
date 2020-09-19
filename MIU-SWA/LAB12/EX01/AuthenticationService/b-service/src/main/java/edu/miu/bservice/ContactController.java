package edu.miu.bservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {
    @GetMapping("/phone")
    public String getPhone() {
        return "B-Service (Contact service only accessible employees): 645322899";
    }

}
