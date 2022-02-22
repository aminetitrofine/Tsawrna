package com.moroccanpixels.moroccanpixels.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@ConfigurationProperties(prefix ="image")
public class ImageConfig {
    private String directory;
    private String initDirectory;
}
