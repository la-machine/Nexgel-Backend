package com.example.demo.coursedocument;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class CourseDocumentService {
    public static String savefile(CourseDocuments file, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get("filesUpload");
        if (!Files.exists(uploadPath)){
            Files.createDirectory(uploadPath);
        }
        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + file.getFilename());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + file.getFilename(), ioe);
        }

        return fileCode;

    }
    private Path foundFile;
        public Resource getFileAsResource(String fileCode) throws IOException {
            Path dirPath = Paths.get("filesUpload");
            Files.list(dirPath).forEach(file -> {
                if (file.getFileName().toString().startsWith(fileCode)) {
                    foundFile = file;
                    return;
                }
            });
            if (foundFile != null) {
                return (Resource) new UrlResource(foundFile.toUri());
            }
            return null;
        }
}
