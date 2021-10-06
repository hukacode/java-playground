package dev.huka.playground.spring_boot_oauth2.resource_server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class JobController {
    @GetMapping("/jobs")
    public String get() {
        return "From GetMapping jobs";
    }

    @PostMapping("/jobs")
    public String post() {
        return "From PostMapping jobs";
    }

    @PutMapping("/jobs")
    public String put() {
        return "From PutMapping jobs";
    }

    @DeleteMapping("/jobs")
    public String delete() {
        return "From DeleteMapping jobs";
    }
}