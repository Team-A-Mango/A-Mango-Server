package com.mango.amango.domain.image;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class ImageUtil {

    public static String storeImage(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        makeDir();

        String originalFilename = file.getOriginalFilename();
        String storeFilename = createStoreFilename(originalFilename);
        try {
            file.transferTo(new File(getFullPath(storeFilename)));
        } catch (IOException e) {
            throw new CustomException(CustomErrorCode.FILE_PROCESSING_ERROR);
        }

        return storeFilename;
    }

    public static String getFullPath(String fileName) {
        if (fileName.isEmpty()) {
            return null;
        }

        return Paths.get(getFilePath(), fileName).toString();
    }

    private static String getFilePath() {
        return Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "static", "images").toString();
    }

    private static void makeDir() {
        File file = new File(getFilePath());
        if(!file.exists() && !file.mkdirs()) {
            throw new CustomException(CustomErrorCode.FILE_CREATE_ERROR);
        }
    }

    private static String createStoreFilename(String filename) {
        String ext = extractExt(filename);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    private static String extractExt(String filename) {
        int pos = filename.lastIndexOf(".");
        return filename.substring(pos + 1);
    }


}
