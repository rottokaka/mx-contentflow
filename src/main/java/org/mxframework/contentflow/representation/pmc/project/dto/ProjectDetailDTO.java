package org.mxframework.contentflow.representation.pmc.project.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.mxframework.contentflow.domain.model.pmc.project.version.Version;
import org.mxframework.contentflow.representation.pmc.section.dto.SectionItemDTO;
import org.mxframework.contentflow.representation.pmc.subject.dto.SubjectItemDTO;
import org.mxframework.contentflow.representation.pmc.version.dto.VersionItemDTO;

import java.util.List;

/**
 * ProjectDetailDTO: 话题详情传输对象
 *
 * @author mx
 * @date 2019-10-10
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProjectDetailDTO extends ProjectBaseDTO {

    /**
     * hidden: 是否隐藏
     */
    private Integer hidden;

    /**
     * archived: 是否归档
     */
    private Integer archived;

    /**
     * pinned: 是否置顶
     */
    private Integer pinned;

    /**
     * aboveId: 上级ID
     */
    private Long aboveId;

    /**
     * notAllowContribute: 是否允许投稿
     */
    private Integer notAllowContribute;

    /**
     * appointedVersion: 指定版本
     */
    private Version appointedVersion;

    /**
     * versionItemDtoList: 版本条目[DTO]集合
     */
    private List<VersionItemDTO> versionItemDtoList;

    /**
     * subjectItemDtoList: 主题条目[DTO]集合
     */
    private List<SubjectItemDTO> subjectItemDtoList;

    /**
     * sectionItemDtoList: 篇章条目[DTO]集合
     */
    private List<SectionItemDTO> sectionItemDtoList;
}
