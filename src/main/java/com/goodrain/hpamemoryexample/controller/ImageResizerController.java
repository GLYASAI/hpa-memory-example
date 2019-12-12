package com.goodrain.hpamemoryexample.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.TEXT_HTML;

@RestController
@RequestMapping("/api")
public class ImageResizerController {

    List<OOMObject> list = new ArrayList<>();

    static class OOMObject {

        byte[] fileContent;

        public OOMObject(byte[] fileContent) {
            this.fileContent = fileContent;
        }
    }

    @GetMapping("/resize")
    public ResponseEntity<?> resize() {
        try {

            File file = new File(getClass().getClassLoader().getResource("teddy-kelley-106391-unsplash.jpg").getFile());
            byte[] fileContent = Files.readAllBytes(file.toPath());
            OOMObject oomObject = new OOMObject(fileContent);
            list.add(oomObject);

            return ResponseEntity.ok()
                    .contentType(TEXT_HTML)
                    .body("ok");
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/read-size")
    public ResponseEntity<?> readzise() {
        return ResponseEntity.ok()
                .contentType(TEXT_HTML)
                .body(list.size() + "");
    }


}
