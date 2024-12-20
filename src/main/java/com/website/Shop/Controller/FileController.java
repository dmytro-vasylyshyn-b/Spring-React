package com.website.Shop.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {

    @GetMapping("/image-path")
    public String getImagePath() {
        return "Image Path: " + System.getProperty("user.dir") + "/src/main/resources/images";
    }
}
