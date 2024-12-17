package com.mango.amango.global.file;

import com.mango.amango.global.exception.CustomErrorCode;
import com.mango.amango.global.exception.CustomException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

public abstract class ImageUtil {

    private static final Map<String, String> EXTENTION_TO_MIME_MAP = new HashMap<>() {{
        put("jpg", "image/jpeg");
        put("jpeg", "image/jpeg");
        put("png", "image/png");
    }};

    public static String getFileContentType(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(extension);
        String contentType = EXTENTION_TO_MIME_MAP.get(extension);
        System.out.println(contentType);
        if (contentType == null) {
            throw new CustomException(CustomErrorCode.FILE_PROCESSING_ERROR);
        }

        return contentType;
    }
}
