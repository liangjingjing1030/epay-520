package org.epay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankTariffExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public BankTariffExample() {
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

        public Criteria andBank_tariff_idIsNull() {
            addCriterion("bank_tariff_id is null");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idIsNotNull() {
            addCriterion("bank_tariff_id is not null");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idEqualTo(String value) {
            addCriterion("bank_tariff_id =", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idNotEqualTo(String value) {
            addCriterion("bank_tariff_id <>", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idGreaterThan(String value) {
            addCriterion("bank_tariff_id >", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idGreaterThanOrEqualTo(String value) {
            addCriterion("bank_tariff_id >=", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idLessThan(String value) {
            addCriterion("bank_tariff_id <", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idLessThanOrEqualTo(String value) {
            addCriterion("bank_tariff_id <=", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idLike(String value) {
            addCriterion("bank_tariff_id like", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idNotLike(String value) {
            addCriterion("bank_tariff_id not like", value, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idIn(List<String> values) {
            addCriterion("bank_tariff_id in", values, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idNotIn(List<String> values) {
            addCriterion("bank_tariff_id not in", values, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idBetween(String value1, String value2) {
            addCriterion("bank_tariff_id between", value1, value2, "bank_tariff_id");
            return (Criteria) this;
        }

        public Criteria andBank_tariff_idNotBetween(String value1, String value2) {
            addCriterion("bank_tariff_id not between", value1, value2, "bank_tariff_id");
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

        public Criteria andDeal_moneyIsNull() {
            addCriterion("deal_money is null");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyIsNotNull() {
            addCriterion("deal_money is not null");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyEqualTo(Long value) {
            addCriterion("deal_money =", value, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyNotEqualTo(Long value) {
            addCriterion("deal_money <>", value, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyGreaterThan(Long value) {
            addCriterion("deal_money >", value, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyGreaterThanOrEqualTo(Long value) {
            addCriterion("deal_money >=", value, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyLessThan(Long value) {
            addCriterion("deal_money <", value, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyLessThanOrEqualTo(Long value) {
            addCriterion("deal_money <=", value, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyIn(List<Long> values) {
            addCriterion("deal_money in", values, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyNotIn(List<Long> values) {
            addCriterion("deal_money not in", values, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyBetween(Long value1, Long value2) {
            addCriterion("deal_money between", value1, value2, "deal_money");
            return (Criteria) this;
        }

        public Criteria andDeal_moneyNotBetween(Long value1, Long value2) {
            addCriterion("deal_money not between", value1, value2, "deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyIsNull() {
            addCriterion("third_party_deal_money is null");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyIsNotNull() {
            addCriterion("third_party_deal_money is not null");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyEqualTo(Long value) {
            addCriterion("third_party_deal_money =", value, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyNotEqualTo(Long value) {
            addCriterion("third_party_deal_money <>", value, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyGreaterThan(Long value) {
            addCriterion("third_party_deal_money >", value, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyGreaterThanOrEqualTo(Long value) {
            addCriterion("third_party_deal_money >=", value, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyLessThan(Long value) {
            addCriterion("third_party_deal_money <", value, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyLessThanOrEqualTo(Long value) {
            addCriterion("third_party_deal_money <=", value, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyIn(List<Long> values) {
            addCriterion("third_party_deal_money in", values, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyNotIn(List<Long> values) {
            addCriterion("third_party_deal_money not in", values, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyBetween(Long value1, Long value2) {
            addCriterion("third_party_deal_money between", value1, value2, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_deal_moneyNotBetween(Long value1, Long value2) {
            addCriterion("third_party_deal_money not between", value1, value2, "third_party_deal_money");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateIsNull() {
            addCriterion("third_party_checkout_rate is null");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateIsNotNull() {
            addCriterion("third_party_checkout_rate is not null");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateEqualTo(Integer value) {
            addCriterion("third_party_checkout_rate =", value, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateNotEqualTo(Integer value) {
            addCriterion("third_party_checkout_rate <>", value, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateGreaterThan(Integer value) {
            addCriterion("third_party_checkout_rate >", value, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateGreaterThanOrEqualTo(Integer value) {
            addCriterion("third_party_checkout_rate >=", value, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateLessThan(Integer value) {
            addCriterion("third_party_checkout_rate <", value, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateLessThanOrEqualTo(Integer value) {
            addCriterion("third_party_checkout_rate <=", value, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateIn(List<Integer> values) {
            addCriterion("third_party_checkout_rate in", values, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateNotIn(List<Integer> values) {
            addCriterion("third_party_checkout_rate not in", values, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateBetween(Integer value1, Integer value2) {
            addCriterion("third_party_checkout_rate between", value1, value2, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andThird_party_checkout_rateNotBetween(Integer value1, Integer value2) {
            addCriterion("third_party_checkout_rate not between", value1, value2, "third_party_checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateIsNull() {
            addCriterion("checkout_rate is null");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateIsNotNull() {
            addCriterion("checkout_rate is not null");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateEqualTo(Integer value) {
            addCriterion("checkout_rate =", value, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateNotEqualTo(Integer value) {
            addCriterion("checkout_rate <>", value, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateGreaterThan(Integer value) {
            addCriterion("checkout_rate >", value, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateGreaterThanOrEqualTo(Integer value) {
            addCriterion("checkout_rate >=", value, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateLessThan(Integer value) {
            addCriterion("checkout_rate <", value, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateLessThanOrEqualTo(Integer value) {
            addCriterion("checkout_rate <=", value, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateIn(List<Integer> values) {
            addCriterion("checkout_rate in", values, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateNotIn(List<Integer> values) {
            addCriterion("checkout_rate not in", values, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateBetween(Integer value1, Integer value2) {
            addCriterion("checkout_rate between", value1, value2, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andCheckout_rateNotBetween(Integer value1, Integer value2) {
            addCriterion("checkout_rate not between", value1, value2, "checkout_rate");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyIsNull() {
            addCriterion("tariff_money is null");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyIsNotNull() {
            addCriterion("tariff_money is not null");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyEqualTo(Long value) {
            addCriterion("tariff_money =", value, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyNotEqualTo(Long value) {
            addCriterion("tariff_money <>", value, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyGreaterThan(Long value) {
            addCriterion("tariff_money >", value, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyGreaterThanOrEqualTo(Long value) {
            addCriterion("tariff_money >=", value, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyLessThan(Long value) {
            addCriterion("tariff_money <", value, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyLessThanOrEqualTo(Long value) {
            addCriterion("tariff_money <=", value, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyIn(List<Long> values) {
            addCriterion("tariff_money in", values, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyNotIn(List<Long> values) {
            addCriterion("tariff_money not in", values, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyBetween(Long value1, Long value2) {
            addCriterion("tariff_money between", value1, value2, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andTariff_moneyNotBetween(Long value1, Long value2) {
            addCriterion("tariff_money not between", value1, value2, "tariff_money");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateIsNull() {
            addCriterion("checkout_date is null");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateIsNotNull() {
            addCriterion("checkout_date is not null");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateEqualTo(String value) {
            addCriterion("checkout_date =", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateNotEqualTo(String value) {
            addCriterion("checkout_date <>", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateGreaterThan(String value) {
            addCriterion("checkout_date >", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateGreaterThanOrEqualTo(String value) {
            addCriterion("checkout_date >=", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateLessThan(String value) {
            addCriterion("checkout_date <", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateLessThanOrEqualTo(String value) {
            addCriterion("checkout_date <=", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateLike(String value) {
            addCriterion("checkout_date like", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateNotLike(String value) {
            addCriterion("checkout_date not like", value, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateIn(List<String> values) {
            addCriterion("checkout_date in", values, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateNotIn(List<String> values) {
            addCriterion("checkout_date not in", values, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateBetween(String value1, String value2) {
            addCriterion("checkout_date between", value1, value2, "checkout_date");
            return (Criteria) this;
        }

        public Criteria andCheckout_dateNotBetween(String value1, String value2) {
            addCriterion("checkout_date not between", value1, value2, "checkout_date");
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