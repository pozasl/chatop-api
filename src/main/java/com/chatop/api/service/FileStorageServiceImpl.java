package com.chatop.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.configuration.FileStorageConfiguration;
import com.chatop.api.exception.FileStorageException;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path baseLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageConfiguration properties) {
        if (properties.getLocation().trim().length() == 0) {
            throw new FileStorageException("File upload location can not be Empty.");
        }

        this.baseLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String saveFileAs(MultipartFile file, String prefix) throws Exception {
        try {
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file.");
            }
            String fileName = prefix + file.getOriginalFilename();
            Path destinationFile = this.baseLocation.resolve(
                    Paths.get(fileName))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.baseLocation.toAbsolutePath())) {
                // This is a security check
                throw new FileStorageException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                return fileName;
            }
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public String saveFileAs(MultipartFile file) throws Exception {
        return this.saveFileAs(file, "");
    }

}
