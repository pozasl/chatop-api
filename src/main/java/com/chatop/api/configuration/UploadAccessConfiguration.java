package com.chatop.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class UploadAccessConfiguration extends WebMvcConfigurationSupport {

    @Value("${application.storage.location}")
    private String uploadFolderPath;

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Path baseLocation = Paths.get(uploadFolderPath);
        registry
            .addResourceHandler("/" + uploadFolderPath + "/**") 
            .addResourceLocations("file:" + uploadFolderPath + "/");
    }
}