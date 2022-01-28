package com.moroccanpixels.moroccanpixels.model;

public enum ImageType {
    PNG("png"),
    JPEG("jpeg"),
    GIF("gif"),
    TIFF("tiff");

    private final String value;

    ImageType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static ImageType fromContentType(String contentType) {
        if(contentType.endsWith("png")){
            return PNG;
        }else if(contentType.endsWith("jpeg")){
            return JPEG;
        }else if(contentType.endsWith("gif")){
            return GIF;
        }else if(contentType.endsWith("tiff")){
            return TIFF;
        }
        return null;
    }
}
