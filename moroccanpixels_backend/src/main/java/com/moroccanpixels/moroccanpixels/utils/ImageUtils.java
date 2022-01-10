package com.moroccanpixels.moroccanpixels.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ImageUtils {
    //This function saves an image in a directory
    public static void saveImage(MultipartFile image, String directory, String fileName) {
        if (!image.isEmpty()) {
            try {
                if (!new File(directory).exists()) {
                    new File(directory).mkdirs();
                }
                String filePath = directory + fileName;
                File dest = new File(filePath);
                image.transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void replaceImage(MultipartFile image,  String directory, String file1Name, String file2Name){
        if (!image.isEmpty()) {
            if (new File(directory+"/"+file1Name).exists()) {
                if (!new File(directory+"/"+file1Name).delete()) {
                    throw new IllegalStateException("An error occurred while deleting "+directory+"/"+file1Name);
                }
            }
            ImageUtils.saveImage(image,directory,file2Name);
        }
    }
}