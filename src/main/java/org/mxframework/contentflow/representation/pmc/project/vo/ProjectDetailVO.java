package org.mxframework.contentflow.representation.pmc.project.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.mxframework.contentflow.representation.pmc.project.ProjectBase;
import org.mxframework.contentflow.representation.pmc.section.vo.SectionItemVO;
import org.mxframework.contentflow.representation.pmc.subject.vo.SubjectItemVO;
import org.mxframework.contentflow.representation.pmc.version.vo.VersionItemVO;

import java.util.List;

/**
 * ProjectDetailVO: 项目详情[VO]
 *
 * @author mx
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDetailVO extends ProjectBaseVO {

    private VersionItemVO appointedVersionVo;
    private List<VersionItemVO> versionItemVoList;
    private List<SubjectItemVO> subjectItemVoList;
    private List<SectionItemVO> sectionItemVoList;
    private ProjectItemVO aboveProjectItemVo;
    private List<ProjectItemVO> belowProjectItemVoList;

    public ProjectDetailVO(ProjectBase projectBase) {
        super(projectBase);
    }

    public ProjectDetailVO() {
    }

}
