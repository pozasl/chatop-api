package com.chatop.api.service;

import static java.util.UUID.randomUUID;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.configuration.FileStorageConfiguration;
import com.chatop.api.exception.FileStorageException;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path baseLocation;
    private final String uploadFolder;

    @Autowired
    public FileStorageServiceImpl(FileStorageConfiguration properties) {
        if (properties.getLocation().trim().length() == 0) {
            throw new FileStorageException("File upload location can not be Empty.");
        }
        this.uploadFolder = properties.getLocation();
        this.baseLocation = Paths.get(properties.getLocation());
    }

    @Override
    public String saveFile(MultipartFile file) throws FileStorageException {
        try {
            if (file.isEmpty()) {
                throw new FileStorageException("Failed to store empty file.");
            }
            String fileName = createFileName(file.getOriginalFilename());
            Path destinationFile = this.baseLocation.resolve(
                    Paths.get(fileName))
                    .normalize().toAbsolutePath();
            checkFileParentFolder(destinationFile);
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
                return uploadFolder + "/" + fileName;
            }
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }


    /**
     * Create a randomly generated filename with the same extension
     * @param originalName
     * @return
     * @throws Exception
     */
    private String createFileName(String originalName) throws FileUploadException{
        List<String> allowedExtensionList = List.of("jpg","png");
        String prefix = "picture";
        int lastDotIndex = originalName.lastIndexOf(".");
        if (lastDotIndex == -1 )
            throw new FileUploadException("File must have an extension");
        String extension = originalName.substring(lastDotIndex+1);
        if (!allowedExtensionList.contains(extension))
            throw new FileUploadException("Picture must be a jpeg or a png file");
        return prefix + "_"+ randomUUID() + "." +  extension;
    }

    @Override
    public void deleteFile(String fileLocation) throws FileStorageException {
        Path filePath = Paths.get(fileLocation);
        checkFileParentFolder(filePath);
        try {
            Files.delete(filePath);
        } catch (Exception e) {
            throw new FileStorageException(e.getMessage());
        }
        
    }

    /**
     * Check that the path is or will be in the upload folder
     * @param filePath
     * @throws FileStorageException
     */
    private void checkFileParentFolder(Path filePath) throws FileStorageException {
        if (!filePath.getParent().equals(this.baseLocation.toAbsolutePath())) {
            throw new FileStorageException(
                    "Cannot delete file outside current directory.");
        }
    }

}
