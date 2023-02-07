package com.example.nexgel.Controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.nexgel.Service.CourseDocumentService;
import com.example.nexgel.model.CourseDocuments;


import java.io.IOException;

@RestController
public class CourseDocumentController {
    @PostMapping("/uploadFile")
        public ResponseEntity<CourseDocuments> uploadFile(@RequestParam("file") MultipartFile multipartFile)
        throws IOException {

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            long size = multipartFile.getSize();
            CourseDocuments response = new CourseDocuments();
            response.setFilename(fileName);
            String filecode = CourseDocumentService.savefile(response, multipartFile);

            response.setDownloadurl("/downloadFile/" + filecode);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @GetMapping("/downloadFile/{fileCode}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
        CourseDocumentService downloadUtil = new CourseDocumentService();
        Resource resrce = null;
        try {
            resrce = downloadUtil.getFileAsResource(fileCode);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
        if (resrce == null) {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + resrce.getFilename() + "\"";
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
            .body(resrce);
    }

}
