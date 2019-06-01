package org.epay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PtUserExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public PtUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLogin_nameIsNull() {
            addCriterion("login_name is null");
            return (Criteria) this;
        }

        public Criteria andLogin_nameIsNotNull() {
            addCriterion("login_name is not null");
            return (Criteria) this;
        }

        public Criteria andLogin_nameEqualTo(String value) {
            addCriterion("login_name =", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameNotEqualTo(String value) {
            addCriterion("login_name <>", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameGreaterThan(String value) {
            addCriterion("login_name >", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameGreaterThanOrEqualTo(String value) {
            addCriterion("login_name >=", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameLessThan(String value) {
            addCriterion("login_name <", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameLessThanOrEqualTo(String value) {
            addCriterion("login_name <=", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameLike(String value) {
            addCriterion("login_name like", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameNotLike(String value) {
            addCriterion("login_name not like", value, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameIn(List<String> values) {
            addCriterion("login_name in", values, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameNotIn(List<String> values) {
            addCriterion("login_name not in", values, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameBetween(String value1, String value2) {
            addCriterion("login_name between", value1, value2, "login_name");
            return (Criteria) this;
        }

        public Criteria andLogin_nameNotBetween(String value1, String value2) {
            addCriterion("login_name not between", value1, value2, "login_name");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andOrg_codeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrg_codeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrg_codeEqualTo(String value) {
            addCriterion("org_code =", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeGreaterThan(String value) {
            addCriterion("org_code >", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeLessThan(String value) {
            addCriterion("org_code <", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeLike(String value) {
            addCriterion("org_code like", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotLike(String value) {
            addCriterion("org_code not like", value, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeIn(List<String> values) {
            addCriterion("org_code in", values, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "org_code");
            return (Criteria) this;
        }

        public Criteria andOrg_codeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "org_code");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStop_dateIsNull() {
            addCriterion("stop_date is null");
            return (Criteria) this;
        }

        public Criteria andStop_dateIsNotNull() {
            addCriterion("stop_date is not null");
            return (Criteria) this;
        }

        public Criteria andStop_dateEqualTo(String value) {
            addCriterion("stop_date =", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateNotEqualTo(String value) {
            addCriterion("stop_date <>", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateGreaterThan(String value) {
            addCriterion("stop_date >", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateGreaterThanOrEqualTo(String value) {
            addCriterion("stop_date >=", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateLessThan(String value) {
            addCriterion("stop_date <", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateLessThanOrEqualTo(String value) {
            addCriterion("stop_date <=", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateLike(String value) {
            addCriterion("stop_date like", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateNotLike(String value) {
            addCriterion("stop_date not like", value, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateIn(List<String> values) {
            addCriterion("stop_date in", values, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateNotIn(List<String> values) {
            addCriterion("stop_date not in", values, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateBetween(String value1, String value2) {
            addCriterion("stop_date between", value1, value2, "stop_date");
            return (Criteria) this;
        }

        public Criteria andStop_dateNotBetween(String value1, String value2) {
            addCriterion("stop_date not between", value1, value2, "stop_date");
            return (Criteria) this;
        }

        public Criteria andError_countIsNull() {
            addCriterion("error_count is null");
            return (Criteria) this;
        }

        public Criteria andError_countIsNotNull() {
            addCriterion("error_count is not null");
            return (Criteria) this;
        }

        public Criteria andError_countEqualTo(String value) {
            addCriterion("error_count =", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countNotEqualTo(String value) {
            addCriterion("error_count <>", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countGreaterThan(String value) {
            addCriterion("error_count >", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countGreaterThanOrEqualTo(String value) {
            addCriterion("error_count >=", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countLessThan(String value) {
            addCriterion("error_count <", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countLessThanOrEqualTo(String value) {
            addCriterion("error_count <=", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countLike(String value) {
            addCriterion("error_count like", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countNotLike(String value) {
            addCriterion("error_count not like", value, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countIn(List<String> values) {
            addCriterion("error_count in", values, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countNotIn(List<String> values) {
            addCriterion("error_count not in", values, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countBetween(String value1, String value2) {
            addCriterion("error_count between", value1, value2, "error_count");
            return (Criteria) this;
        }

        public Criteria andError_countNotBetween(String value1, String value2) {
            addCriterion("error_count not between", value1, value2, "error_count");
            return (Criteria) this;
        }

        public Criteria andReset_markIsNull() {
            addCriterion("reset_mark is null");
            return (Criteria) this;
        }

        public Criteria andReset_markIsNotNull() {
            addCriterion("reset_mark is not null");
            return (Criteria) this;
        }

        public Criteria andReset_markEqualTo(String value) {
            addCriterion("reset_mark =", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markNotEqualTo(String value) {
            addCriterion("reset_mark <>", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markGreaterThan(String value) {
            addCriterion("reset_mark >", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markGreaterThanOrEqualTo(String value) {
            addCriterion("reset_mark >=", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markLessThan(String value) {
            addCriterion("reset_mark <", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markLessThanOrEqualTo(String value) {
            addCriterion("reset_mark <=", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markLike(String value) {
            addCriterion("reset_mark like", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markNotLike(String value) {
            addCriterion("reset_mark not like", value, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markIn(List<String> values) {
            addCriterion("reset_mark in", values, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markNotIn(List<String> values) {
            addCriterion("reset_mark not in", values, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markBetween(String value1, String value2) {
            addCriterion("reset_mark between", value1, value2, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andReset_markNotBetween(String value1, String value2) {
            addCriterion("reset_mark not between", value1, value2, "reset_mark");
            return (Criteria) this;
        }

        public Criteria andCreate_byIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreate_byIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_byEqualTo(String value) {
            addCriterion("create_by =", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotEqualTo(String value) {
            addCriterion("create_by <>", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byGreaterThan(String value) {
            addCriterion("create_by >", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLessThan(String value) {
            addCriterion("create_by <", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byLike(String value) {
            addCriterion("create_by like", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotLike(String value) {
            addCriterion("create_by not like", value, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byIn(List<String> values) {
            addCriterion("create_by in", values, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotIn(List<String> values) {
            addCriterion("create_by not in", values, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_byNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "create_by");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreate_timeEqualTo(String value) {
            addCriterion("create_time =", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotEqualTo(String value) {
            addCriterion("create_time <>", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThan(String value) {
            addCriterion("create_time >", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeGreaterThanOrEqualTo(String value) {
            addCriterion("create_time >=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThan(String value) {
            addCriterion("create_time <", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLessThanOrEqualTo(String value) {
            addCriterion("create_time <=", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeLike(String value) {
            addCriterion("create_time like", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotLike(String value) {
            addCriterion("create_time not like", value, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeIn(List<String> values) {
            addCriterion("create_time in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotIn(List<String> values) {
            addCriterion("create_time not in", values, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeBetween(String value1, String value2) {
            addCriterion("create_time between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andCreate_timeNotBetween(String value1, String value2) {
            addCriterion("create_time not between", value1, value2, "create_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_byIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_byIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_byEqualTo(String value) {
            addCriterion("update_by =", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byNotEqualTo(String value) {
            addCriterion("update_by <>", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byGreaterThan(String value) {
            addCriterion("update_by >", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byLessThan(String value) {
            addCriterion("update_by <", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byLike(String value) {
            addCriterion("update_by like", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byNotLike(String value) {
            addCriterion("update_by not like", value, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byIn(List<String> values) {
            addCriterion("update_by in", values, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byNotIn(List<String> values) {
            addCriterion("update_by not in", values, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_byNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "update_by");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeEqualTo(String value) {
            addCriterion("update_time =", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotEqualTo(String value) {
            addCriterion("update_time <>", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThan(String value) {
            addCriterion("update_time >", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeGreaterThanOrEqualTo(String value) {
            addCriterion("update_time >=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThan(String value) {
            addCriterion("update_time <", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLessThanOrEqualTo(String value) {
            addCriterion("update_time <=", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeLike(String value) {
            addCriterion("update_time like", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotLike(String value) {
            addCriterion("update_time not like", value, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeIn(List<String> values) {
            addCriterion("update_time in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotIn(List<String> values) {
            addCriterion("update_time not in", values, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeBetween(String value1, String value2) {
            addCriterion("update_time between", value1, value2, "update_time");
            return (Criteria) this;
        }

        public Criteria andUpdate_timeNotBetween(String value1, String value2) {
            addCriterion("update_time not between", value1, value2, "update_time");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}