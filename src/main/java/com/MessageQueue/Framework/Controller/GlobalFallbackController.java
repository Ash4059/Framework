package com.MessageQueue.Framework.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GlobalFallbackController {

    @GetMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        return ResponseEntity.ok().body("An error occurred. Please contact support.");
    }


}
