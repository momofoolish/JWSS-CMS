package com.jwss.cms.model.menu;

import java.io.Serializable;

/**
 * tb_menu
 * @author jwss
 */
public class TbMenu implements Serializable {
    private Integer id;

    /**
     * 菜单名称
     */
    private String col_name;

    /**
     * 菜单类型,1在左,0在右
     */
    private Integer col_type;

    /**
     * 菜单对应路径
     */
    private String col_url;

    /**
     * 菜单状态，1为已登录，0为未登录
     */
    private Integer col_state;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCol_name() {
        return col_name;
    }

    public void setCol_name(String col_name) {
        this.col_name = col_name;
    }

    public Integer getCol_type() {
        return col_type;
    }

    public void setCol_type(Integer col_type) {
        this.col_type = col_type;
    }

    public String getCol_url() {
        return col_url;
    }

    public void setCol_url(String col_url) {
        this.col_url = col_url;
    }

    public Integer getCol_state() {
        return col_state;
    }

    public void setCol_state(Integer col_state) {
        this.col_state = col_state;
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
        TbMenu other = (TbMenu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCol_name() == null ? other.getCol_name() == null : this.getCol_name().equals(other.getCol_name()))
            && (this.getCol_type() == null ? other.getCol_type() == null : this.getCol_type().equals(other.getCol_type()))
            && (this.getCol_url() == null ? other.getCol_url() == null : this.getCol_url().equals(other.getCol_url()))
            && (this.getCol_state() == null ? other.getCol_state() == null : this.getCol_state().equals(other.getCol_state()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCol_name() == null) ? 0 : getCol_name().hashCode());
        result = prime * result + ((getCol_type() == null) ? 0 : getCol_type().hashCode());
        result = prime * result + ((getCol_url() == null) ? 0 : getCol_url().hashCode());
        result = prime * result + ((getCol_state() == null) ? 0 : getCol_state().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", col_name=").append(col_name);
        sb.append(", col_type=").append(col_type);
        sb.append(", col_url=").append(col_url);
        sb.append(", col_state=").append(col_state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
