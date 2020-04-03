package org.mxframework.contentflow.service.vfs;

/**
 * IFileService: 文件接口
 *
 * @author m
 * @date 2019-03-13
 */
public interface IFileService {

    /**
     * 文件导入
     * 内容：{markdown}
     * 方式：定时任务，手动刷新
     */
    void importFile();
}
