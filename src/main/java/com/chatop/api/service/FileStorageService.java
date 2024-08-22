package com.chatop.api.service;

import org.springframework.web.multipart.MultipartFile;

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
    public String saveFile(MultipartFile file) throws Exception;

    public void deleteFile(String fileLocation) throws Exception;;
}
