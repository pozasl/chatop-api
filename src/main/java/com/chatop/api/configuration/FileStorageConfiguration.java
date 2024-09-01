package com.chatop.api.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * File storage configuration.
 */
@Configuration
@ConfigurationProperties(prefix = "application.storage")
public class FileStorageConfiguration {
  private String location = "upload";

  public String getLocation() {
    return this.location;
  }

  public void setLocation(String location) {
    this.location = location;
  }
}
