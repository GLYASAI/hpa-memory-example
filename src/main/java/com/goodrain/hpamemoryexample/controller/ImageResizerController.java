package com.goodrain.hpamemoryexample.controller;

import com.goodrain.hpamemoryexample.service.ImageResizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.springframework.http.MediaType.TEXT_HTML;

@RestController
@RequestMapping("/api")
public class ImageResizerController {

    private final ImageResizerService imageResizerService;

    public ImageResizerController(ImageResizerService imageResizerService) {
        this.imageResizerService = imageResizerService;
    }

    @PostMapping("/resize")
    public ResponseEntity<?> resize(
            @RequestParam("data") MultipartFile file) {

        try {
            BufferedImage resizedImage = this.imageResizerService.resize(file.getBytes());

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(resizedImage, "jpg", byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();

            Thread.sleep(1 * 500);
            return ResponseEntity.ok()
                    .contentType(TEXT_HTML)
                    .body("ok");
        } catch (IOException | InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
