package org.mxframework.contentflow.domain.model.sis.tag;

import org.mxframework.contentflow.domain.model.IdentifiedValueObject;
import org.mxframework.contentflow.domain.model.sis.identity.Operator;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * TagSynonym: 标签同义词
 *
 * @author mx
 */
@Entity
public class TagSynonym extends IdentifiedValueObject {
    private static final long serialVersionUID = 1L;

    @Embedded
    private Operator operator;

    @NotNull(message = "目标标签不能为空")
    @Column(name = "target_id")
    private String target;

    @NotNull(message = "标签同义词不能为空")
    @Column(name = "synonym_id")
    private String synonym;

    public TagSynonym(String taget, String synonym, Operator operator) {
        this();
        this.setTarget(taget);
        this.setSynonym(synonym);
        this.setOperator(operator);
    }

    protected TagSynonym() {
        super();
    }

    public Operator operator() {
        return this.operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public String target() {
        return this.target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String synonym() {
        return this.synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    @Override
    public String toString() {
        return "TagSynonym{" +
                "operator=" + operator +
                ", target='" + target + '\'' +
                ", synonym='" + synonym + '\'' +
                '}';
    }
}
