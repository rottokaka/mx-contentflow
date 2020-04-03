package org.mxframework.contentflow.domain.model.pmc.project.subject;

import org.mxframework.contentflow.domain.model.AbstractValueObject;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author mx
 */
@Embeddable
public class Contribution extends AbstractValueObject {
    private static final long serialVersionUID = 1L;

    /**
     * 投稿允许状态
     */
    private static final Integer STATUS_ABLE_DEFAULT = 0;
    /**
     * 投稿禁止状态
     */
    private static final Integer STATUS_DISABLED = 0;
    private static final Integer VERIFY_REQUIRED_NOT_DEFAULT = 0;
    private static final Integer VERIFY_REQUIRED_YES = 0;

    @Column
    private Integer status;
    @Column
    private Integer verifyRequired;

    public Contribution(Integer status, Integer verifyRequired) {
        this();
        this.setStatus(status);
        this.setVerifyRequired(verifyRequired);
    }

    protected Contribution() {
        super();
    }

    public Integer status() {
        return this.status;
    }

    public Integer verifyRequired() {
        return this.verifyRequired;
    }

    public void setStatus(Integer status) {
        if (status != null) {
            this.status = status;
        } else {
            this.status = STATUS_ABLE_DEFAULT;
        }
    }

    public void setVerifyRequired(Integer verifyRequired) {
        if (verifyRequired != null) {
            if (STATUS_DISABLED.equals(this.status())) {
                this.verifyRequired = VERIFY_REQUIRED_YES;
            } else {
                this.verifyRequired = verifyRequired;
            }
        } else {
            this.verifyRequired = VERIFY_REQUIRED_NOT_DEFAULT;
        }
    }
}
