package org.epay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountFileExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public AccountFileExample() {
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

        public Criteria andFile_idIsNull() {
            addCriterion("file_id is null");
            return (Criteria) this;
        }

        public Criteria andFile_idIsNotNull() {
            addCriterion("file_id is not null");
            return (Criteria) this;
        }

        public Criteria andFile_idEqualTo(String value) {
            addCriterion("file_id =", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idNotEqualTo(String value) {
            addCriterion("file_id <>", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idGreaterThan(String value) {
            addCriterion("file_id >", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idGreaterThanOrEqualTo(String value) {
            addCriterion("file_id >=", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idLessThan(String value) {
            addCriterion("file_id <", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idLessThanOrEqualTo(String value) {
            addCriterion("file_id <=", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idLike(String value) {
            addCriterion("file_id like", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idNotLike(String value) {
            addCriterion("file_id not like", value, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idIn(List<String> values) {
            addCriterion("file_id in", values, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idNotIn(List<String> values) {
            addCriterion("file_id not in", values, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idBetween(String value1, String value2) {
            addCriterion("file_id between", value1, value2, "file_id");
            return (Criteria) this;
        }

        public Criteria andFile_idNotBetween(String value1, String value2) {
            addCriterion("file_id not between", value1, value2, "file_id");
            return (Criteria) this;
        }

        public Criteria andMch_idIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMch_idIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMch_idEqualTo(String value) {
            addCriterion("mch_id =", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idNotEqualTo(String value) {
            addCriterion("mch_id <>", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idGreaterThan(String value) {
            addCriterion("mch_id >", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idGreaterThanOrEqualTo(String value) {
            addCriterion("mch_id >=", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idLessThan(String value) {
            addCriterion("mch_id <", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idLessThanOrEqualTo(String value) {
            addCriterion("mch_id <=", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idLike(String value) {
            addCriterion("mch_id like", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idNotLike(String value) {
            addCriterion("mch_id not like", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idIn(List<String> values) {
            addCriterion("mch_id in", values, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idNotIn(List<String> values) {
            addCriterion("mch_id not in", values, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idBetween(String value1, String value2) {
            addCriterion("mch_id between", value1, value2, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idNotBetween(String value1, String value2) {
            addCriterion("mch_id not between", value1, value2, "mch_id");
            return (Criteria) this;
        }

        public Criteria andItems_idIsNull() {
            addCriterion("items_id is null");
            return (Criteria) this;
        }

        public Criteria andItems_idIsNotNull() {
            addCriterion("items_id is not null");
            return (Criteria) this;
        }

        public Criteria andItems_idEqualTo(String value) {
            addCriterion("items_id =", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idNotEqualTo(String value) {
            addCriterion("items_id <>", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idGreaterThan(String value) {
            addCriterion("items_id >", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idGreaterThanOrEqualTo(String value) {
            addCriterion("items_id >=", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idLessThan(String value) {
            addCriterion("items_id <", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idLessThanOrEqualTo(String value) {
            addCriterion("items_id <=", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idLike(String value) {
            addCriterion("items_id like", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idNotLike(String value) {
            addCriterion("items_id not like", value, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idIn(List<String> values) {
            addCriterion("items_id in", values, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idNotIn(List<String> values) {
            addCriterion("items_id not in", values, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idBetween(String value1, String value2) {
            addCriterion("items_id between", value1, value2, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_idNotBetween(String value1, String value2) {
            addCriterion("items_id not between", value1, value2, "items_id");
            return (Criteria) this;
        }

        public Criteria andItems_nameIsNull() {
            addCriterion("items_name is null");
            return (Criteria) this;
        }

        public Criteria andItems_nameIsNotNull() {
            addCriterion("items_name is not null");
            return (Criteria) this;
        }

        public Criteria andItems_nameEqualTo(String value) {
            addCriterion("items_name =", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameNotEqualTo(String value) {
            addCriterion("items_name <>", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameGreaterThan(String value) {
            addCriterion("items_name >", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameGreaterThanOrEqualTo(String value) {
            addCriterion("items_name >=", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameLessThan(String value) {
            addCriterion("items_name <", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameLessThanOrEqualTo(String value) {
            addCriterion("items_name <=", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameLike(String value) {
            addCriterion("items_name like", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameNotLike(String value) {
            addCriterion("items_name not like", value, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameIn(List<String> values) {
            addCriterion("items_name in", values, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameNotIn(List<String> values) {
            addCriterion("items_name not in", values, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameBetween(String value1, String value2) {
            addCriterion("items_name between", value1, value2, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_nameNotBetween(String value1, String value2) {
            addCriterion("items_name not between", value1, value2, "items_name");
            return (Criteria) this;
        }

        public Criteria andItems_typeIsNull() {
            addCriterion("items_type is null");
            return (Criteria) this;
        }

        public Criteria andItems_typeIsNotNull() {
            addCriterion("items_type is not null");
            return (Criteria) this;
        }

        public Criteria andItems_typeEqualTo(String value) {
            addCriterion("items_type =", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeNotEqualTo(String value) {
            addCriterion("items_type <>", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeGreaterThan(String value) {
            addCriterion("items_type >", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeGreaterThanOrEqualTo(String value) {
            addCriterion("items_type >=", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeLessThan(String value) {
            addCriterion("items_type <", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeLessThanOrEqualTo(String value) {
            addCriterion("items_type <=", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeLike(String value) {
            addCriterion("items_type like", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeNotLike(String value) {
            addCriterion("items_type not like", value, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeIn(List<String> values) {
            addCriterion("items_type in", values, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeNotIn(List<String> values) {
            addCriterion("items_type not in", values, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeBetween(String value1, String value2) {
            addCriterion("items_type between", value1, value2, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_typeNotBetween(String value1, String value2) {
            addCriterion("items_type not between", value1, value2, "items_type");
            return (Criteria) this;
        }

        public Criteria andItems_filenameIsNull() {
            addCriterion("items_filename is null");
            return (Criteria) this;
        }

        public Criteria andItems_filenameIsNotNull() {
            addCriterion("items_filename is not null");
            return (Criteria) this;
        }

        public Criteria andItems_filenameEqualTo(String value) {
            addCriterion("items_filename =", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameNotEqualTo(String value) {
            addCriterion("items_filename <>", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameGreaterThan(String value) {
            addCriterion("items_filename >", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameGreaterThanOrEqualTo(String value) {
            addCriterion("items_filename >=", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameLessThan(String value) {
            addCriterion("items_filename <", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameLessThanOrEqualTo(String value) {
            addCriterion("items_filename <=", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameLike(String value) {
            addCriterion("items_filename like", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameNotLike(String value) {
            addCriterion("items_filename not like", value, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameIn(List<String> values) {
            addCriterion("items_filename in", values, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameNotIn(List<String> values) {
            addCriterion("items_filename not in", values, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameBetween(String value1, String value2) {
            addCriterion("items_filename between", value1, value2, "items_filename");
            return (Criteria) this;
        }

        public Criteria andItems_filenameNotBetween(String value1, String value2) {
            addCriterion("items_filename not between", value1, value2, "items_filename");
            return (Criteria) this;
        }

        public Criteria andUpload_dateIsNull() {
            addCriterion("upload_date is null");
            return (Criteria) this;
        }

        public Criteria andUpload_dateIsNotNull() {
            addCriterion("upload_date is not null");
            return (Criteria) this;
        }

        public Criteria andUpload_dateEqualTo(String value) {
            addCriterion("upload_date =", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateNotEqualTo(String value) {
            addCriterion("upload_date <>", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateGreaterThan(String value) {
            addCriterion("upload_date >", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateGreaterThanOrEqualTo(String value) {
            addCriterion("upload_date >=", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateLessThan(String value) {
            addCriterion("upload_date <", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateLessThanOrEqualTo(String value) {
            addCriterion("upload_date <=", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateLike(String value) {
            addCriterion("upload_date like", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateNotLike(String value) {
            addCriterion("upload_date not like", value, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateIn(List<String> values) {
            addCriterion("upload_date in", values, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateNotIn(List<String> values) {
            addCriterion("upload_date not in", values, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateBetween(String value1, String value2) {
            addCriterion("upload_date between", value1, value2, "upload_date");
            return (Criteria) this;
        }

        public Criteria andUpload_dateNotBetween(String value1, String value2) {
            addCriterion("upload_date not between", value1, value2, "upload_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateIsNull() {
            addCriterion("affect_date is null");
            return (Criteria) this;
        }

        public Criteria andAffect_dateIsNotNull() {
            addCriterion("affect_date is not null");
            return (Criteria) this;
        }

        public Criteria andAffect_dateEqualTo(String value) {
            addCriterion("affect_date =", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateNotEqualTo(String value) {
            addCriterion("affect_date <>", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateGreaterThan(String value) {
            addCriterion("affect_date >", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateGreaterThanOrEqualTo(String value) {
            addCriterion("affect_date >=", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateLessThan(String value) {
            addCriterion("affect_date <", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateLessThanOrEqualTo(String value) {
            addCriterion("affect_date <=", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateLike(String value) {
            addCriterion("affect_date like", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateNotLike(String value) {
            addCriterion("affect_date not like", value, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateIn(List<String> values) {
            addCriterion("affect_date in", values, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateNotIn(List<String> values) {
            addCriterion("affect_date not in", values, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateBetween(String value1, String value2) {
            addCriterion("affect_date between", value1, value2, "affect_date");
            return (Criteria) this;
        }

        public Criteria andAffect_dateNotBetween(String value1, String value2) {
            addCriterion("affect_date not between", value1, value2, "affect_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateIsNull() {
            addCriterion("expiry_date is null");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateIsNotNull() {
            addCriterion("expiry_date is not null");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateEqualTo(String value) {
            addCriterion("expiry_date =", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateNotEqualTo(String value) {
            addCriterion("expiry_date <>", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateGreaterThan(String value) {
            addCriterion("expiry_date >", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateGreaterThanOrEqualTo(String value) {
            addCriterion("expiry_date >=", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateLessThan(String value) {
            addCriterion("expiry_date <", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateLessThanOrEqualTo(String value) {
            addCriterion("expiry_date <=", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateLike(String value) {
            addCriterion("expiry_date like", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateNotLike(String value) {
            addCriterion("expiry_date not like", value, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateIn(List<String> values) {
            addCriterion("expiry_date in", values, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateNotIn(List<String> values) {
            addCriterion("expiry_date not in", values, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateBetween(String value1, String value2) {
            addCriterion("expiry_date between", value1, value2, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andExpiry_dateNotBetween(String value1, String value2) {
            addCriterion("expiry_date not between", value1, value2, "expiry_date");
            return (Criteria) this;
        }

        public Criteria andReserve1IsNull() {
            addCriterion("reserve1 is null");
            return (Criteria) this;
        }

        public Criteria andReserve1IsNotNull() {
            addCriterion("reserve1 is not null");
            return (Criteria) this;
        }

        public Criteria andReserve1EqualTo(String value) {
            addCriterion("reserve1 =", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1NotEqualTo(String value) {
            addCriterion("reserve1 <>", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1GreaterThan(String value) {
            addCriterion("reserve1 >", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1GreaterThanOrEqualTo(String value) {
            addCriterion("reserve1 >=", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1LessThan(String value) {
            addCriterion("reserve1 <", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1LessThanOrEqualTo(String value) {
            addCriterion("reserve1 <=", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1Like(String value) {
            addCriterion("reserve1 like", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1NotLike(String value) {
            addCriterion("reserve1 not like", value, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1In(List<String> values) {
            addCriterion("reserve1 in", values, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1NotIn(List<String> values) {
            addCriterion("reserve1 not in", values, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1Between(String value1, String value2) {
            addCriterion("reserve1 between", value1, value2, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve1NotBetween(String value1, String value2) {
            addCriterion("reserve1 not between", value1, value2, "reserve1");
            return (Criteria) this;
        }

        public Criteria andReserve2IsNull() {
            addCriterion("reserve2 is null");
            return (Criteria) this;
        }

        public Criteria andReserve2IsNotNull() {
            addCriterion("reserve2 is not null");
            return (Criteria) this;
        }

        public Criteria andReserve2EqualTo(String value) {
            addCriterion("reserve2 =", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2NotEqualTo(String value) {
            addCriterion("reserve2 <>", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2GreaterThan(String value) {
            addCriterion("reserve2 >", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2GreaterThanOrEqualTo(String value) {
            addCriterion("reserve2 >=", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2LessThan(String value) {
            addCriterion("reserve2 <", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2LessThanOrEqualTo(String value) {
            addCriterion("reserve2 <=", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2Like(String value) {
            addCriterion("reserve2 like", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2NotLike(String value) {
            addCriterion("reserve2 not like", value, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2In(List<String> values) {
            addCriterion("reserve2 in", values, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2NotIn(List<String> values) {
            addCriterion("reserve2 not in", values, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2Between(String value1, String value2) {
            addCriterion("reserve2 between", value1, value2, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve2NotBetween(String value1, String value2) {
            addCriterion("reserve2 not between", value1, value2, "reserve2");
            return (Criteria) this;
        }

        public Criteria andReserve3IsNull() {
            addCriterion("reserve3 is null");
            return (Criteria) this;
        }

        public Criteria andReserve3IsNotNull() {
            addCriterion("reserve3 is not null");
            return (Criteria) this;
        }

        public Criteria andReserve3EqualTo(String value) {
            addCriterion("reserve3 =", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3NotEqualTo(String value) {
            addCriterion("reserve3 <>", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3GreaterThan(String value) {
            addCriterion("reserve3 >", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3GreaterThanOrEqualTo(String value) {
            addCriterion("reserve3 >=", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3LessThan(String value) {
            addCriterion("reserve3 <", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3LessThanOrEqualTo(String value) {
            addCriterion("reserve3 <=", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3Like(String value) {
            addCriterion("reserve3 like", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3NotLike(String value) {
            addCriterion("reserve3 not like", value, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3In(List<String> values) {
            addCriterion("reserve3 in", values, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3NotIn(List<String> values) {
            addCriterion("reserve3 not in", values, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3Between(String value1, String value2) {
            addCriterion("reserve3 between", value1, value2, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve3NotBetween(String value1, String value2) {
            addCriterion("reserve3 not between", value1, value2, "reserve3");
            return (Criteria) this;
        }

        public Criteria andReserve4IsNull() {
            addCriterion("reserve4 is null");
            return (Criteria) this;
        }

        public Criteria andReserve4IsNotNull() {
            addCriterion("reserve4 is not null");
            return (Criteria) this;
        }

        public Criteria andReserve4EqualTo(String value) {
            addCriterion("reserve4 =", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4NotEqualTo(String value) {
            addCriterion("reserve4 <>", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4GreaterThan(String value) {
            addCriterion("reserve4 >", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4GreaterThanOrEqualTo(String value) {
            addCriterion("reserve4 >=", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4LessThan(String value) {
            addCriterion("reserve4 <", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4LessThanOrEqualTo(String value) {
            addCriterion("reserve4 <=", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4Like(String value) {
            addCriterion("reserve4 like", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4NotLike(String value) {
            addCriterion("reserve4 not like", value, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4In(List<String> values) {
            addCriterion("reserve4 in", values, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4NotIn(List<String> values) {
            addCriterion("reserve4 not in", values, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4Between(String value1, String value2) {
            addCriterion("reserve4 between", value1, value2, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve4NotBetween(String value1, String value2) {
            addCriterion("reserve4 not between", value1, value2, "reserve4");
            return (Criteria) this;
        }

        public Criteria andReserve5IsNull() {
            addCriterion("reserve5 is null");
            return (Criteria) this;
        }

        public Criteria andReserve5IsNotNull() {
            addCriterion("reserve5 is not null");
            return (Criteria) this;
        }

        public Criteria andReserve5EqualTo(String value) {
            addCriterion("reserve5 =", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5NotEqualTo(String value) {
            addCriterion("reserve5 <>", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5GreaterThan(String value) {
            addCriterion("reserve5 >", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5GreaterThanOrEqualTo(String value) {
            addCriterion("reserve5 >=", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5LessThan(String value) {
            addCriterion("reserve5 <", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5LessThanOrEqualTo(String value) {
            addCriterion("reserve5 <=", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5Like(String value) {
            addCriterion("reserve5 like", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5NotLike(String value) {
            addCriterion("reserve5 not like", value, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5In(List<String> values) {
            addCriterion("reserve5 in", values, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5NotIn(List<String> values) {
            addCriterion("reserve5 not in", values, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5Between(String value1, String value2) {
            addCriterion("reserve5 between", value1, value2, "reserve5");
            return (Criteria) this;
        }

        public Criteria andReserve5NotBetween(String value1, String value2) {
            addCriterion("reserve5 not between", value1, value2, "reserve5");
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