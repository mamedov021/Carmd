package com.example.carapi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CarController {

    @Autowired
    private CarRecognitionService carRecognitionService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(convFile);


            Map<String, Object> carInfo = carRecognitionService.getCarInfo(convFile.getAbsolutePath());
            return ResponseEntity.ok(carInfo);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
