package com.moroccanpixels.moroccanpixels.utils;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public static void saveImage(File srcFile, String directory, String fileName) {
            try {
                if (!new File(directory).exists()) {
                    new File(directory).mkdirs();
                }
                String filePath = directory + fileName;
                File dest = new File(filePath);
                FileUtils.copyFile(srcFile, dest);
            } catch (IOException e) {
                e.printStackTrace();
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
    public static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf+1);
    }

    public static void deleteDirectory(String directory){
        File dirFile = new File(directory);
        try {
            FileUtils.deleteDirectory(dirFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}