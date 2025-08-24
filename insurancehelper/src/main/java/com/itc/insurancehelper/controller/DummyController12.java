package com.itc.insurancehelper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController12 {
   // generate a dummy controller
    // for testing purposes
    // with a single endpoint /dummy that returns "Hello, World!"
    // when accessed via GET request
    // this endpoint should be accessible without authentication
    // and should return a 200 OK status code
    // and the response body should be "Hello, World!"
    // use ResponseEntity to return the response
    @GetMapping("/hello12")
    public ResponseEntity<String> dummy() {
        return ResponseEntity.ok("Hello, World!");
    }
}
