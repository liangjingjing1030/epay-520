package org.epay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RefundOrderExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public RefundOrderExample() {
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

        public Criteria andRefund_order_idIsNull() {
            addCriterion("refund_order_id is null");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idIsNotNull() {
            addCriterion("refund_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idEqualTo(String value) {
            addCriterion("refund_order_id =", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idNotEqualTo(String value) {
            addCriterion("refund_order_id <>", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idGreaterThan(String value) {
            addCriterion("refund_order_id >", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idGreaterThanOrEqualTo(String value) {
            addCriterion("refund_order_id >=", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idLessThan(String value) {
            addCriterion("refund_order_id <", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idLessThanOrEqualTo(String value) {
            addCriterion("refund_order_id <=", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idLike(String value) {
            addCriterion("refund_order_id like", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idNotLike(String value) {
            addCriterion("refund_order_id not like", value, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idIn(List<String> values) {
            addCriterion("refund_order_id in", values, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idNotIn(List<String> values) {
            addCriterion("refund_order_id not in", values, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idBetween(String value1, String value2) {
            addCriterion("refund_order_id between", value1, value2, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andRefund_order_idNotBetween(String value1, String value2) {
            addCriterion("refund_order_id not between", value1, value2, "refund_order_id");
            return (Criteria) this;
        }

        public Criteria andPay_orderidIsNull() {
            addCriterion("pay_orderid is null");
            return (Criteria) this;
        }

        public Criteria andPay_orderidIsNotNull() {
            addCriterion("pay_orderid is not null");
            return (Criteria) this;
        }

        public Criteria andPay_orderidEqualTo(String value) {
            addCriterion("pay_orderid =", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidNotEqualTo(String value) {
            addCriterion("pay_orderid <>", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidGreaterThan(String value) {
            addCriterion("pay_orderid >", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidGreaterThanOrEqualTo(String value) {
            addCriterion("pay_orderid >=", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidLessThan(String value) {
            addCriterion("pay_orderid <", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidLessThanOrEqualTo(String value) {
            addCriterion("pay_orderid <=", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidLike(String value) {
            addCriterion("pay_orderid like", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidNotLike(String value) {
            addCriterion("pay_orderid not like", value, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidIn(List<String> values) {
            addCriterion("pay_orderid in", values, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidNotIn(List<String> values) {
            addCriterion("pay_orderid not in", values, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidBetween(String value1, String value2) {
            addCriterion("pay_orderid between", value1, value2, "pay_orderid");
            return (Criteria) this;
        }

        public Criteria andPay_orderidNotBetween(String value1, String value2) {
            addCriterion("pay_orderid not between", value1, value2, "pay_orderid");
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

        public Criteria andChannel_idIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannel_idIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_idEqualTo(String value) {
            addCriterion("channel_id =", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idNotEqualTo(String value) {
            addCriterion("channel_id <>", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idGreaterThan(String value) {
            addCriterion("channel_id >", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idGreaterThanOrEqualTo(String value) {
            addCriterion("channel_id >=", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idLessThan(String value) {
            addCriterion("channel_id <", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idLessThanOrEqualTo(String value) {
            addCriterion("channel_id <=", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idLike(String value) {
            addCriterion("channel_id like", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idNotLike(String value) {
            addCriterion("channel_id not like", value, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idIn(List<String> values) {
            addCriterion("channel_id in", values, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idNotIn(List<String> values) {
            addCriterion("channel_id not in", values, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idBetween(String value1, String value2) {
            addCriterion("channel_id between", value1, value2, "channel_id");
            return (Criteria) this;
        }

        public Criteria andChannel_idNotBetween(String value1, String value2) {
            addCriterion("channel_id not between", value1, value2, "channel_id");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noIsNull() {
            addCriterion("mch_refund_no is null");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noIsNotNull() {
            addCriterion("mch_refund_no is not null");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noEqualTo(String value) {
            addCriterion("mch_refund_no =", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noNotEqualTo(String value) {
            addCriterion("mch_refund_no <>", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noGreaterThan(String value) {
            addCriterion("mch_refund_no >", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noGreaterThanOrEqualTo(String value) {
            addCriterion("mch_refund_no >=", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noLessThan(String value) {
            addCriterion("mch_refund_no <", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noLessThanOrEqualTo(String value) {
            addCriterion("mch_refund_no <=", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noLike(String value) {
            addCriterion("mch_refund_no like", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noNotLike(String value) {
            addCriterion("mch_refund_no not like", value, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noIn(List<String> values) {
            addCriterion("mch_refund_no in", values, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noNotIn(List<String> values) {
            addCriterion("mch_refund_no not in", values, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noBetween(String value1, String value2) {
            addCriterion("mch_refund_no between", value1, value2, "mch_refund_no");
            return (Criteria) this;
        }

        public Criteria andMch_refund_noNotBetween(String value1, String value2) {
            addCriterion("mch_refund_no not between", value1, value2, "mch_refund_no");
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

        public Criteria andUser_channel_accountIsNull() {
            addCriterion("user_channel_account is null");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountIsNotNull() {
            addCriterion("user_channel_account is not null");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountEqualTo(String value) {
            addCriterion("user_channel_account =", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountNotEqualTo(String value) {
            addCriterion("user_channel_account <>", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountGreaterThan(String value) {
            addCriterion("user_channel_account >", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountGreaterThanOrEqualTo(String value) {
            addCriterion("user_channel_account >=", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountLessThan(String value) {
            addCriterion("user_channel_account <", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountLessThanOrEqualTo(String value) {
            addCriterion("user_channel_account <=", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountLike(String value) {
            addCriterion("user_channel_account like", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountNotLike(String value) {
            addCriterion("user_channel_account not like", value, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountIn(List<String> values) {
            addCriterion("user_channel_account in", values, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountNotIn(List<String> values) {
            addCriterion("user_channel_account not in", values, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountBetween(String value1, String value2) {
            addCriterion("user_channel_account between", value1, value2, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andUser_channel_accountNotBetween(String value1, String value2) {
            addCriterion("user_channel_account not between", value1, value2, "user_channel_account");
            return (Criteria) this;
        }

        public Criteria andPay_amountIsNull() {
            addCriterion("pay_amount is null");
            return (Criteria) this;
        }

        public Criteria andPay_amountIsNotNull() {
            addCriterion("pay_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPay_amountEqualTo(Long value) {
            addCriterion("pay_amount =", value, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountNotEqualTo(Long value) {
            addCriterion("pay_amount <>", value, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountGreaterThan(Long value) {
            addCriterion("pay_amount >", value, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountGreaterThanOrEqualTo(Long value) {
            addCriterion("pay_amount >=", value, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountLessThan(Long value) {
            addCriterion("pay_amount <", value, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountLessThanOrEqualTo(Long value) {
            addCriterion("pay_amount <=", value, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountIn(List<Long> values) {
            addCriterion("pay_amount in", values, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountNotIn(List<Long> values) {
            addCriterion("pay_amount not in", values, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountBetween(Long value1, Long value2) {
            addCriterion("pay_amount between", value1, value2, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andPay_amountNotBetween(Long value1, Long value2) {
            addCriterion("pay_amount not between", value1, value2, "pay_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountIsNull() {
            addCriterion("refund_amount is null");
            return (Criteria) this;
        }

        public Criteria andRefund_amountIsNotNull() {
            addCriterion("refund_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRefund_amountEqualTo(Long value) {
            addCriterion("refund_amount =", value, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountNotEqualTo(Long value) {
            addCriterion("refund_amount <>", value, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountGreaterThan(Long value) {
            addCriterion("refund_amount >", value, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountGreaterThanOrEqualTo(Long value) {
            addCriterion("refund_amount >=", value, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountLessThan(Long value) {
            addCriterion("refund_amount <", value, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountLessThanOrEqualTo(Long value) {
            addCriterion("refund_amount <=", value, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountIn(List<Long> values) {
            addCriterion("refund_amount in", values, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountNotIn(List<Long> values) {
            addCriterion("refund_amount not in", values, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountBetween(Long value1, Long value2) {
            addCriterion("refund_amount between", value1, value2, "refund_amount");
            return (Criteria) this;
        }

        public Criteria andRefund_amountNotBetween(Long value1, Long value2) {
            addCriterion("refund_amount not between", value1, value2, "refund_amount");
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

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noIsNull() {
            addCriterion("channel_pay_order_no is null");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noIsNotNull() {
            addCriterion("channel_pay_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noEqualTo(String value) {
            addCriterion("channel_pay_order_no =", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noNotEqualTo(String value) {
            addCriterion("channel_pay_order_no <>", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noGreaterThan(String value) {
            addCriterion("channel_pay_order_no >", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noGreaterThanOrEqualTo(String value) {
            addCriterion("channel_pay_order_no >=", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noLessThan(String value) {
            addCriterion("channel_pay_order_no <", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noLessThanOrEqualTo(String value) {
            addCriterion("channel_pay_order_no <=", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noLike(String value) {
            addCriterion("channel_pay_order_no like", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noNotLike(String value) {
            addCriterion("channel_pay_order_no not like", value, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noIn(List<String> values) {
            addCriterion("channel_pay_order_no in", values, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noNotIn(List<String> values) {
            addCriterion("channel_pay_order_no not in", values, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noBetween(String value1, String value2) {
            addCriterion("channel_pay_order_no between", value1, value2, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_pay_order_noNotBetween(String value1, String value2) {
            addCriterion("channel_pay_order_no not between", value1, value2, "channel_pay_order_no");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(Byte value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(Byte value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(Byte value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(Byte value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(Byte value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(Byte value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<Byte> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<Byte> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(Byte value1, Byte value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(Byte value1, Byte value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andClient_ipIsNull() {
            addCriterion("client_ip is null");
            return (Criteria) this;
        }

        public Criteria andClient_ipIsNotNull() {
            addCriterion("client_ip is not null");
            return (Criteria) this;
        }

        public Criteria andClient_ipEqualTo(String value) {
            addCriterion("client_ip =", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipNotEqualTo(String value) {
            addCriterion("client_ip <>", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipGreaterThan(String value) {
            addCriterion("client_ip >", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipGreaterThanOrEqualTo(String value) {
            addCriterion("client_ip >=", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipLessThan(String value) {
            addCriterion("client_ip <", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipLessThanOrEqualTo(String value) {
            addCriterion("client_ip <=", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipLike(String value) {
            addCriterion("client_ip like", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipNotLike(String value) {
            addCriterion("client_ip not like", value, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipIn(List<String> values) {
            addCriterion("client_ip in", values, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipNotIn(List<String> values) {
            addCriterion("client_ip not in", values, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipBetween(String value1, String value2) {
            addCriterion("client_ip between", value1, value2, "client_ip");
            return (Criteria) this;
        }

        public Criteria andClient_ipNotBetween(String value1, String value2) {
            addCriterion("client_ip not between", value1, value2, "client_ip");
            return (Criteria) this;
        }

        public Criteria andDeviceIsNull() {
            addCriterion("device is null");
            return (Criteria) this;
        }

        public Criteria andDeviceIsNotNull() {
            addCriterion("device is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceEqualTo(String value) {
            addCriterion("device =", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotEqualTo(String value) {
            addCriterion("device <>", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceGreaterThan(String value) {
            addCriterion("device >", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceGreaterThanOrEqualTo(String value) {
            addCriterion("device >=", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLessThan(String value) {
            addCriterion("device <", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLessThanOrEqualTo(String value) {
            addCriterion("device <=", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceLike(String value) {
            addCriterion("device like", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotLike(String value) {
            addCriterion("device not like", value, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceIn(List<String> values) {
            addCriterion("device in", values, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotIn(List<String> values) {
            addCriterion("device not in", values, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceBetween(String value1, String value2) {
            addCriterion("device between", value1, value2, "device");
            return (Criteria) this;
        }

        public Criteria andDeviceNotBetween(String value1, String value2) {
            addCriterion("device not between", value1, value2, "device");
            return (Criteria) this;
        }

        public Criteria andRemark_infoIsNull() {
            addCriterion("remark_info is null");
            return (Criteria) this;
        }

        public Criteria andRemark_infoIsNotNull() {
            addCriterion("remark_info is not null");
            return (Criteria) this;
        }

        public Criteria andRemark_infoEqualTo(String value) {
            addCriterion("remark_info =", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoNotEqualTo(String value) {
            addCriterion("remark_info <>", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoGreaterThan(String value) {
            addCriterion("remark_info >", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoGreaterThanOrEqualTo(String value) {
            addCriterion("remark_info >=", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoLessThan(String value) {
            addCriterion("remark_info <", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoLessThanOrEqualTo(String value) {
            addCriterion("remark_info <=", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoLike(String value) {
            addCriterion("remark_info like", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoNotLike(String value) {
            addCriterion("remark_info not like", value, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoIn(List<String> values) {
            addCriterion("remark_info in", values, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoNotIn(List<String> values) {
            addCriterion("remark_info not in", values, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoBetween(String value1, String value2) {
            addCriterion("remark_info between", value1, value2, "remark_info");
            return (Criteria) this;
        }

        public Criteria andRemark_infoNotBetween(String value1, String value2) {
            addCriterion("remark_info not between", value1, value2, "remark_info");
            return (Criteria) this;
        }

        public Criteria andChannel_userIsNull() {
            addCriterion("channel_user is null");
            return (Criteria) this;
        }

        public Criteria andChannel_userIsNotNull() {
            addCriterion("channel_user is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_userEqualTo(String value) {
            addCriterion("channel_user =", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userNotEqualTo(String value) {
            addCriterion("channel_user <>", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userGreaterThan(String value) {
            addCriterion("channel_user >", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userGreaterThanOrEqualTo(String value) {
            addCriterion("channel_user >=", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userLessThan(String value) {
            addCriterion("channel_user <", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userLessThanOrEqualTo(String value) {
            addCriterion("channel_user <=", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userLike(String value) {
            addCriterion("channel_user like", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userNotLike(String value) {
            addCriterion("channel_user not like", value, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userIn(List<String> values) {
            addCriterion("channel_user in", values, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userNotIn(List<String> values) {
            addCriterion("channel_user not in", values, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userBetween(String value1, String value2) {
            addCriterion("channel_user between", value1, value2, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_userNotBetween(String value1, String value2) {
            addCriterion("channel_user not between", value1, value2, "channel_user");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idIsNull() {
            addCriterion("channel_mch_id is null");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idIsNotNull() {
            addCriterion("channel_mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idEqualTo(String value) {
            addCriterion("channel_mch_id =", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idNotEqualTo(String value) {
            addCriterion("channel_mch_id <>", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idGreaterThan(String value) {
            addCriterion("channel_mch_id >", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idGreaterThanOrEqualTo(String value) {
            addCriterion("channel_mch_id >=", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idLessThan(String value) {
            addCriterion("channel_mch_id <", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idLessThanOrEqualTo(String value) {
            addCriterion("channel_mch_id <=", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idLike(String value) {
            addCriterion("channel_mch_id like", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idNotLike(String value) {
            addCriterion("channel_mch_id not like", value, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idIn(List<String> values) {
            addCriterion("channel_mch_id in", values, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idNotIn(List<String> values) {
            addCriterion("channel_mch_id not in", values, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idBetween(String value1, String value2) {
            addCriterion("channel_mch_id between", value1, value2, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_mch_idNotBetween(String value1, String value2) {
            addCriterion("channel_mch_id not between", value1, value2, "channel_mch_id");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noIsNull() {
            addCriterion("channel_order_no is null");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noIsNotNull() {
            addCriterion("channel_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noEqualTo(String value) {
            addCriterion("channel_order_no =", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noNotEqualTo(String value) {
            addCriterion("channel_order_no <>", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noGreaterThan(String value) {
            addCriterion("channel_order_no >", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noGreaterThanOrEqualTo(String value) {
            addCriterion("channel_order_no >=", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noLessThan(String value) {
            addCriterion("channel_order_no <", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noLessThanOrEqualTo(String value) {
            addCriterion("channel_order_no <=", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noLike(String value) {
            addCriterion("channel_order_no like", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noNotLike(String value) {
            addCriterion("channel_order_no not like", value, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noIn(List<String> values) {
            addCriterion("channel_order_no in", values, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noNotIn(List<String> values) {
            addCriterion("channel_order_no not in", values, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noBetween(String value1, String value2) {
            addCriterion("channel_order_no between", value1, value2, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_order_noNotBetween(String value1, String value2) {
            addCriterion("channel_order_no not between", value1, value2, "channel_order_no");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeIsNull() {
            addCriterion("channel_res_code is null");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeIsNotNull() {
            addCriterion("channel_res_code is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeEqualTo(String value) {
            addCriterion("channel_res_code =", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeNotEqualTo(String value) {
            addCriterion("channel_res_code <>", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeGreaterThan(String value) {
            addCriterion("channel_res_code >", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeGreaterThanOrEqualTo(String value) {
            addCriterion("channel_res_code >=", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeLessThan(String value) {
            addCriterion("channel_res_code <", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeLessThanOrEqualTo(String value) {
            addCriterion("channel_res_code <=", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeLike(String value) {
            addCriterion("channel_res_code like", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeNotLike(String value) {
            addCriterion("channel_res_code not like", value, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeIn(List<String> values) {
            addCriterion("channel_res_code in", values, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeNotIn(List<String> values) {
            addCriterion("channel_res_code not in", values, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeBetween(String value1, String value2) {
            addCriterion("channel_res_code between", value1, value2, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_codeNotBetween(String value1, String value2) {
            addCriterion("channel_res_code not between", value1, value2, "channel_res_code");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgIsNull() {
            addCriterion("channel_res_msg is null");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgIsNotNull() {
            addCriterion("channel_res_msg is not null");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgEqualTo(String value) {
            addCriterion("channel_res_msg =", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgNotEqualTo(String value) {
            addCriterion("channel_res_msg <>", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgGreaterThan(String value) {
            addCriterion("channel_res_msg >", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgGreaterThanOrEqualTo(String value) {
            addCriterion("channel_res_msg >=", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgLessThan(String value) {
            addCriterion("channel_res_msg <", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgLessThanOrEqualTo(String value) {
            addCriterion("channel_res_msg <=", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgLike(String value) {
            addCriterion("channel_res_msg like", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgNotLike(String value) {
            addCriterion("channel_res_msg not like", value, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgIn(List<String> values) {
            addCriterion("channel_res_msg in", values, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgNotIn(List<String> values) {
            addCriterion("channel_res_msg not in", values, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgBetween(String value1, String value2) {
            addCriterion("channel_res_msg between", value1, value2, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andChannel_res_msgNotBetween(String value1, String value2) {
            addCriterion("channel_res_msg not between", value1, value2, "channel_res_msg");
            return (Criteria) this;
        }

        public Criteria andExtraIsNull() {
            addCriterion("extra is null");
            return (Criteria) this;
        }

        public Criteria andExtraIsNotNull() {
            addCriterion("extra is not null");
            return (Criteria) this;
        }

        public Criteria andExtraEqualTo(String value) {
            addCriterion("extra =", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotEqualTo(String value) {
            addCriterion("extra <>", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThan(String value) {
            addCriterion("extra >", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThanOrEqualTo(String value) {
            addCriterion("extra >=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThan(String value) {
            addCriterion("extra <", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThanOrEqualTo(String value) {
            addCriterion("extra <=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLike(String value) {
            addCriterion("extra like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotLike(String value) {
            addCriterion("extra not like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraIn(List<String> values) {
            addCriterion("extra in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotIn(List<String> values) {
            addCriterion("extra not in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraBetween(String value1, String value2) {
            addCriterion("extra between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotBetween(String value1, String value2) {
            addCriterion("extra not between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andNotifyurlIsNull() {
            addCriterion("notifyurl is null");
            return (Criteria) this;
        }

        public Criteria andNotifyurlIsNotNull() {
            addCriterion("notifyurl is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyurlEqualTo(String value) {
            addCriterion("notifyurl =", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotEqualTo(String value) {
            addCriterion("notifyurl <>", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlGreaterThan(String value) {
            addCriterion("notifyurl >", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlGreaterThanOrEqualTo(String value) {
            addCriterion("notifyurl >=", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlLessThan(String value) {
            addCriterion("notifyurl <", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlLessThanOrEqualTo(String value) {
            addCriterion("notifyurl <=", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlLike(String value) {
            addCriterion("notifyurl like", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotLike(String value) {
            addCriterion("notifyurl not like", value, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlIn(List<String> values) {
            addCriterion("notifyurl in", values, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotIn(List<String> values) {
            addCriterion("notifyurl not in", values, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlBetween(String value1, String value2) {
            addCriterion("notifyurl between", value1, value2, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andNotifyurlNotBetween(String value1, String value2) {
            addCriterion("notifyurl not between", value1, value2, "notifyurl");
            return (Criteria) this;
        }

        public Criteria andParam1IsNull() {
            addCriterion("param1 is null");
            return (Criteria) this;
        }

        public Criteria andParam1IsNotNull() {
            addCriterion("param1 is not null");
            return (Criteria) this;
        }

        public Criteria andParam1EqualTo(String value) {
            addCriterion("param1 =", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotEqualTo(String value) {
            addCriterion("param1 <>", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1GreaterThan(String value) {
            addCriterion("param1 >", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1GreaterThanOrEqualTo(String value) {
            addCriterion("param1 >=", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1LessThan(String value) {
            addCriterion("param1 <", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1LessThanOrEqualTo(String value) {
            addCriterion("param1 <=", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1Like(String value) {
            addCriterion("param1 like", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotLike(String value) {
            addCriterion("param1 not like", value, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1In(List<String> values) {
            addCriterion("param1 in", values, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotIn(List<String> values) {
            addCriterion("param1 not in", values, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1Between(String value1, String value2) {
            addCriterion("param1 between", value1, value2, "param1");
            return (Criteria) this;
        }

        public Criteria andParam1NotBetween(String value1, String value2) {
            addCriterion("param1 not between", value1, value2, "param1");
            return (Criteria) this;
        }

        public Criteria andParam2IsNull() {
            addCriterion("param2 is null");
            return (Criteria) this;
        }

        public Criteria andParam2IsNotNull() {
            addCriterion("param2 is not null");
            return (Criteria) this;
        }

        public Criteria andParam2EqualTo(String value) {
            addCriterion("param2 =", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotEqualTo(String value) {
            addCriterion("param2 <>", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2GreaterThan(String value) {
            addCriterion("param2 >", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2GreaterThanOrEqualTo(String value) {
            addCriterion("param2 >=", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2LessThan(String value) {
            addCriterion("param2 <", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2LessThanOrEqualTo(String value) {
            addCriterion("param2 <=", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2Like(String value) {
            addCriterion("param2 like", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotLike(String value) {
            addCriterion("param2 not like", value, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2In(List<String> values) {
            addCriterion("param2 in", values, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotIn(List<String> values) {
            addCriterion("param2 not in", values, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2Between(String value1, String value2) {
            addCriterion("param2 between", value1, value2, "param2");
            return (Criteria) this;
        }

        public Criteria andParam2NotBetween(String value1, String value2) {
            addCriterion("param2 not between", value1, value2, "param2");
            return (Criteria) this;
        }

        public Criteria andExpire_timeIsNull() {
            addCriterion("expire_time is null");
            return (Criteria) this;
        }

        public Criteria andExpire_timeIsNotNull() {
            addCriterion("expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpire_timeEqualTo(String value) {
            addCriterion("expire_time =", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeNotEqualTo(String value) {
            addCriterion("expire_time <>", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeGreaterThan(String value) {
            addCriterion("expire_time >", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeGreaterThanOrEqualTo(String value) {
            addCriterion("expire_time >=", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeLessThan(String value) {
            addCriterion("expire_time <", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeLessThanOrEqualTo(String value) {
            addCriterion("expire_time <=", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeLike(String value) {
            addCriterion("expire_time like", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeNotLike(String value) {
            addCriterion("expire_time not like", value, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeIn(List<String> values) {
            addCriterion("expire_time in", values, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeNotIn(List<String> values) {
            addCriterion("expire_time not in", values, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeBetween(String value1, String value2) {
            addCriterion("expire_time between", value1, value2, "expire_time");
            return (Criteria) this;
        }

        public Criteria andExpire_timeNotBetween(String value1, String value2) {
            addCriterion("expire_time not between", value1, value2, "expire_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeIsNull() {
            addCriterion("refund_succ_time is null");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeIsNotNull() {
            addCriterion("refund_succ_time is not null");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeEqualTo(String value) {
            addCriterion("refund_succ_time =", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeNotEqualTo(String value) {
            addCriterion("refund_succ_time <>", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeGreaterThan(String value) {
            addCriterion("refund_succ_time >", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeGreaterThanOrEqualTo(String value) {
            addCriterion("refund_succ_time >=", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeLessThan(String value) {
            addCriterion("refund_succ_time <", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeLessThanOrEqualTo(String value) {
            addCriterion("refund_succ_time <=", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeLike(String value) {
            addCriterion("refund_succ_time like", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeNotLike(String value) {
            addCriterion("refund_succ_time not like", value, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeIn(List<String> values) {
            addCriterion("refund_succ_time in", values, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeNotIn(List<String> values) {
            addCriterion("refund_succ_time not in", values, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeBetween(String value1, String value2) {
            addCriterion("refund_succ_time between", value1, value2, "refund_succ_time");
            return (Criteria) this;
        }

        public Criteria andRefund_succ_timeNotBetween(String value1, String value2) {
            addCriterion("refund_succ_time not between", value1, value2, "refund_succ_time");
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