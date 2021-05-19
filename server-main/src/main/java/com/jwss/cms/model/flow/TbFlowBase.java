package com.jwss.cms.model.flow;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_flow_base
 * @author jwss
 */
public class TbFlowBase implements Serializable {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 流程标题
     */
    private String title;

    /**
     * 流程内容填写
     */
    private String content;

    /**
     * 审批意见
     */
    private String opinion;

    /**
     * 审核结果，0审核中，1通过，2退回，3归档
     */
    private Integer audit_state;

    /**
     * 创建日期
     */
    private Date create_date;

    /**
     * 当前操作人
     */
    private String current_operator;

    /**
     * 流程ID
     */
    private Integer flow_id;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public Integer getAudit_state() {
        return audit_state;
    }

    public void setAudit_state(Integer audit_state) {
        this.audit_state = audit_state;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getCurrent_operator() {
        return current_operator;
    }

    public void setCurrent_operator(String current_operator) {
        this.current_operator = current_operator;
    }

    public Integer getFlow_id() {
        return flow_id;
    }

    public void setFlow_id(Integer flow_id) {
        this.flow_id = flow_id;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TbFlowBase other = (TbFlowBase) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getOpinion() == null ? other.getOpinion() == null : this.getOpinion().equals(other.getOpinion()))
            && (this.getAudit_state() == null ? other.getAudit_state() == null : this.getAudit_state().equals(other.getAudit_state()))
            && (this.getCreate_date() == null ? other.getCreate_date() == null : this.getCreate_date().equals(other.getCreate_date()))
            && (this.getCurrent_operator() == null ? other.getCurrent_operator() == null : this.getCurrent_operator().equals(other.getCurrent_operator()))
            && (this.getFlow_id() == null ? other.getFlow_id() == null : this.getFlow_id().equals(other.getFlow_id()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getOpinion() == null) ? 0 : getOpinion().hashCode());
        result = prime * result + ((getAudit_state() == null) ? 0 : getAudit_state().hashCode());
        result = prime * result + ((getCreate_date() == null) ? 0 : getCreate_date().hashCode());
        result = prime * result + ((getCurrent_operator() == null) ? 0 : getCurrent_operator().hashCode());
        result = prime * result + ((getFlow_id() == null) ? 0 : getFlow_id().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", opinion=").append(opinion);
        sb.append(", audit_state=").append(audit_state);
        sb.append(", create_date=").append(create_date);
        sb.append(", current_operator=").append(current_operator);
        sb.append(", flow_id=").append(flow_id);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
