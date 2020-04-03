package org.mxframework.contentflow.controller.vfs;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.mxframework.contentflow.domain.model.ccp.product.blog.Blog;
import org.mxframework.contentflow.domain.model.ccp.product.blog.BlogId;
import org.mxframework.contentflow.service.ccp.BlogService;
import org.mxframework.contentflow.service.vfs.IFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Objects;

/**
 * FileController: 文件控制器
 *
 * @author mx
 */
@RestController
@RequestMapping("/files")
public class FileController {
    private final static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Value("${file.upload-dir}")
    private String uploadDir;
    @Value("${file.download-dir}")
    private String downloadDir;

    @Autowired
    private IFileService fileService;

    @Autowired
    private BlogService blogService;

    String folder = "D:\\Workspaces\\mx-projects\\mx-security\\mx-security-demo\\import\\";

    @PostMapping
    public File upload(MultipartFile file) throws Exception {
        logger.info("[文件类型] - [{}]", file.getContentType());
        logger.info("[文件名称] - [{}]", file.getOriginalFilename());
        logger.info("[文件大小] - [{}]", file.getSize());

        File localFile = new File(uploadDir, Objects.requireNonNull(file.getOriginalFilename()));
        file.transferTo(localFile);
        blogService.insertInFile(localFile);
        return new File(localFile.getAbsolutePath());
    }

    @GetMapping("/{blogId}")
    public void download(@PathVariable String blogId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Blog blog = blogService.getByBlogId(new BlogId(blogId));
        File file = new File(downloadDir + blog.title() + ".md");
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(blog.content());
        fileWriter.close();
        // try() 1.7功能，不用手动finally关闭流
        try (
                InputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = response.getOutputStream()
        ) {
            response.setContentType("application/x-download");
            response.addHeader("ContentDetail-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }

    @GetMapping("/index")
    public ModelAndView index(Model model) {
        return new ModelAndView("file/index", "fileModel", model);
    }

    /**
     * 获取文件导入页面
     *
     * @return ModelAndView
     */
    @GetMapping("/import")
    public ModelAndView importFile(Model model) {
        try {
            fileService.importFile();
        } catch (Exception e) {
            logger.error("【未知错误】", e);
        }
        return new ModelAndView("file/import", "fileModel", model);
    }
}
