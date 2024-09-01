package com.chatop.api.service;

import com.chatop.api.exception.FileStorageException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Simple file storage service interface.
 */
public interface FileStorageService {
  
  public String saveFile(MultipartFile file) throws FileStorageException;

  public void deleteFile(String fileLocation) throws FileStorageException;
}
