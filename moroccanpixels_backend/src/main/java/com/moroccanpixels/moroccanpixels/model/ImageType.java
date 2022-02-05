package com.moroccanpixels.moroccanpixels.model;

public enum ImageType {
    PNG("png"),
    JPEG("jpeg"),
    JPG("jpg"),
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
       for (ImageType imageType : ImageType.values()){
           if(contentType.endsWith(imageType.value()))
               return imageType;
       }
       return null;
    }

    public static ImageType fromValue(String value){
        return valueOf(value.toUpperCase());
    }
}
