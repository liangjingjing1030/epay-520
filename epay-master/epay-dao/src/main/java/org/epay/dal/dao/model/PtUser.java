package org.epay.dal.dao.model;

import java.io.Serializable;

public class PtUser implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated
     */
    private Long id;

    /**
     * 登录名
     *
     * @mbg.generated
     */
    private String login_name;

    /**
     * 登录密码
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 机构代码
     *
     * @mbg.generated
     */
    private String org_code;

    /**
     * 用户状态
     *
     * @mbg.generated
     */
    private String type;

    /**
     * 状态：0启用 1禁用
     *
     * @mbg.generated
     */
    private String status;

    /**
     * 锁定时间
     *
     * @mbg.generated
     */
    private String stop_date;

    /**
     * 错误次数
     *
     * @mbg.generated
     */
    private String error_count;

    /**
     * 重置密码标识
     *
     * @mbg.generated
     */
    private String reset_mark;

    /**
     * 建立人
     *
     * @mbg.generated
     */
    private String create_by;

    /**
     * 建立时间
     *
     * @mbg.generated
     */
    private String create_time;

    /**
     * 修改人
     *
     * @mbg.generated
     */
    private String update_by;

    /**
     * 修改时间
     *
     * @mbg.generated
     */
    private String update_time;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name == null ? null : login_name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getOrg_code() {
        return org_code;
    }

    public void setOrg_code(String org_code) {
        this.org_code = org_code == null ? null : org_code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getStop_date() {
        return stop_date;
    }

    public void setStop_date(String stop_date) {
        this.stop_date = stop_date == null ? null : stop_date.trim();
    }

    public String getError_count() {
        return error_count;
    }

    public void setError_count(String error_count) {
        this.error_count = error_count == null ? null : error_count.trim();
    }

    public String getReset_mark() {
        return reset_mark;
    }

    public void setReset_mark(String reset_mark) {
        this.reset_mark = reset_mark == null ? null : reset_mark.trim();
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by == null ? null : create_by.trim();
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time == null ? null : create_time.trim();
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by == null ? null : update_by.trim();
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time == null ? null : update_time.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", login_name=").append(login_name);
        sb.append(", password=").append(password);
        sb.append(", org_code=").append(org_code);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", stop_date=").append(stop_date);
        sb.append(", error_count=").append(error_count);
        sb.append(", reset_mark=").append(reset_mark);
        sb.append(", create_by=").append(create_by);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_by=").append(update_by);
        sb.append(", update_time=").append(update_time);
        sb.append("]");
        return sb.toString();
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
        PtUser other = (PtUser) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLogin_name() == null ? other.getLogin_name() == null : this.getLogin_name().equals(other.getLogin_name()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getOrg_code() == null ? other.getOrg_code() == null : this.getOrg_code().equals(other.getOrg_code()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getStop_date() == null ? other.getStop_date() == null : this.getStop_date().equals(other.getStop_date()))
            && (this.getError_count() == null ? other.getError_count() == null : this.getError_count().equals(other.getError_count()))
            && (this.getReset_mark() == null ? other.getReset_mark() == null : this.getReset_mark().equals(other.getReset_mark()))
            && (this.getCreate_by() == null ? other.getCreate_by() == null : this.getCreate_by().equals(other.getCreate_by()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_by() == null ? other.getUpdate_by() == null : this.getUpdate_by().equals(other.getUpdate_by()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLogin_name() == null) ? 0 : getLogin_name().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getOrg_code() == null) ? 0 : getOrg_code().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getStop_date() == null) ? 0 : getStop_date().hashCode());
        result = prime * result + ((getError_count() == null) ? 0 : getError_count().hashCode());
        result = prime * result + ((getReset_mark() == null) ? 0 : getReset_mark().hashCode());
        result = prime * result + ((getCreate_by() == null) ? 0 : getCreate_by().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_by() == null) ? 0 : getUpdate_by().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        return result;
    }
}