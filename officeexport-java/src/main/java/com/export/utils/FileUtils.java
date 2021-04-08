package com.export.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * 文件相关操作工具类
 *
 * @author: zhoucx
 * @time: 2021/3/11 14:14
 */
public class FileUtils {


    /**
     * 将文件内容转为字符串输出.
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String read2StrByFilepath(String filePath)throws IOException {
        StringBuilder data = new StringBuilder(16);
        try (Reader reader = new FileReader(new File(filePath))){
            char [] c = new char [1024];
            int len = 0;
            while ((len = reader.read(c)) != -1){
                data.append(String.copyValueOf(c,0,len));
            }
        }
        return data.toString();
    }

    /**
     * 将文件转为 byte .
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] read2BytesByFilepath(String filePath)throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream(32);
        try (FileInputStream reader = new FileInputStream(filePath)) {
            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = reader.read(buffer)) != -1){
                output.write(buffer, 0, len);
            }
        }
        return output.toByteArray();
    }
}
