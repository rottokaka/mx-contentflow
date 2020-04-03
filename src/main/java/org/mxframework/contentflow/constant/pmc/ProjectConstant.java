package org.mxframework.contentflow.constant.pmc;

/**
 * ProjectConstant: 项目常量
 *
 * @author mx
 */
public class ProjectConstant {

    /**
     * project.property: 0，公共项目
     */
    public static final Integer PROJECT_PROPERTY_PUBLIC = 0;

    /**
     * project.property: 1，个人项目
     */
    public static final Integer PROJECT_PROPERTY_PRIVATE = 1;

    /**
     * project.pinned: 0-不置顶【默认】
     */
    public static final Integer PROJECT_PINNED_NOT = 0;

    /**
     * project.pinned: 1-已置顶
     */
    public static final Integer PROJECT_PINNED_YES = 1;

    /**
     * project.pinned: 2-已置顶，第二等级
     */
    public static final Integer TOPIC_PINNED_YES_LEVEL_2 = 2;

    /**
     * project.pinned: 3-已置顶，第三级
     */
    public static final Integer TOPIC_PINNED_YES_LEVEL_3 = 3;

    /**
     * project.pinned: 4-已置顶，第四等级
     */
    public static final Integer TOPIC_PINNED_YES_LEVEL_4 = 4;

    /**
     * project.pinned: 5-已置顶，第四等级
     */
    public static final Integer TOPIC_PINNED_YES_LEVEL_5 = 5;

    /**
     * project.archived: 0-未归档【默认】
     */
    public static final Integer PROJECT_ARCHIVED_NOT = 0;

    /**
     * project.archived: 1-已归档
     */
    public static final Integer PROJECT_ARCHIVED_YES = 1;

    /**
     * project.aboveId: pmc-project-00000000【默认】
     */
    public static final String PROJECT_DEFAULT_ABOVE_ID = "pmc-project-00000000";

    /**
     * 项目异常信息-分类已经存在
     */
    public static final String TOPIC_NAME_IS_EXISTED = "项目已经存在";

    /**
     * 项目名称字符串分隔符
     */
    public static final String TOPIC_SEPARATOR = ",";

    /**
     * 项目预览模式：主题优先
     */
    public static final String PROJECT_PREVIEW_PATTERN_SUBJECT = "PATTERN_SUBJECT";

    /**
     * 项目预览模式：篇章优先
     */
    public static final String PROJECT_PREVIEW_PATTERN_SECTION = "PATTERN_SECTION";

    /**
     * 项目收录模式，INSERT-添加收录
     */
    public static final String PROJECT_COLLECT_PATTERN_INSERT = "PATTERN_INSERT";

    /**
     * 项目收录模式，INSERT-取消收录
     */
    public static final String PROJECT_COLLECT_PATTERN_REMOVE = "PATTERN_REMOVE";

    /**
     * 0-未收录【默认】
     */
    public static final Integer PROJECT_COLLECTED_NOT = 0;

    /**
     * 1-已收录
     */
    public static final Integer PROJECT_COLLECTED_YES = 1;

    /**
     * project.contributionNotAllowed: 0-项目已允许投稿【默认】
     */
    public static final Integer PROJECT_CONTRIBUTION_NOT_ALLOWED_DEFAULT_FALSE = 0;

    /**
     * project.contributionNotAllowed: 1-项目不允许投稿
     */
    public static final Integer PROJECT_CONTRIBUTION_NOT_ALLOWED_TRUE = 1;

}
