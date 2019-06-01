package org.epay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MchInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public MchInfoExample() {
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

        public Criteria andMch_nameIsNull() {
            addCriterion("mch_name is null");
            return (Criteria) this;
        }

        public Criteria andMch_nameIsNotNull() {
            addCriterion("mch_name is not null");
            return (Criteria) this;
        }

        public Criteria andMch_nameEqualTo(String value) {
            addCriterion("mch_name =", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameNotEqualTo(String value) {
            addCriterion("mch_name <>", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameGreaterThan(String value) {
            addCriterion("mch_name >", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameGreaterThanOrEqualTo(String value) {
            addCriterion("mch_name >=", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameLessThan(String value) {
            addCriterion("mch_name <", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameLessThanOrEqualTo(String value) {
            addCriterion("mch_name <=", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameLike(String value) {
            addCriterion("mch_name like", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameNotLike(String value) {
            addCriterion("mch_name not like", value, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameIn(List<String> values) {
            addCriterion("mch_name in", values, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameNotIn(List<String> values) {
            addCriterion("mch_name not in", values, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameBetween(String value1, String value2) {
            addCriterion("mch_name between", value1, value2, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_nameNotBetween(String value1, String value2) {
            addCriterion("mch_name not between", value1, value2, "mch_name");
            return (Criteria) this;
        }

        public Criteria andMch_typeIsNull() {
            addCriterion("mch_type is null");
            return (Criteria) this;
        }

        public Criteria andMch_typeIsNotNull() {
            addCriterion("mch_type is not null");
            return (Criteria) this;
        }

        public Criteria andMch_typeEqualTo(String value) {
            addCriterion("mch_type =", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeNotEqualTo(String value) {
            addCriterion("mch_type <>", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeGreaterThan(String value) {
            addCriterion("mch_type >", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeGreaterThanOrEqualTo(String value) {
            addCriterion("mch_type >=", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeLessThan(String value) {
            addCriterion("mch_type <", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeLessThanOrEqualTo(String value) {
            addCriterion("mch_type <=", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeLike(String value) {
            addCriterion("mch_type like", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeNotLike(String value) {
            addCriterion("mch_type not like", value, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeIn(List<String> values) {
            addCriterion("mch_type in", values, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeNotIn(List<String> values) {
            addCriterion("mch_type not in", values, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeBetween(String value1, String value2) {
            addCriterion("mch_type between", value1, value2, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_typeNotBetween(String value1, String value2) {
            addCriterion("mch_type not between", value1, value2, "mch_type");
            return (Criteria) this;
        }

        public Criteria andMch_rangeIsNull() {
            addCriterion("mch_range is null");
            return (Criteria) this;
        }

        public Criteria andMch_rangeIsNotNull() {
            addCriterion("mch_range is not null");
            return (Criteria) this;
        }

        public Criteria andMch_rangeEqualTo(String value) {
            addCriterion("mch_range =", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeNotEqualTo(String value) {
            addCriterion("mch_range <>", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeGreaterThan(String value) {
            addCriterion("mch_range >", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeGreaterThanOrEqualTo(String value) {
            addCriterion("mch_range >=", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeLessThan(String value) {
            addCriterion("mch_range <", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeLessThanOrEqualTo(String value) {
            addCriterion("mch_range <=", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeLike(String value) {
            addCriterion("mch_range like", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeNotLike(String value) {
            addCriterion("mch_range not like", value, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeIn(List<String> values) {
            addCriterion("mch_range in", values, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeNotIn(List<String> values) {
            addCriterion("mch_range not in", values, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeBetween(String value1, String value2) {
            addCriterion("mch_range between", value1, value2, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_rangeNotBetween(String value1, String value2) {
            addCriterion("mch_range not between", value1, value2, "mch_range");
            return (Criteria) this;
        }

        public Criteria andMch_cityIsNull() {
            addCriterion("mch_city is null");
            return (Criteria) this;
        }

        public Criteria andMch_cityIsNotNull() {
            addCriterion("mch_city is not null");
            return (Criteria) this;
        }

        public Criteria andMch_cityEqualTo(String value) {
            addCriterion("mch_city =", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityNotEqualTo(String value) {
            addCriterion("mch_city <>", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityGreaterThan(String value) {
            addCriterion("mch_city >", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityGreaterThanOrEqualTo(String value) {
            addCriterion("mch_city >=", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityLessThan(String value) {
            addCriterion("mch_city <", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityLessThanOrEqualTo(String value) {
            addCriterion("mch_city <=", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityLike(String value) {
            addCriterion("mch_city like", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityNotLike(String value) {
            addCriterion("mch_city not like", value, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityIn(List<String> values) {
            addCriterion("mch_city in", values, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityNotIn(List<String> values) {
            addCriterion("mch_city not in", values, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityBetween(String value1, String value2) {
            addCriterion("mch_city between", value1, value2, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_cityNotBetween(String value1, String value2) {
            addCriterion("mch_city not between", value1, value2, "mch_city");
            return (Criteria) this;
        }

        public Criteria andMch_addressIsNull() {
            addCriterion("mch_address is null");
            return (Criteria) this;
        }

        public Criteria andMch_addressIsNotNull() {
            addCriterion("mch_address is not null");
            return (Criteria) this;
        }

        public Criteria andMch_addressEqualTo(String value) {
            addCriterion("mch_address =", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressNotEqualTo(String value) {
            addCriterion("mch_address <>", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressGreaterThan(String value) {
            addCriterion("mch_address >", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressGreaterThanOrEqualTo(String value) {
            addCriterion("mch_address >=", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressLessThan(String value) {
            addCriterion("mch_address <", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressLessThanOrEqualTo(String value) {
            addCriterion("mch_address <=", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressLike(String value) {
            addCriterion("mch_address like", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressNotLike(String value) {
            addCriterion("mch_address not like", value, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressIn(List<String> values) {
            addCriterion("mch_address in", values, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressNotIn(List<String> values) {
            addCriterion("mch_address not in", values, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressBetween(String value1, String value2) {
            addCriterion("mch_address between", value1, value2, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_addressNotBetween(String value1, String value2) {
            addCriterion("mch_address not between", value1, value2, "mch_address");
            return (Criteria) this;
        }

        public Criteria andMch_statusIsNull() {
            addCriterion("mch_status is null");
            return (Criteria) this;
        }

        public Criteria andMch_statusIsNotNull() {
            addCriterion("mch_status is not null");
            return (Criteria) this;
        }

        public Criteria andMch_statusEqualTo(Byte value) {
            addCriterion("mch_status =", value, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusNotEqualTo(Byte value) {
            addCriterion("mch_status <>", value, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusGreaterThan(Byte value) {
            addCriterion("mch_status >", value, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("mch_status >=", value, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusLessThan(Byte value) {
            addCriterion("mch_status <", value, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusLessThanOrEqualTo(Byte value) {
            addCriterion("mch_status <=", value, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusIn(List<Byte> values) {
            addCriterion("mch_status in", values, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusNotIn(List<Byte> values) {
            addCriterion("mch_status not in", values, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusBetween(Byte value1, Byte value2) {
            addCriterion("mch_status between", value1, value2, "mch_status");
            return (Criteria) this;
        }

        public Criteria andMch_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("mch_status not between", value1, value2, "mch_status");
            return (Criteria) this;
        }

        public Criteria andContact_personIsNull() {
            addCriterion("contact_person is null");
            return (Criteria) this;
        }

        public Criteria andContact_personIsNotNull() {
            addCriterion("contact_person is not null");
            return (Criteria) this;
        }

        public Criteria andContact_personEqualTo(String value) {
            addCriterion("contact_person =", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personNotEqualTo(String value) {
            addCriterion("contact_person <>", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personGreaterThan(String value) {
            addCriterion("contact_person >", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personGreaterThanOrEqualTo(String value) {
            addCriterion("contact_person >=", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personLessThan(String value) {
            addCriterion("contact_person <", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personLessThanOrEqualTo(String value) {
            addCriterion("contact_person <=", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personLike(String value) {
            addCriterion("contact_person like", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personNotLike(String value) {
            addCriterion("contact_person not like", value, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personIn(List<String> values) {
            addCriterion("contact_person in", values, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personNotIn(List<String> values) {
            addCriterion("contact_person not in", values, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personBetween(String value1, String value2) {
            addCriterion("contact_person between", value1, value2, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_personNotBetween(String value1, String value2) {
            addCriterion("contact_person not between", value1, value2, "contact_person");
            return (Criteria) this;
        }

        public Criteria andContact_phoneIsNull() {
            addCriterion("contact_phone is null");
            return (Criteria) this;
        }

        public Criteria andContact_phoneIsNotNull() {
            addCriterion("contact_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContact_phoneEqualTo(String value) {
            addCriterion("contact_phone =", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneNotEqualTo(String value) {
            addCriterion("contact_phone <>", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneGreaterThan(String value) {
            addCriterion("contact_phone >", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneGreaterThanOrEqualTo(String value) {
            addCriterion("contact_phone >=", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneLessThan(String value) {
            addCriterion("contact_phone <", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneLessThanOrEqualTo(String value) {
            addCriterion("contact_phone <=", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneLike(String value) {
            addCriterion("contact_phone like", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneNotLike(String value) {
            addCriterion("contact_phone not like", value, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneIn(List<String> values) {
            addCriterion("contact_phone in", values, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneNotIn(List<String> values) {
            addCriterion("contact_phone not in", values, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneBetween(String value1, String value2) {
            addCriterion("contact_phone between", value1, value2, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_phoneNotBetween(String value1, String value2) {
            addCriterion("contact_phone not between", value1, value2, "contact_phone");
            return (Criteria) this;
        }

        public Criteria andContact_emailIsNull() {
            addCriterion("contact_email is null");
            return (Criteria) this;
        }

        public Criteria andContact_emailIsNotNull() {
            addCriterion("contact_email is not null");
            return (Criteria) this;
        }

        public Criteria andContact_emailEqualTo(String value) {
            addCriterion("contact_email =", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailNotEqualTo(String value) {
            addCriterion("contact_email <>", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailGreaterThan(String value) {
            addCriterion("contact_email >", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailGreaterThanOrEqualTo(String value) {
            addCriterion("contact_email >=", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailLessThan(String value) {
            addCriterion("contact_email <", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailLessThanOrEqualTo(String value) {
            addCriterion("contact_email <=", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailLike(String value) {
            addCriterion("contact_email like", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailNotLike(String value) {
            addCriterion("contact_email not like", value, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailIn(List<String> values) {
            addCriterion("contact_email in", values, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailNotIn(List<String> values) {
            addCriterion("contact_email not in", values, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailBetween(String value1, String value2) {
            addCriterion("contact_email between", value1, value2, "contact_email");
            return (Criteria) this;
        }

        public Criteria andContact_emailNotBetween(String value1, String value2) {
            addCriterion("contact_email not between", value1, value2, "contact_email");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeIsNull() {
            addCriterion("certificate_type is null");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeIsNotNull() {
            addCriterion("certificate_type is not null");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeEqualTo(String value) {
            addCriterion("certificate_type =", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeNotEqualTo(String value) {
            addCriterion("certificate_type <>", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeGreaterThan(String value) {
            addCriterion("certificate_type >", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeGreaterThanOrEqualTo(String value) {
            addCriterion("certificate_type >=", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeLessThan(String value) {
            addCriterion("certificate_type <", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeLessThanOrEqualTo(String value) {
            addCriterion("certificate_type <=", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeLike(String value) {
            addCriterion("certificate_type like", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeNotLike(String value) {
            addCriterion("certificate_type not like", value, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeIn(List<String> values) {
            addCriterion("certificate_type in", values, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeNotIn(List<String> values) {
            addCriterion("certificate_type not in", values, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeBetween(String value1, String value2) {
            addCriterion("certificate_type between", value1, value2, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_typeNotBetween(String value1, String value2) {
            addCriterion("certificate_type not between", value1, value2, "certificate_type");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberIsNull() {
            addCriterion("certificate_number is null");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberIsNotNull() {
            addCriterion("certificate_number is not null");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberEqualTo(String value) {
            addCriterion("certificate_number =", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberNotEqualTo(String value) {
            addCriterion("certificate_number <>", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberGreaterThan(String value) {
            addCriterion("certificate_number >", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberGreaterThanOrEqualTo(String value) {
            addCriterion("certificate_number >=", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberLessThan(String value) {
            addCriterion("certificate_number <", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberLessThanOrEqualTo(String value) {
            addCriterion("certificate_number <=", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberLike(String value) {
            addCriterion("certificate_number like", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberNotLike(String value) {
            addCriterion("certificate_number not like", value, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberIn(List<String> values) {
            addCriterion("certificate_number in", values, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberNotIn(List<String> values) {
            addCriterion("certificate_number not in", values, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberBetween(String value1, String value2) {
            addCriterion("certificate_number between", value1, value2, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andCertificate_numberNotBetween(String value1, String value2) {
            addCriterion("certificate_number not between", value1, value2, "certificate_number");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseIsNull() {
            addCriterion("business_license is null");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseIsNotNull() {
            addCriterion("business_license is not null");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseEqualTo(String value) {
            addCriterion("business_license =", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseNotEqualTo(String value) {
            addCriterion("business_license <>", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseGreaterThan(String value) {
            addCriterion("business_license >", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseGreaterThanOrEqualTo(String value) {
            addCriterion("business_license >=", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseLessThan(String value) {
            addCriterion("business_license <", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseLessThanOrEqualTo(String value) {
            addCriterion("business_license <=", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseLike(String value) {
            addCriterion("business_license like", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseNotLike(String value) {
            addCriterion("business_license not like", value, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseIn(List<String> values) {
            addCriterion("business_license in", values, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseNotIn(List<String> values) {
            addCriterion("business_license not in", values, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseBetween(String value1, String value2) {
            addCriterion("business_license between", value1, value2, "business_license");
            return (Criteria) this;
        }

        public Criteria andBusiness_licenseNotBetween(String value1, String value2) {
            addCriterion("business_license not between", value1, value2, "business_license");
            return (Criteria) this;
        }

        public Criteria andReq_keyIsNull() {
            addCriterion("req_key is null");
            return (Criteria) this;
        }

        public Criteria andReq_keyIsNotNull() {
            addCriterion("req_key is not null");
            return (Criteria) this;
        }

        public Criteria andReq_keyEqualTo(String value) {
            addCriterion("req_key =", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyNotEqualTo(String value) {
            addCriterion("req_key <>", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyGreaterThan(String value) {
            addCriterion("req_key >", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyGreaterThanOrEqualTo(String value) {
            addCriterion("req_key >=", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyLessThan(String value) {
            addCriterion("req_key <", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyLessThanOrEqualTo(String value) {
            addCriterion("req_key <=", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyLike(String value) {
            addCriterion("req_key like", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyNotLike(String value) {
            addCriterion("req_key not like", value, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyIn(List<String> values) {
            addCriterion("req_key in", values, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyNotIn(List<String> values) {
            addCriterion("req_key not in", values, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyBetween(String value1, String value2) {
            addCriterion("req_key between", value1, value2, "req_key");
            return (Criteria) this;
        }

        public Criteria andReq_keyNotBetween(String value1, String value2) {
            addCriterion("req_key not between", value1, value2, "req_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyIsNull() {
            addCriterion("res_key is null");
            return (Criteria) this;
        }

        public Criteria andRes_keyIsNotNull() {
            addCriterion("res_key is not null");
            return (Criteria) this;
        }

        public Criteria andRes_keyEqualTo(String value) {
            addCriterion("res_key =", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyNotEqualTo(String value) {
            addCriterion("res_key <>", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyGreaterThan(String value) {
            addCriterion("res_key >", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyGreaterThanOrEqualTo(String value) {
            addCriterion("res_key >=", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyLessThan(String value) {
            addCriterion("res_key <", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyLessThanOrEqualTo(String value) {
            addCriterion("res_key <=", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyLike(String value) {
            addCriterion("res_key like", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyNotLike(String value) {
            addCriterion("res_key not like", value, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyIn(List<String> values) {
            addCriterion("res_key in", values, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyNotIn(List<String> values) {
            addCriterion("res_key not in", values, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyBetween(String value1, String value2) {
            addCriterion("res_key between", value1, value2, "res_key");
            return (Criteria) this;
        }

        public Criteria andRes_keyNotBetween(String value1, String value2) {
            addCriterion("res_key not between", value1, value2, "res_key");
            return (Criteria) this;
        }

        public Criteria andBranch_idIsNull() {
            addCriterion("branch_id is null");
            return (Criteria) this;
        }

        public Criteria andBranch_idIsNotNull() {
            addCriterion("branch_id is not null");
            return (Criteria) this;
        }

        public Criteria andBranch_idEqualTo(String value) {
            addCriterion("branch_id =", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idNotEqualTo(String value) {
            addCriterion("branch_id <>", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idGreaterThan(String value) {
            addCriterion("branch_id >", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idGreaterThanOrEqualTo(String value) {
            addCriterion("branch_id >=", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idLessThan(String value) {
            addCriterion("branch_id <", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idLessThanOrEqualTo(String value) {
            addCriterion("branch_id <=", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idLike(String value) {
            addCriterion("branch_id like", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idNotLike(String value) {
            addCriterion("branch_id not like", value, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idIn(List<String> values) {
            addCriterion("branch_id in", values, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idNotIn(List<String> values) {
            addCriterion("branch_id not in", values, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idBetween(String value1, String value2) {
            addCriterion("branch_id between", value1, value2, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_idNotBetween(String value1, String value2) {
            addCriterion("branch_id not between", value1, value2, "branch_id");
            return (Criteria) this;
        }

        public Criteria andBranch_nameIsNull() {
            addCriterion("branch_name is null");
            return (Criteria) this;
        }

        public Criteria andBranch_nameIsNotNull() {
            addCriterion("branch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBranch_nameEqualTo(String value) {
            addCriterion("branch_name =", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameNotEqualTo(String value) {
            addCriterion("branch_name <>", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameGreaterThan(String value) {
            addCriterion("branch_name >", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameGreaterThanOrEqualTo(String value) {
            addCriterion("branch_name >=", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameLessThan(String value) {
            addCriterion("branch_name <", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameLessThanOrEqualTo(String value) {
            addCriterion("branch_name <=", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameLike(String value) {
            addCriterion("branch_name like", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameNotLike(String value) {
            addCriterion("branch_name not like", value, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameIn(List<String> values) {
            addCriterion("branch_name in", values, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameNotIn(List<String> values) {
            addCriterion("branch_name not in", values, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameBetween(String value1, String value2) {
            addCriterion("branch_name between", value1, value2, "branch_name");
            return (Criteria) this;
        }

        public Criteria andBranch_nameNotBetween(String value1, String value2) {
            addCriterion("branch_name not between", value1, value2, "branch_name");
            return (Criteria) this;
        }

        public Criteria andStaff_idIsNull() {
            addCriterion("staff_id is null");
            return (Criteria) this;
        }

        public Criteria andStaff_idIsNotNull() {
            addCriterion("staff_id is not null");
            return (Criteria) this;
        }

        public Criteria andStaff_idEqualTo(String value) {
            addCriterion("staff_id =", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idNotEqualTo(String value) {
            addCriterion("staff_id <>", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idGreaterThan(String value) {
            addCriterion("staff_id >", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idGreaterThanOrEqualTo(String value) {
            addCriterion("staff_id >=", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idLessThan(String value) {
            addCriterion("staff_id <", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idLessThanOrEqualTo(String value) {
            addCriterion("staff_id <=", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idLike(String value) {
            addCriterion("staff_id like", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idNotLike(String value) {
            addCriterion("staff_id not like", value, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idIn(List<String> values) {
            addCriterion("staff_id in", values, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idNotIn(List<String> values) {
            addCriterion("staff_id not in", values, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idBetween(String value1, String value2) {
            addCriterion("staff_id between", value1, value2, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStaff_idNotBetween(String value1, String value2) {
            addCriterion("staff_id not between", value1, value2, "staff_id");
            return (Criteria) this;
        }

        public Criteria andStall_nameIsNull() {
            addCriterion("stall_name is null");
            return (Criteria) this;
        }

        public Criteria andStall_nameIsNotNull() {
            addCriterion("stall_name is not null");
            return (Criteria) this;
        }

        public Criteria andStall_nameEqualTo(String value) {
            addCriterion("stall_name =", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameNotEqualTo(String value) {
            addCriterion("stall_name <>", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameGreaterThan(String value) {
            addCriterion("stall_name >", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameGreaterThanOrEqualTo(String value) {
            addCriterion("stall_name >=", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameLessThan(String value) {
            addCriterion("stall_name <", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameLessThanOrEqualTo(String value) {
            addCriterion("stall_name <=", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameLike(String value) {
            addCriterion("stall_name like", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameNotLike(String value) {
            addCriterion("stall_name not like", value, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameIn(List<String> values) {
            addCriterion("stall_name in", values, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameNotIn(List<String> values) {
            addCriterion("stall_name not in", values, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameBetween(String value1, String value2) {
            addCriterion("stall_name between", value1, value2, "stall_name");
            return (Criteria) this;
        }

        public Criteria andStall_nameNotBetween(String value1, String value2) {
            addCriterion("stall_name not between", value1, value2, "stall_name");
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

        public Criteria andAudit_timeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAudit_timeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAudit_timeEqualTo(String value) {
            addCriterion("audit_time =", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeNotEqualTo(String value) {
            addCriterion("audit_time <>", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeGreaterThan(String value) {
            addCriterion("audit_time >", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeGreaterThanOrEqualTo(String value) {
            addCriterion("audit_time >=", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeLessThan(String value) {
            addCriterion("audit_time <", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeLessThanOrEqualTo(String value) {
            addCriterion("audit_time <=", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeLike(String value) {
            addCriterion("audit_time like", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeNotLike(String value) {
            addCriterion("audit_time not like", value, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeIn(List<String> values) {
            addCriterion("audit_time in", values, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeNotIn(List<String> values) {
            addCriterion("audit_time not in", values, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeBetween(String value1, String value2) {
            addCriterion("audit_time between", value1, value2, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_timeNotBetween(String value1, String value2) {
            addCriterion("audit_time not between", value1, value2, "audit_time");
            return (Criteria) this;
        }

        public Criteria andAudit_statusIsNull() {
            addCriterion("audit_status is null");
            return (Criteria) this;
        }

        public Criteria andAudit_statusIsNotNull() {
            addCriterion("audit_status is not null");
            return (Criteria) this;
        }

        public Criteria andAudit_statusEqualTo(Byte value) {
            addCriterion("audit_status =", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusNotEqualTo(Byte value) {
            addCriterion("audit_status <>", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusGreaterThan(Byte value) {
            addCriterion("audit_status >", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("audit_status >=", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusLessThan(Byte value) {
            addCriterion("audit_status <", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusLessThanOrEqualTo(Byte value) {
            addCriterion("audit_status <=", value, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusIn(List<Byte> values) {
            addCriterion("audit_status in", values, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusNotIn(List<Byte> values) {
            addCriterion("audit_status not in", values, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusBetween(Byte value1, Byte value2) {
            addCriterion("audit_status between", value1, value2, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("audit_status not between", value1, value2, "audit_status");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonIsNull() {
            addCriterion("audit_reason is null");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonIsNotNull() {
            addCriterion("audit_reason is not null");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonEqualTo(String value) {
            addCriterion("audit_reason =", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonNotEqualTo(String value) {
            addCriterion("audit_reason <>", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonGreaterThan(String value) {
            addCriterion("audit_reason >", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonGreaterThanOrEqualTo(String value) {
            addCriterion("audit_reason >=", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonLessThan(String value) {
            addCriterion("audit_reason <", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonLessThanOrEqualTo(String value) {
            addCriterion("audit_reason <=", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonLike(String value) {
            addCriterion("audit_reason like", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonNotLike(String value) {
            addCriterion("audit_reason not like", value, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonIn(List<String> values) {
            addCriterion("audit_reason in", values, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonNotIn(List<String> values) {
            addCriterion("audit_reason not in", values, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonBetween(String value1, String value2) {
            addCriterion("audit_reason between", value1, value2, "audit_reason");
            return (Criteria) this;
        }

        public Criteria andAudit_reasonNotBetween(String value1, String value2) {
            addCriterion("audit_reason not between", value1, value2, "audit_reason");
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