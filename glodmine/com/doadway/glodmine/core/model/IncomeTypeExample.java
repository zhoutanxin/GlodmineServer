package com.doadway.glodmine.core.model;

import java.util.ArrayList;
import java.util.List;

public class IncomeTypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IncomeTypeExample() {
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

    protected abstract static class GeneratedCriteria {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIcategoryIsNull() {
            addCriterion("icategory is null");
            return (Criteria) this;
        }

        public Criteria andIcategoryIsNotNull() {
            addCriterion("icategory is not null");
            return (Criteria) this;
        }

        public Criteria andIcategoryEqualTo(String value) {
            addCriterion("icategory =", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryNotEqualTo(String value) {
            addCriterion("icategory <>", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryGreaterThan(String value) {
            addCriterion("icategory >", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryGreaterThanOrEqualTo(String value) {
            addCriterion("icategory >=", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryLessThan(String value) {
            addCriterion("icategory <", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryLessThanOrEqualTo(String value) {
            addCriterion("icategory <=", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryLike(String value) {
            addCriterion("icategory like", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryNotLike(String value) {
            addCriterion("icategory not like", value, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryIn(List<String> values) {
            addCriterion("icategory in", values, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryNotIn(List<String> values) {
            addCriterion("icategory not in", values, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryBetween(String value1, String value2) {
            addCriterion("icategory between", value1, value2, "icategory");
            return (Criteria) this;
        }

        public Criteria andIcategoryNotBetween(String value1, String value2) {
            addCriterion("icategory not between", value1, value2, "icategory");
            return (Criteria) this;
        }

        public Criteria andSaysIsNull() {
            addCriterion("says is null");
            return (Criteria) this;
        }

        public Criteria andSaysIsNotNull() {
            addCriterion("says is not null");
            return (Criteria) this;
        }

        public Criteria andSaysEqualTo(String value) {
            addCriterion("says =", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysNotEqualTo(String value) {
            addCriterion("says <>", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysGreaterThan(String value) {
            addCriterion("says >", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysGreaterThanOrEqualTo(String value) {
            addCriterion("says >=", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysLessThan(String value) {
            addCriterion("says <", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysLessThanOrEqualTo(String value) {
            addCriterion("says <=", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysLike(String value) {
            addCriterion("says like", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysNotLike(String value) {
            addCriterion("says not like", value, "says");
            return (Criteria) this;
        }

        public Criteria andSaysIn(List<String> values) {
            addCriterion("says in", values, "says");
            return (Criteria) this;
        }

        public Criteria andSaysNotIn(List<String> values) {
            addCriterion("says not in", values, "says");
            return (Criteria) this;
        }

        public Criteria andSaysBetween(String value1, String value2) {
            addCriterion("says between", value1, value2, "says");
            return (Criteria) this;
        }

        public Criteria andSaysNotBetween(String value1, String value2) {
            addCriterion("says not between", value1, value2, "says");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNull() {
            addCriterion("member_id is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("member_id is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(Integer value) {
            addCriterion("member_id =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(Integer value) {
            addCriterion("member_id <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(Integer value) {
            addCriterion("member_id >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("member_id >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(Integer value) {
            addCriterion("member_id <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(Integer value) {
            addCriterion("member_id <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<Integer> values) {
            addCriterion("member_id in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<Integer> values) {
            addCriterion("member_id not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(Integer value1, Integer value2) {
            addCriterion("member_id between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(Integer value1, Integer value2) {
            addCriterion("member_id not between", value1, value2, "memberId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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