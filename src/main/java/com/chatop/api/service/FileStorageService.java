package com.chatop.api.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Simple file storage service
 */
public interface FileStorageService {
    /**
     * Save a file from a form
     * @param file the file data
     * @param fileName the file name to save as
     * @return the file url
     * @throws Exception
     */
    public String saveFileAs(MultipartFile file, String prefix) throws Exception;

    public String saveFileAs(MultipartFile file) throws Exception;
}
