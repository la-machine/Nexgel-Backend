package com.example.nexgel.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.nexgel.model.User;
import com.example.nexgel.model.User_file;

public class UserFileService {
    
    public static void savefile(User user) throws IOException {
       MultipartFile multipartFile = user.getFile();
       String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        User_file file = new User_file();
        file.setFileName(fileName);
        Path uploadPath = Paths.get("UserFile");
        if (!Files.exists(uploadPath)){
            Files.createDirectory(uploadPath);
        }
        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + file.getFileName());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + file.getFileName(), ioe);
        }

    }
    private Path foundFile;
        public Resource getFileAsResource(String fileCode) throws IOException {
            Path dirPath = Paths.get("UserFile");
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
