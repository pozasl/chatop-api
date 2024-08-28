package com.chatop.api.service;

import org.springframework.web.multipart.MultipartFile;

import com.chatop.api.exception.FileStorageException;

/**
 * Simple file storage service
 */
public interface FileStorageService {
    /**
     * Save a file from a form
     * @param file the file data
     * @return the file url
     * @throws Exception
     */
    public String saveFile(MultipartFile file) throws FileStorageException;

    public void deleteFile(String fileLocation) throws FileStorageException;
}
