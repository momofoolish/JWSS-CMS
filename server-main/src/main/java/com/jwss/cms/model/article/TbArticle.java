package com.jwss.cms.model.article;

import java.io.Serializable;
import java.util.Date;

/**
 * tb_article
 * @author jwss
 */
public class TbArticle implements Serializable {
    /**
     * ID
     */
    private String id;

    /**
     * 作者id
     */
    private String authorId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容
     */
    private String content;

    /**
     * 封面
     */
    private String cover;

    /**
     * 分类id
     */
    private Integer sortId;

    /**
     * 修改日期
     */
    private Date alterDate;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 点赞数量
     */
    private Integer likesNumber;

    /**
     * 阅读数量
     */
    private Integer readsNumber;

    /**
     * 评论数量
     */
    private Integer commentNumber;

    /**
     * 文章状态，0审核，1通过，2退回，3保存，-1锁定
     */
    private Integer state;

    /**
     * 标签
     */
    private String label;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getSortId() {
        return sortId;
    }

    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    public Date getAlterDate() {
        return alterDate;
    }

    public void setAlterDate(Date alterDate) {
        this.alterDate = alterDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getLikesNumber() {
        return likesNumber;
    }

    public void setLikesNumber(Integer likesNumber) {
        this.likesNumber = likesNumber;
    }

    public Integer getReadsNumber() {
        return readsNumber;
    }

    public void setReadsNumber(Integer readsNumber) {
        this.readsNumber = readsNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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
        TbArticle other = (TbArticle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAuthorId() == null ? other.getAuthorId() == null : this.getAuthorId().equals(other.getAuthorId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getCover() == null ? other.getCover() == null : this.getCover().equals(other.getCover()))
            && (this.getSortId() == null ? other.getSortId() == null : this.getSortId().equals(other.getSortId()))
            && (this.getAlterDate() == null ? other.getAlterDate() == null : this.getAlterDate().equals(other.getAlterDate()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getLikesNumber() == null ? other.getLikesNumber() == null : this.getLikesNumber().equals(other.getLikesNumber()))
            && (this.getReadsNumber() == null ? other.getReadsNumber() == null : this.getReadsNumber().equals(other.getReadsNumber()))
            && (this.getCommentNumber() == null ? other.getCommentNumber() == null : this.getCommentNumber().equals(other.getCommentNumber()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAuthorId() == null) ? 0 : getAuthorId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getCover() == null) ? 0 : getCover().hashCode());
        result = prime * result + ((getSortId() == null) ? 0 : getSortId().hashCode());
        result = prime * result + ((getAlterDate() == null) ? 0 : getAlterDate().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getLikesNumber() == null) ? 0 : getLikesNumber().hashCode());
        result = prime * result + ((getReadsNumber() == null) ? 0 : getReadsNumber().hashCode());
        result = prime * result + ((getCommentNumber() == null) ? 0 : getCommentNumber().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", authorId=").append(authorId);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", content=").append(content);
        sb.append(", cover=").append(cover);
        sb.append(", sortId=").append(sortId);
        sb.append(", alterDate=").append(alterDate);
        sb.append(", createDate=").append(createDate);
        sb.append(", likesNumber=").append(likesNumber);
        sb.append(", readsNumber=").append(readsNumber);
        sb.append(", commentNumber=").append(commentNumber);
        sb.append(", state=").append(state);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
