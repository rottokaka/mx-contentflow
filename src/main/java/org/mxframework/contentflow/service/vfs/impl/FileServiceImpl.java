package org.mxframework.contentflow.service.vfs.impl;

import org.mxframework.contentflow.constant.vfs.FileConstant;
import org.mxframework.contentflow.service.ccp.BlogService;
import org.mxframework.contentflow.service.vfs.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

/**
 * FileServiceImpl: 文件接口实现
 *
 * @author mx
 */
@Service
public class FileServiceImpl implements IFileService {
    private final static Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Value("${file.importPath}")
    private String importPath;

    @Autowired
    private BlogService blogService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void importFile() {
        importFile(importPath);
    }

    private void importFile(String path) {
        File file = new File(path);
        // 1. 判断指定路径是否是目录
        if (file.isDirectory()) {
            // 2. 获取目录下的文件数组
            File[] fileList = file.listFiles();
            assert fileList != null;
            // 3. 判断文件数组是否为空
            if (fileList.length > 0) {
                for (File tempFile : fileList) {
                    if (tempFile.isDirectory()) {
                        importFile(tempFile.getPath());
                    } else {
                        // 4. .md Markdown处理
                        if (tempFile.getName().endsWith(FileConstant.FILE_SUFFIX)) {
                            logger.info("[BEN]==================================================" + tempFile.getName());
                            blogService.insertInFile(tempFile);
                            logger.info("[END]==================================================" + tempFile.getName());
                        } else {
                            logger.info("暂不支持除.md格式以外的文件");
                        }
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
