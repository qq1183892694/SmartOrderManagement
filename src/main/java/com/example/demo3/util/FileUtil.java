package com.example.demo3.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileUtil {
    /**
     * @Function: 图片上传
     * @author:
     * @Date:
     */
    public static void uploadFiles(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * @Function: 创建新的文件名
     * @author:
     * @Date:
     */
    public static String renameToUUID(String fileName) {
        return UUID.randomUUID() + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
