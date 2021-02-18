package com.csrfsec.sample;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @PostMapping("/login")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/get")
    @ResponseBody
    public String get() {
        logger.info("/get called: successful");
        return "Some private info from a GET request";
    }

    @RequestMapping(value = "/post", method = POST)
    @ResponseBody
    public String post() {
        logger.info("/post called: successful");
        return "A private POST was successful";
    }

    @RequestMapping(value = "/upload", method = POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file) {
        logger.info("/upload called: file {} uploaded", file.getOriginalFilename());
        return String.format("Upload of %s was successful", file.getOriginalFilename());
    }

}
