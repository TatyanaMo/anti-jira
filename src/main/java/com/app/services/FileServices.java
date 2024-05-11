package com.app.services;

import com.app.dao.FileDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServices {
    @Autowired
    private FileDAO fileDAO;

    private final Path LOCATION = Paths.get("src/main/resources/static/files");

    public void storeNewFile(MultipartFile file) throws IOException {
        Path destinationFile = LOCATION.resolve(Paths.get(file.getOriginalFilename()))
                        .normalize().toAbsolutePath();

        Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
        fileDAO.storeNewFile(file.getOriginalFilename());
    }
}
