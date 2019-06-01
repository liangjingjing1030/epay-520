package org.epay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AccountBookExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public AccountBookExample() {
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

        public Criteria andAccount_book_idIsNull() {
            addCriterion("account_book_id is null");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idIsNotNull() {
            addCriterion("account_book_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idEqualTo(String value) {
            addCriterion("account_book_id =", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idNotEqualTo(String value) {
            addCriterion("account_book_id <>", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idGreaterThan(String value) {
            addCriterion("account_book_id >", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idGreaterThanOrEqualTo(String value) {
            addCriterion("account_book_id >=", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idLessThan(String value) {
            addCriterion("account_book_id <", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idLessThanOrEqualTo(String value) {
            addCriterion("account_book_id <=", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idLike(String value) {
            addCriterion("account_book_id like", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idNotLike(String value) {
            addCriterion("account_book_id not like", value, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idIn(List<String> values) {
            addCriterion("account_book_id in", values, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idNotIn(List<String> values) {
            addCriterion("account_book_id not in", values, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idBetween(String value1, String value2) {
            addCriterion("account_book_id between", value1, value2, "account_book_id");
            return (Criteria) this;
        }

        public Criteria andAccount_book_idNotBetween(String value1, String value2) {
            addCriterion("account_book_id not between", value1, value2, "account_book_id");
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

        public Criteria andUser_idIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUser_idIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUser_idEqualTo(String value) {
            addCriterion("user_id =", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotEqualTo(String value) {
            addCriterion("user_id <>", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThan(String value) {
            addCriterion("user_id >", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThan(String value) {
            addCriterion("user_id <", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idLike(String value) {
            addCriterion("user_id like", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotLike(String value) {
            addCriterion("user_id not like", value, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idIn(List<String> values) {
            addCriterion("user_id in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotIn(List<String> values) {
            addCriterion("user_id not in", values, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_idNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "user_id");
            return (Criteria) this;
        }

        public Criteria andUser_nameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUser_nameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUser_nameEqualTo(String value) {
            addCriterion("user_name =", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameGreaterThan(String value) {
            addCriterion("user_name >", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameLessThan(String value) {
            addCriterion("user_name <", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameLike(String value) {
            addCriterion("user_name like", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotLike(String value) {
            addCriterion("user_name not like", value, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameIn(List<String> values) {
            addCriterion("user_name in", values, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "user_name");
            return (Criteria) this;
        }

        public Criteria andUser_nameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "user_name");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andItems_moneyIsNull() {
            addCriterion("items_money is null");
            return (Criteria) this;
        }

        public Criteria andItems_moneyIsNotNull() {
            addCriterion("items_money is not null");
            return (Criteria) this;
        }

        public Criteria andItems_moneyEqualTo(Long value) {
            addCriterion("items_money =", value, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyNotEqualTo(Long value) {
            addCriterion("items_money <>", value, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyGreaterThan(Long value) {
            addCriterion("items_money >", value, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyGreaterThanOrEqualTo(Long value) {
            addCriterion("items_money >=", value, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyLessThan(Long value) {
            addCriterion("items_money <", value, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyLessThanOrEqualTo(Long value) {
            addCriterion("items_money <=", value, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyIn(List<Long> values) {
            addCriterion("items_money in", values, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyNotIn(List<Long> values) {
            addCriterion("items_money not in", values, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyBetween(Long value1, Long value2) {
            addCriterion("items_money between", value1, value2, "items_money");
            return (Criteria) this;
        }

        public Criteria andItems_moneyNotBetween(Long value1, Long value2) {
            addCriterion("items_money not between", value1, value2, "items_money");
            return (Criteria) this;
        }

        public Criteria andPay_timeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPay_timeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPay_timeEqualTo(String value) {
            addCriterion("pay_time =", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeNotEqualTo(String value) {
            addCriterion("pay_time <>", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeGreaterThan(String value) {
            addCriterion("pay_time >", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeGreaterThanOrEqualTo(String value) {
            addCriterion("pay_time >=", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeLessThan(String value) {
            addCriterion("pay_time <", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeLessThanOrEqualTo(String value) {
            addCriterion("pay_time <=", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeLike(String value) {
            addCriterion("pay_time like", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeNotLike(String value) {
            addCriterion("pay_time not like", value, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeIn(List<String> values) {
            addCriterion("pay_time in", values, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeNotIn(List<String> values) {
            addCriterion("pay_time not in", values, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeBetween(String value1, String value2) {
            addCriterion("pay_time between", value1, value2, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_timeNotBetween(String value1, String value2) {
            addCriterion("pay_time not between", value1, value2, "pay_time");
            return (Criteria) this;
        }

        public Criteria andPay_statusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPay_statusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPay_statusEqualTo(Byte value) {
            addCriterion("pay_status =", value, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusNotEqualTo(Byte value) {
            addCriterion("pay_status <>", value, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusGreaterThan(Byte value) {
            addCriterion("pay_status >", value, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_status >=", value, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusLessThan(Byte value) {
            addCriterion("pay_status <", value, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusLessThanOrEqualTo(Byte value) {
            addCriterion("pay_status <=", value, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusIn(List<Byte> values) {
            addCriterion("pay_status in", values, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusNotIn(List<Byte> values) {
            addCriterion("pay_status not in", values, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusBetween(Byte value1, Byte value2) {
            addCriterion("pay_status between", value1, value2, "pay_status");
            return (Criteria) this;
        }

        public Criteria andPay_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_status not between", value1, value2, "pay_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheck_statusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheck_statusEqualTo(Byte value) {
            addCriterion("check_status =", value, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusNotEqualTo(Byte value) {
            addCriterion("check_status <>", value, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusGreaterThan(Byte value) {
            addCriterion("check_status >", value, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("check_status >=", value, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusLessThan(Byte value) {
            addCriterion("check_status <", value, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusLessThanOrEqualTo(Byte value) {
            addCriterion("check_status <=", value, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusIn(List<Byte> values) {
            addCriterion("check_status in", values, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusNotIn(List<Byte> values) {
            addCriterion("check_status not in", values, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusBetween(Byte value1, Byte value2) {
            addCriterion("check_status between", value1, value2, "check_status");
            return (Criteria) this;
        }

        public Criteria andCheck_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("check_status not between", value1, value2, "check_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusIsNull() {
            addCriterion("settle_status is null");
            return (Criteria) this;
        }

        public Criteria andSettle_statusIsNotNull() {
            addCriterion("settle_status is not null");
            return (Criteria) this;
        }

        public Criteria andSettle_statusEqualTo(Byte value) {
            addCriterion("settle_status =", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusNotEqualTo(Byte value) {
            addCriterion("settle_status <>", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusGreaterThan(Byte value) {
            addCriterion("settle_status >", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusGreaterThanOrEqualTo(Byte value) {
            addCriterion("settle_status >=", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusLessThan(Byte value) {
            addCriterion("settle_status <", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusLessThanOrEqualTo(Byte value) {
            addCriterion("settle_status <=", value, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusIn(List<Byte> values) {
            addCriterion("settle_status in", values, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusNotIn(List<Byte> values) {
            addCriterion("settle_status not in", values, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusBetween(Byte value1, Byte value2) {
            addCriterion("settle_status between", value1, value2, "settle_status");
            return (Criteria) this;
        }

        public Criteria andSettle_statusNotBetween(Byte value1, Byte value2) {
            addCriterion("settle_status not between", value1, value2, "settle_status");
            return (Criteria) this;
        }

        public Criteria andPay_channelIsNull() {
            addCriterion("pay_channel is null");
            return (Criteria) this;
        }

        public Criteria andPay_channelIsNotNull() {
            addCriterion("pay_channel is not null");
            return (Criteria) this;
        }

        public Criteria andPay_channelEqualTo(String value) {
            addCriterion("pay_channel =", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelNotEqualTo(String value) {
            addCriterion("pay_channel <>", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelGreaterThan(String value) {
            addCriterion("pay_channel >", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelGreaterThanOrEqualTo(String value) {
            addCriterion("pay_channel >=", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelLessThan(String value) {
            addCriterion("pay_channel <", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelLessThanOrEqualTo(String value) {
            addCriterion("pay_channel <=", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelLike(String value) {
            addCriterion("pay_channel like", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelNotLike(String value) {
            addCriterion("pay_channel not like", value, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelIn(List<String> values) {
            addCriterion("pay_channel in", values, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelNotIn(List<String> values) {
            addCriterion("pay_channel not in", values, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelBetween(String value1, String value2) {
            addCriterion("pay_channel between", value1, value2, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andPay_channelNotBetween(String value1, String value2) {
            addCriterion("pay_channel not between", value1, value2, "pay_channel");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costIsNull() {
            addCriterion("channel_settle_cost is null");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costIsNotNull() {
            addCriterion("channel_settle_cost is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costEqualTo(Integer value) {
            addCriterion("channel_settle_cost =", value, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costNotEqualTo(Integer value) {
            addCriterion("channel_settle_cost <>", value, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costGreaterThan(Integer value) {
            addCriterion("channel_settle_cost >", value, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_settle_cost >=", value, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costLessThan(Integer value) {
            addCriterion("channel_settle_cost <", value, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costLessThanOrEqualTo(Integer value) {
            addCriterion("channel_settle_cost <=", value, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costIn(List<Integer> values) {
            addCriterion("channel_settle_cost in", values, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costNotIn(List<Integer> values) {
            addCriterion("channel_settle_cost not in", values, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costBetween(Integer value1, Integer value2) {
            addCriterion("channel_settle_cost between", value1, value2, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_costNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_settle_cost not between", value1, value2, "channel_settle_cost");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyIsNull() {
            addCriterion("channel_settle_money is null");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyIsNotNull() {
            addCriterion("channel_settle_money is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyEqualTo(Long value) {
            addCriterion("channel_settle_money =", value, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyNotEqualTo(Long value) {
            addCriterion("channel_settle_money <>", value, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyGreaterThan(Long value) {
            addCriterion("channel_settle_money >", value, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyGreaterThanOrEqualTo(Long value) {
            addCriterion("channel_settle_money >=", value, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyLessThan(Long value) {
            addCriterion("channel_settle_money <", value, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyLessThanOrEqualTo(Long value) {
            addCriterion("channel_settle_money <=", value, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyIn(List<Long> values) {
            addCriterion("channel_settle_money in", values, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyNotIn(List<Long> values) {
            addCriterion("channel_settle_money not in", values, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyBetween(Long value1, Long value2) {
            addCriterion("channel_settle_money between", value1, value2, "channel_settle_money");
            return (Criteria) this;
        }

        public Criteria andChannel_settle_moneyNotBetween(Long value1, Long value2) {
            addCriterion("channel_settle_money not between", value1, value2, "channel_settle_money");
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