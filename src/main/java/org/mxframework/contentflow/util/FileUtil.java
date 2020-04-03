package org.mxframework.contentflow.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * FileUtil: 文件工具类
 *
 * @author mx
 */
public class FileUtil {
    private final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static void main(String[] args) {
        String importPath = "D:\\Users\\rotto\\Documents\\工作";
        importFile(importPath);
    }

    public static String read(File file) {
        String encoding = "UTF-8";
        Long filelength = file.length();
        byte[] filecontent = new byte[filelength.intValue()];
        try {
            FileInputStream in = new FileInputStream(file);
            in.read(filecontent);
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new String(filecontent, encoding);
        } catch (UnsupportedEncodingException e) {
            System.err.println("The OS does not support " + encoding);
            e.printStackTrace();
            return null;
        }
    }

    public static void importFile(String importPath) {
        logger.info("path{}", importPath);
        File file = new File(importPath);
        // 1. 判断指定路径是否是目录
        if (file.isDirectory()) {
            // 2. 获取目录下的文件数组
            File[] fileList = file.listFiles();
            assert fileList != null;
            // 3. 判断文件数组是否为空
            if (fileList.length > 0) {
                for (File tempFile : fileList) {
                    if (tempFile.isDirectory()) {
                        logger.info("目录：" + tempFile.getName());
                        importFile(tempFile.getPath());
                    } else {
                        logger.info("文件：" + tempFile.getName());
                    }
                }
            } else {
                logger.info("该路径下没有文件");
            }
        } else {
            logger.info("不存在此目录：" + importPath);
        }
    }
}
