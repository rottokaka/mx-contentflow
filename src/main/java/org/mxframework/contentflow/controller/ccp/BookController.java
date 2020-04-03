package org.mxframework.contentflow.controller.ccp;

import org.mxframework.contentflow.application.pmc.ProjectApplicationService;
import org.mxframework.contentflow.application.pmc.VersionApplicationService;
import org.mxframework.contentflow.constant.pmc.ProjectConstant;
import org.mxframework.contentflow.representation.pmc.project.vo.ProjectItemVO;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionItemVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * BookController: 图书控制器
 *
 * @author mx
 */
@RestController
@RequestMapping("book")
public class BookController {
    private final static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private ProjectApplicationService projectApplicationService;
    @Autowired
    private VersionApplicationService versionApplicationService;


    /**
     * 获取图书视图预览页面
     *
     * @param projectId 项目ID
     * @param versionId 版本ID
     * @param pattern   模式
     * @param model     数据模型
     * @return 模型视图
     */
    @GetMapping("/preview")
    public ModelAndView preview(String projectId, String versionId
            , @RequestParam(value = "pattern", defaultValue = ProjectConstant.PROJECT_PREVIEW_PATTERN_SUBJECT) String pattern
            , Model model) {
        ProjectItemVO projectItemVo = projectApplicationService.getItemVoByProjectId(projectId);
        VersionItemVO versionItemVo = versionApplicationService.getTtemVoByVersionId(versionId);
        List<VersionItemVO> versionItemVoList = versionApplicationService.listItemVoByProjectId(projectId);
        model.addAttribute("projectItemVo", projectItemVo);
        model.addAttribute("versionItemVo", versionItemVo);
        model.addAttribute("versionItemVoList", versionItemVoList);
        model.addAttribute("pattern", pattern);
        return new ModelAndView("book/preview", "bookModel", model);
    }

    /**
     * 获取图书的视图页面
     *
     * @param versionId 版本ID
     * @param pattern   模式
     * @param model     数据模型
     * @return 模型视图
     *//*
    @GetMapping("/view")
    public ModelAndView view(@RequestParam(value = "versionId", defaultValue = "") String versionId
            , @RequestParam(value = "pattern", defaultValue = ProjectConstant.TOPIC_PREVIEW_PATTERN_SUBJECT) String pattern
            , Model model) {
        // 数据字段校验
        if ("".equals(versionId) || "".equals(pattern)) {
            throw new BlogException(BlogExceptionEnum.BLOG_CONSTRAINT_VIOLATION.getCode()
                    , BlogExceptionEnum.BLOG_CONSTRAINT_VIOLATION.getMessage());
        }
        Version version = versionService.selectById(Long.parseLong(versionId));
        // 确认主题和类型集合
        List<Subject> subjectList = themeService.selectAllByVersion(version);
        List<Section> typeList = typeService.selectAllByVersion(version);
        // 处理预览模式
        // 2. 确认模式
        List<Axis> axisList = new ArrayList<>(axisService.selectAllBySectionIdAndSubjectIdAndVersionId(null, null, version.getId()));
        // 2.1. 主题优先
        if (ProjectConstant.TOPIC_PREVIEW_PATTERN_SUBJECT.equals(pattern)) {
            for (Subject subject : subjectList) {
                List<Axis> axisListAtTheme = axisService.selectAllBySectionIdAndSubjectIdAndVersionId(null, subject.getId(), version.getId());
                axisList.addAll(axisListAtTheme);
                if (typeList != null && typeList.size() > 1) {
                    for (Section type : typeList) {
                        List<Axis> axisListAtType = axisService.selectAllBySectionIdAndSubjectIdAndVersionId(type.getId(), subject.getId(), version.getId());
                        axisList.addAll(axisListAtType);
                    }
                }
            }
        }
        // 2.2. 类型优先
        if (ProjectConstant.PROJECT_PREVIEW_PATTERN_SECTION.equals(pattern)) {
            for (Section type : typeList) {
                axisList.addAll(axisService.selectAllBySectionIdAndSubjectIdAndVersionId(type.getId(), null, version.getId()));
                if (subjectList != null && subjectList.size() > 1) {
                    for (Subject subject : subjectList) {
                        List<Axis> axisListAtTheme = axisService.selectAllBySectionIdAndSubjectIdAndVersionId(type.getId(), subject.getId(), version.getId());
                        axisList.addAll(axisListAtTheme);
                    }
                }
            }
        }
        BookVO bookVo = bookService.getByAxisSet(axisList);
        bookVo.setProject(version.getProject());
        model.addAttribute("bookVo", bookVo);
        return new ModelAndView("book/view_self", "bookModel", model);
    }

    *//**
     * 获取图书主页页面
     *
     * @return ModelAndView
     *//*
    @GetMapping
    public ModelAndView index(Model model) {
        // 1. 确定话题和版本
        // 原则：先取多，后取少
        List<Project> projectList = topicService.selectAllByScopeAndProperty(ScopeConstant.SCOPE_PUBLIC, ProjectConstant.PROJECT_PROPERTY_PUBLIC);
        if (projectList == null) {
            throw new BlogException(BlogExceptionEnum.BLOG_CONSTRAINT_VIOLATION.getCode()
                    , BlogExceptionEnum.BLOG_CONSTRAINT_VIOLATION.getMessage());
        }
        // 获取唯一置顶话题
        Project project = topicService.selectByPinned(ProjectConstant.TOPIC_PINNED_YES);
        // 如果没有，则取第一个
        if (project == null) {
            project = projectList.get(0);
        }
        Version version = versionService.selectAllByIsCustomAndProject(VersionConstant.VERSION_CUSTOM_NOT, project).get(0);
        List<Version> versionList = versionService.selectAllByProject(project);
        model.addAttribute("topic", project);
        model.addAttribute("topicList", projectList);
        model.addAttribute("version", version);
        model.addAttribute("versionList", versionList);
        model.addAttribute("pattern", ProjectConstant.TOPIC_PREVIEW_PATTERN_SUBJECT);
        return new ModelAndView("book/index", "bookModel", model);
    }*/
}
