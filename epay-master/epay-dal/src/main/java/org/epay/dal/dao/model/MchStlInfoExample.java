package org.epay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MchStlInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public MchStlInfoExample() {
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

        public Criteria andMch_bank_accountIsNull() {
            addCriterion("mch_bank_account is null");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountIsNotNull() {
            addCriterion("mch_bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountEqualTo(String value) {
            addCriterion("mch_bank_account =", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountNotEqualTo(String value) {
            addCriterion("mch_bank_account <>", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountGreaterThan(String value) {
            addCriterion("mch_bank_account >", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountGreaterThanOrEqualTo(String value) {
            addCriterion("mch_bank_account >=", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountLessThan(String value) {
            addCriterion("mch_bank_account <", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountLessThanOrEqualTo(String value) {
            addCriterion("mch_bank_account <=", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountLike(String value) {
            addCriterion("mch_bank_account like", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountNotLike(String value) {
            addCriterion("mch_bank_account not like", value, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountIn(List<String> values) {
            addCriterion("mch_bank_account in", values, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountNotIn(List<String> values) {
            addCriterion("mch_bank_account not in", values, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountBetween(String value1, String value2) {
            addCriterion("mch_bank_account between", value1, value2, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_accountNotBetween(String value1, String value2) {
            addCriterion("mch_bank_account not between", value1, value2, "mch_bank_account");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameIsNull() {
            addCriterion("mch_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameIsNotNull() {
            addCriterion("mch_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameEqualTo(String value) {
            addCriterion("mch_bank_name =", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameNotEqualTo(String value) {
            addCriterion("mch_bank_name <>", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameGreaterThan(String value) {
            addCriterion("mch_bank_name >", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameGreaterThanOrEqualTo(String value) {
            addCriterion("mch_bank_name >=", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameLessThan(String value) {
            addCriterion("mch_bank_name <", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameLessThanOrEqualTo(String value) {
            addCriterion("mch_bank_name <=", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameLike(String value) {
            addCriterion("mch_bank_name like", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameNotLike(String value) {
            addCriterion("mch_bank_name not like", value, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameIn(List<String> values) {
            addCriterion("mch_bank_name in", values, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameNotIn(List<String> values) {
            addCriterion("mch_bank_name not in", values, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameBetween(String value1, String value2) {
            addCriterion("mch_bank_name between", value1, value2, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andMch_bank_nameNotBetween(String value1, String value2) {
            addCriterion("mch_bank_name not between", value1, value2, "mch_bank_name");
            return (Criteria) this;
        }

        public Criteria andAccount_bankIsNull() {
            addCriterion("account_bank is null");
            return (Criteria) this;
        }

        public Criteria andAccount_bankIsNotNull() {
            addCriterion("account_bank is not null");
            return (Criteria) this;
        }

        public Criteria andAccount_bankEqualTo(String value) {
            addCriterion("account_bank =", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankNotEqualTo(String value) {
            addCriterion("account_bank <>", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankGreaterThan(String value) {
            addCriterion("account_bank >", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankGreaterThanOrEqualTo(String value) {
            addCriterion("account_bank >=", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankLessThan(String value) {
            addCriterion("account_bank <", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankLessThanOrEqualTo(String value) {
            addCriterion("account_bank <=", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankLike(String value) {
            addCriterion("account_bank like", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankNotLike(String value) {
            addCriterion("account_bank not like", value, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankIn(List<String> values) {
            addCriterion("account_bank in", values, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankNotIn(List<String> values) {
            addCriterion("account_bank not in", values, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankBetween(String value1, String value2) {
            addCriterion("account_bank between", value1, value2, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_bankNotBetween(String value1, String value2) {
            addCriterion("account_bank not between", value1, value2, "account_bank");
            return (Criteria) this;
        }

        public Criteria andAccount_typeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccount_typeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccount_typeEqualTo(String value) {
            addCriterion("account_type =", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeNotEqualTo(String value) {
            addCriterion("account_type <>", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeGreaterThan(String value) {
            addCriterion("account_type >", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeGreaterThanOrEqualTo(String value) {
            addCriterion("account_type >=", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeLessThan(String value) {
            addCriterion("account_type <", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeLessThanOrEqualTo(String value) {
            addCriterion("account_type <=", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeLike(String value) {
            addCriterion("account_type like", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeNotLike(String value) {
            addCriterion("account_type not like", value, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeIn(List<String> values) {
            addCriterion("account_type in", values, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeNotIn(List<String> values) {
            addCriterion("account_type not in", values, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeBetween(String value1, String value2) {
            addCriterion("account_type between", value1, value2, "account_type");
            return (Criteria) this;
        }

        public Criteria andAccount_typeNotBetween(String value1, String value2) {
            addCriterion("account_type not between", value1, value2, "account_type");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayIsNull() {
            addCriterion("mch_stl_day is null");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayIsNotNull() {
            addCriterion("mch_stl_day is not null");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayEqualTo(String value) {
            addCriterion("mch_stl_day =", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayNotEqualTo(String value) {
            addCriterion("mch_stl_day <>", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayGreaterThan(String value) {
            addCriterion("mch_stl_day >", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayGreaterThanOrEqualTo(String value) {
            addCriterion("mch_stl_day >=", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayLessThan(String value) {
            addCriterion("mch_stl_day <", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayLessThanOrEqualTo(String value) {
            addCriterion("mch_stl_day <=", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayLike(String value) {
            addCriterion("mch_stl_day like", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayNotLike(String value) {
            addCriterion("mch_stl_day not like", value, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayIn(List<String> values) {
            addCriterion("mch_stl_day in", values, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayNotIn(List<String> values) {
            addCriterion("mch_stl_day not in", values, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayBetween(String value1, String value2) {
            addCriterion("mch_stl_day between", value1, value2, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andMch_stl_dayNotBetween(String value1, String value2) {
            addCriterion("mch_stl_day not between", value1, value2, "mch_stl_day");
            return (Criteria) this;
        }

        public Criteria andCost_rateIsNull() {
            addCriterion("cost_rate is null");
            return (Criteria) this;
        }

        public Criteria andCost_rateIsNotNull() {
            addCriterion("cost_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCost_rateEqualTo(String value) {
            addCriterion("cost_rate =", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateNotEqualTo(String value) {
            addCriterion("cost_rate <>", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateGreaterThan(String value) {
            addCriterion("cost_rate >", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateGreaterThanOrEqualTo(String value) {
            addCriterion("cost_rate >=", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateLessThan(String value) {
            addCriterion("cost_rate <", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateLessThanOrEqualTo(String value) {
            addCriterion("cost_rate <=", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateLike(String value) {
            addCriterion("cost_rate like", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateNotLike(String value) {
            addCriterion("cost_rate not like", value, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateIn(List<String> values) {
            addCriterion("cost_rate in", values, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateNotIn(List<String> values) {
            addCriterion("cost_rate not in", values, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateBetween(String value1, String value2) {
            addCriterion("cost_rate between", value1, value2, "cost_rate");
            return (Criteria) this;
        }

        public Criteria andCost_rateNotBetween(String value1, String value2) {
            addCriterion("cost_rate not between", value1, value2, "cost_rate");
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